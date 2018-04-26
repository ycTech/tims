package com.tims.manage;

import com.github.pagehelper.PageHelper;
import com.github.tobato.fastdfs.FdfsClientConfig;
import com.sun.glass.ui.Application;
import com.tims.facade.config.TimsCoreHessianAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

import java.util.Properties;

/**
 * manage启动类
 *
 * @author liuzm
 * @create 2018-02-27 17:56
 **/
//解决jmx重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@SpringBootApplication
@Import({TimsCoreHessianAutoConfiguration.class,FdfsClientConfig.class})
public class TimsManageController extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        builder.sources(Application.class);
        return super.configure(builder);
    }

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("dialect", "mysql");
        p.setProperty("supportMethodsArguments", "false");
        p.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }

    public static void main(String[] args) {
        SpringApplication.run(TimsManageController.class, args);
    }

}
