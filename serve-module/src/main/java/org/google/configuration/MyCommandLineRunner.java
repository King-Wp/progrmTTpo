package org.google.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author: 11653
 * @createTime: 2024/01/24 9:37
 * @package: org.google.configuration
 * @description: 启动后执行的
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("启动完成");
    }

}
