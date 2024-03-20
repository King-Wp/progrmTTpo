package org.guava;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description: GuavaApplication
 * @Version: 1.0
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("org.guava.CacheService.mapper")
@EnableAspectJAutoProxy
public class GuavaApplication
{
    public static void main(String[] args) {
        SpringApplication.run(GuavaApplication.class, args);
    }
}
