package com.tims.facade.hessian;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * hessian 接口客户端自动扫描注入
 * Created by peterc on 2017/7/14.
 */
public class HessianClientProxyBeanDefinitionRegistryProcessor implements
        BeanDefinitionRegistryPostProcessor, InitializingBean,
        ApplicationContextAware, BeanNameAware {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private String beanName;
    private String basePackage;
    private String remoteURL; //hessian远程服务地址
    private String remoteURLPropertyName;//hessian远程服务地址属性名称
    private boolean includeAnnotationConfig = true;
    private ApplicationContext applicationContext;
    // 实现了该接口
    private Class<?> markerInterface;
    // 配置了该注解
    private Class<? extends Annotation> annotationClass;

    private BeanNameGenerator nameGenerator;

    @Override
    public void postProcessBeanFactory(
            ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String getRemoteURLPropertyName() {
        return remoteURLPropertyName;
    }

    public void setRemoteURLPropertyName(String remoteURLPropertyName) {
        this.remoteURLPropertyName = remoteURLPropertyName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(StringUtils.isEmpty(remoteURLPropertyName)) throw new Exception("服务端地址属性名称未配置");
        //获取本hessian服务端地址
        String profilesActive = applicationContext.getEnvironment().getActiveProfiles()[0];
        if (StringUtils.isEmpty(profilesActive))
            this.remoteURL = getProperty("/application.properties", remoteURLPropertyName);
        else
            this.remoteURL = getProperty("/application-" + profilesActive + ".properties", remoteURLPropertyName);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(
            BeanDefinitionRegistry registry) throws BeansException {

        //如果未配置本hessian服务端地址,则不做本hessian服务的client端注册
        if (StringUtils.isEmpty(this.remoteURL)) {
            logger.warn("未服务端地址， 则服务端忽略");
            return;
        }
        HessianClassPathScanner scan = new HessianClassPathScanner(registry, this.remoteURL);
        scan.setResourceLoader(this.applicationContext);
        scan.setBeanNameGenerator(this.nameGenerator);
        // 引入注解配置
        scan.setIncludeAnnotationConfig(this.includeAnnotationConfig);
        scan.registerFilters();
        String[] basePackages = StringUtils.tokenizeToStringArray(this.basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
        scan.scan(basePackages);
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setIncludeAnnotationConfig(boolean includeAnnotationConfig) {
        this.includeAnnotationConfig = includeAnnotationConfig;
    }

    public Class<?> getMarkerInterface() {
        return markerInterface;
    }

    public void setMarkerInterface(Class<?> markerInterface) {
        this.markerInterface = markerInterface;
    }

    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public BeanNameGenerator getNameGenerator() {
        return nameGenerator;
    }

    public void setNameGenerator(BeanNameGenerator nameGenerator) {
        this.nameGenerator = nameGenerator;
    }

    private String getProperty(String classPath, String key) {
        Assert.notNull(classPath, "class path is null");
        Assert.notNull(key, "key is null");
        try {
            Resource resource = new ClassPathResource(classPath);
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            String value = props.getProperty(key);
            return value;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private class HessianClassPathScanner extends ClassPathBeanDefinitionScanner {
        protected final Logger logger = LoggerFactory.getLogger(getClass());
        private String remoteURL;

        public HessianClassPathScanner(BeanDefinitionRegistry registry, String remoteURL) {
            super(registry, false);
            Assert.hasText(remoteURL, "hessian 服务端地址为配置");
            this.remoteURL = remoteURL;
        }

        @Override
        public Set<BeanDefinitionHolder> doScan(String... basePackages) {
            Set<BeanDefinitionHolder> beanDefinitions = null;
            try {
                beanDefinitions = super.doScan(basePackages);
            } catch (BeanCreationException exception) {
                logger.info(exception.getMessage());
            }
            if (beanDefinitions == null || beanDefinitions.isEmpty()) {
                logger.warn("No hessian was found in '"
                        + Arrays.toString(basePackages)
                        + "' package. Please check your configuration.");
            } else {
                for (BeanDefinitionHolder holder : beanDefinitions) {
                    GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();

                    if (logger.isDebugEnabled()) {
                        logger.debug("Creating HessianFactoryBean with name '"
                                + holder.getBeanName() + "' and '"
                                + definition.getBeanClassName()
                                + "' hessianInterface");
                    }

                    AnnotationMetadata metadata = ((ScannedGenericBeanDefinition) definition).getMetadata();
                    Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(annotationClass.getName());
                    String uri = (String) annotationAttributes.get("uri");
                    Boolean overloadEnabled = MapUtils.getBoolean(annotationAttributes, "overloadEnabled", false);
                    definition.getPropertyValues().add("serviceUrl", this.remoteURL + uri);
                    definition.getPropertyValues().add("serviceInterface", definition.getBeanClassName());
//                  新增overloadEnabled属性，并把它的值设置为true，默认是false，则Hessian就能支持方法的重载了
                    definition.getPropertyValues().add("overloadEnabled", overloadEnabled);
                    definition.setBeanClass(HessianProxyFactoryBean.class);
                }
            }
            return beanDefinitions;

        }

        @Override
        protected boolean isCandidateComponent(
                AnnotatedBeanDefinition beanDefinition) {
            return (beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected boolean checkCandidate(String beanName,
                                         BeanDefinition beanDefinition) throws IllegalStateException {
            if (super.checkCandidate(beanName, beanDefinition)) {
                return true;
            } else {
                logger.warn("Skipping HessianFactoryBean with name '" + beanName
                        + "' and '" + beanDefinition.getBeanClassName()
                        + "' hessianInterface"
                        + ". Bean already defined with the same name!");
                return false;
            }
        }

        public void registerFilters() {
            boolean acceptAllInterfaces = true;

            // if specified, use the given annotation and / or marker interface
            if (HessianClientProxyBeanDefinitionRegistryProcessor.this.annotationClass != null) {
                addIncludeFilter(new AnnotationTypeFilter(HessianClientProxyBeanDefinitionRegistryProcessor.this.annotationClass));
                acceptAllInterfaces = false;
            }

            // override AssignableTypeFilter to ignore matches on the actual marker interface
            if (HessianClientProxyBeanDefinitionRegistryProcessor.this.markerInterface != null) {
                addIncludeFilter(new AssignableTypeFilter(HessianClientProxyBeanDefinitionRegistryProcessor.this.markerInterface) {
                    @Override
                    protected boolean matchClassName(String className) {
                        return false;
                    }
                });
                acceptAllInterfaces = false;
            }

            if (acceptAllInterfaces) {
                // default include filter that accepts all classes
                addIncludeFilter(new TypeFilter() {
                    @Override
                    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                        return true;
                    }
                });
            }

            // exclude package-info.java
            addExcludeFilter(new TypeFilter() {
                @Override
                public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                    String className = metadataReader.getClassMetadata().getClassName();
                    return className.endsWith("package-info");
                }
            });
        }
    }
}