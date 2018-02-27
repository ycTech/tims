package com.tims.facade.hessian;


import com.tims.common.util.AopTargetUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.remoting.caucho.HessianServiceExporter;

import java.util.Map;

/**
 * hessian service exporter bean definition registry post processor
 * <p>
 * Created by peterc on 2017/7/14
 */
public class HessianServiceExporterBeanDefinitionRegistry {
    private ApplicationContext ctx = null;

    public HessianServiceExporterBeanDefinitionRegistry(ApplicationContext ctx) throws Exception {
        this.ctx = ctx;
        register();
    }

    private HessianService getHessianServericAnnotation(Class<?> hessainInterface) {
        if (null == hessainInterface) throw new NullPointerException("without hessian service interface");
        HessianService[] annotationsByTypes = hessainInterface.getAnnotationsByType(HessianService.class);
        if (null == annotationsByTypes) throw new NullPointerException("without hessian service annotations");
        return annotationsByTypes[0];
    }

    private Class<?> getHessianInterface(Object hessianServiceBean) throws Exception {
        hessianServiceBean = AopTargetUtils.getTarget(hessianServiceBean);
        if(null == hessianServiceBean) return null;
        Class<?>[] interfaceClasses = hessianServiceBean.getClass().getInterfaces();
        if (null == interfaceClasses) throw new NullPointerException("without interface");
        for (Class<?> interfaceClass : interfaceClasses) {
            HessianService[] annotationsByType = interfaceClass.getAnnotationsByType(HessianService.class);
            if (null != annotationsByType) return interfaceClass;
        }
        return null;
    }

    private BeanDefinition createHessianServiceExporterBeanDefinition(Object hessianServiceBean, Class<?> interfaceClasse) {
        if (null == hessianServiceBean) throw new NullPointerException("hessian Service Bean is null");
        if (null == interfaceClasse) throw new NullPointerException("hessian Service inteface is null");


        return BeanDefinitionBuilder.rootBeanDefinition(HessianServiceExporter.class)
                .addPropertyValue("service", hessianServiceBean)
                .addPropertyValue("serviceInterface", interfaceClasse)
                .getBeanDefinition();
    }

    private void register() throws Exception {
        DefaultListableBeanFactory registry = (DefaultListableBeanFactory) ctx.getAutowireCapableBeanFactory();
        Map<String, Object> hessianServiceBeans = ctx.getBeansWithAnnotation(HessianService.class);
        for (Map.Entry<String, Object> entry : hessianServiceBeans.entrySet()) {
            Class<?> hessianInterface = getHessianInterface(entry.getValue());
            if (null == hessianInterface) continue;
            HessianService hessianService = getHessianServericAnnotation(hessianInterface);
            BeanDefinition beanDefinition = createHessianServiceExporterBeanDefinition(entry.getValue(), hessianInterface);
            registry.registerBeanDefinition(hessianService.uri(), beanDefinition);
        }
    }
}
