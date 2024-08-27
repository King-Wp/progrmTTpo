package cdm.experiment.programming;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 11653
 * @createTime: 2024/07/05 16:00
 * @package: cdm.experiment.programming
 * @description:
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RecordFunction {

    String functionName() default "";

}
