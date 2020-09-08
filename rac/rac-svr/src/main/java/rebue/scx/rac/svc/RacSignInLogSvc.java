package rebue.scx.rac.svc;

import org.springframework.validation.annotation.Validated;
import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacSignInLogJo;
import rebue.scx.rac.mo.RacSignInLogMo;
import rebue.scx.rac.to.RacSignInLogAddTo;
import rebue.scx.rac.to.RacSignInLogDelTo;
import rebue.scx.rac.to.RacSignInLogListTo;
import rebue.scx.rac.to.RacSignInLogModifyTo;
import rebue.scx.rac.to.RacSignInLogOneTo;

// @Validated注解按规范应该写在接口上；@Valid注解在参数是POJO类时写在参数类型的前面，具体约束的注解写在参数类型的属性的上方；而普通参数则写在方法的上方，具体约束的注解直接写在参数类型的前面
@Validated
public interface RacSignInLogSvc extends BaseSvc<java.lang.Long, RacSignInLogAddTo, RacSignInLogModifyTo, RacSignInLogDelTo, RacSignInLogOneTo, RacSignInLogListTo, RacSignInLogMo, RacSignInLogJo> {
}
