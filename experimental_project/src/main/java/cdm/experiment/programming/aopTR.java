package cdm.experiment.programming;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 11653
 * @createTime: 2024/07/05 15:54
 * @package: cdm.experiment
 * @description:
 */

@Aspect
@Component
public class aopTR {

    private final Map<String, Integer> usageCount = new HashMap<>();

    @Pointcut("@annotation(RecordFunction)")
    public void countedMethods() {
    }

    @After("countedMethods()")
    public void countServiceUsage(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RecordFunction counted = method.getAnnotation(RecordFunction.class);
        String s = counted.functionName();

        // 获取调用的方法名称
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        usageCount.put(methodName, usageCount.getOrDefault(methodName, 0) + 1);
    }

    public Map<String, Integer> getUsageCount() {
        return usageCount;
    }

}
