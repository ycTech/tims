package cn.keking;

import com.tims.facade.config.TimsCoreHessianAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.util.Properties;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableScheduling
@ComponentScan(value = "cn.keking.*")
@Import({TimsCoreHessianAutoConfiguration.class})
public class FilePreviewApplication {
	public static void main(String[] args) {
        Properties properties = System.getProperties();
        System.out.println(properties.get("user.dir"));
        SpringApplication.run(FilePreviewApplication.class, args);
	}
}
