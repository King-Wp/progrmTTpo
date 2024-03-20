package org.guava.CacheService.mok;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author: 11653
 * @createTime: 2024/03/15 11:44
 * @package: mok
 * @description:
 */

@Aspect
@Component
public class MyAspect {


    @After("execution(* org.guava.CacheService.Coller.DemoHttp.test())")
    public void run1() {

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    @Around("execution(* org.guava.CacheService.Coller.DemoHttp.test())")
//    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
//        //System.out.println("环绕通知：目标方法执行前");
//
//        // 在目标方法执行前的逻辑
//
//        // 调用目标方法
//        Object result = joinPoint.proceed();
//
//        System.out.println("环绕通知：目标方法执行后" + result);
//        try {
//            // 调用目标方法
//            return result;
//        } finally {
//            // 在目标方法执行后的逻辑
//            System.out.println(result);
//            System.out.println("环绕通知：方法返回结果后的逻辑");
//        }
//
//    }
}
