package org.google.configuration;

import org.google.core.RedisCache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author: 11653
 * @createTime: 2024/01/24 9:37
 * @package: org.google.configuration
 * @description: 启动后执行的
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Resource
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("启动完成");
    }

}
