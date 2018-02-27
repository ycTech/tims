package com.tims.core;

import com.tims.facade.config.TimsCoreHessianAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * core启动类
 *
 * @author liuzm
 * @create 2018-02-27 17:30
 **/
@SpringBootApplication
@Import({TimsCoreHessianAutoConfiguration.class})
public class TimsCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimsCoreApplication.class, args);
    }
}
