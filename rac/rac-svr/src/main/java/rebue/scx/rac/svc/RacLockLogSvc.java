package rebue.scx.rac.svc;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacLockLogJo;
import rebue.scx.rac.mo.RacLockLogMo;
import rebue.scx.rac.to.RacLockLogAddTo;
import rebue.scx.rac.to.RacLockLogDelTo;
import rebue.scx.rac.to.RacLockLogListTo;
import rebue.scx.rac.to.RacLockLogModifyTo;
import rebue.scx.rac.to.RacLockLogOneTo;

// @Validated注解按规范应该写在接口上；@Valid注解在参数是POJO类时写在参数类型的前面，具体约束的注解写在参数类型的属性的上方；而普通参数则写在方法的上方，具体约束的注解直接写在参数类型的前面
@Validated
public interface RacLockLogSvc
        extends BaseSvc<java.lang.Long, RacLockLogAddTo, RacLockLogModifyTo, RacLockLogDelTo, RacLockLogOneTo, RacLockLogListTo, RacLockLogMo, RacLockLogJo> {
}
