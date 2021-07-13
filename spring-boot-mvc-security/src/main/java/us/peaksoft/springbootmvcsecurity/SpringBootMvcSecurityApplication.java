package us.peaksoft.springbootmvcsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableJpaRepositories(basePackages="us.peaksoft.springbootmvcsecurity")
public class SpringBootMvcSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMvcSecurityApplication.class, args);
    }

}
