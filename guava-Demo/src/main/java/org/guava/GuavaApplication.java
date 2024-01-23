package org.guava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description: GuavaApplication
 * @Version: 1.0
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class GuavaApplication
{
    public static void main(String[] args) {
        SpringApplication.run(GuavaApplication.class, args);
    }
}
