package rebue.scx.rac.ann;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RacOpLog {
    /**
     * 类型
     */
    String opType();

    /**
     * 标题
     */
    String opTitle();

    /**
     * 详情
     */
    String opDetail();
}
