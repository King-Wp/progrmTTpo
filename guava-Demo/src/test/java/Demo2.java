import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author: 11653
 * @createTime: 2024/03/14 14:41
 * @package: PACKAGE_NAME
 * @description:
 */
@EnableAsync
public class Demo2 {

    @Bean(name = "asyncExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // 设置核心线程数
        executor.setMaxPoolSize(10); // 设置最大线程数
        executor.setQueueCapacity(25); // 设置队列容量
        executor.setThreadNamePrefix("MyAsync-"); // 设置线程名称前缀
        executor.initialize(); // 初始化线程池
        return executor;
    }


    @Async("asyncExecutor")
    public void asyncMethod() {
        // 异步执行的方法逻辑
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("开启异步线程" + i);
        }
    }


    private static String test() {
        Demo2 demo2 = new Demo2();
        for (int i = 0; i < 3; i++){
            System.out.println(i);
            if (i == 2){
                demo2.asyncMethod();
                return "2222";
            }
        }
        return "111";
    }


    public static void main(String[] args) {
        String test = test();
        System.out.println(test);
    }
}
