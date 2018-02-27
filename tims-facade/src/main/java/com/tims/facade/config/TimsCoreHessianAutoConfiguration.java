package com.tims.facade.config;

import com.tims.facade.hessian.HessianClientProxyBeanDefinitionRegistryProcessor;
import com.tims.facade.hessian.HessianService;
import com.tims.facade.hessian.HessianServiceExporterBeanDefinitionRegistry;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * hessian自动注入
 *
 * @author liuzm
 * @create 2018-02-27 17:40
 **/

@Configuration
public class TimsCoreHessianAutoConfiguration implements ApplicationContextAware {

        /**
         * tc core hessian服务端地址配置参数
         */
        private static final String REMOTE_URL_PROPERTY_NAME = "tims.core.server.host.hessian.url";

        private ApplicationContext applicationContext;

        @Bean(name = "timsHessianServiceExporterBeanDefinitionRegistryPostProcessor")
        public HessianServiceExporterBeanDefinitionRegistry hessianServiceExporterBeanDefinitionRegistryPostProcessor() throws Exception {
            return new HessianServiceExporterBeanDefinitionRegistry(this.applicationContext);
        }

        @Bean(name = "timsHessianClientProxyBeanDefinitionRegistryParser")
        public HessianClientProxyBeanDefinitionRegistryProcessor hessianClientProxyBeanDefinitionRegistryParser() {
            HessianClientProxyBeanDefinitionRegistryProcessor parser = new HessianClientProxyBeanDefinitionRegistryProcessor();
            parser.setAnnotationClass(HessianService.class);
            parser.setRemoteURLPropertyName(REMOTE_URL_PROPERTY_NAME); //hessian服务端地址
            parser.setBasePackage("com.tims.facade.api");    //hessian api 接口包目录
            return parser;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }

}
