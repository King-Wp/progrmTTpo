package org.guava.CacheService.mok;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: 11653
 * @createTime: 2024/03/15 11:45
 * @package: mok
 * @description:
 */
public class DemoMain {
    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    public static void test01(){
        CACHE_REBUILD_EXECUTOR.submit(() -> {
            try {
                Thread.sleep(2000);
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CACHE_REBUILD_EXECUTOR.shutdown();
    }

}
