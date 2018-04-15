package com.tims.core;

import com.sun.glass.ui.Application;
import com.tims.facade.config.TimsCoreHessianAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

/**
 * core启动类
 *
 * @author liuzm
 * @create 2018-02-27 17:30
 **/
@SpringBootApplication
@Import({TimsCoreHessianAutoConfiguration.class})
public class TimsCoreApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        builder.sources(Application.class);
        return super.configure(builder);
    }

    public static void main(String[] args) {
        SpringApplication.run(TimsCoreApplication.class, args);
    }
}
