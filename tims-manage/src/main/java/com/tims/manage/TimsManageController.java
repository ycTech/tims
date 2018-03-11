package com.tims.manage;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.tims.facade.config.TimsCoreHessianAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

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
public class TimsManageController {
    public static void main(String[] args) {
        SpringApplication.run(TimsManageController.class, args);
    }

}
