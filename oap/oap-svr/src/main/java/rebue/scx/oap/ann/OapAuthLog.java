package rebue.scx.oap.ann;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 添加认证日志注解
 *
 * XXX 此注解放在控制器层的方法上
 *
 * @author yuanman
 *
 */
@Target({ ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OapAuthLog {

    String value() default "";

}