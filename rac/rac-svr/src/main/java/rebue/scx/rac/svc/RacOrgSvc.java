package rebue.scx.rac.svc;

import org.springframework.validation.annotation.Validated;
import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacOrgJo;
import rebue.scx.rac.mo.RacOrgMo;
import rebue.scx.rac.to.RacOrgAddTo;
import rebue.scx.rac.to.RacOrgDelTo;
import rebue.scx.rac.to.RacOrgListTo;
import rebue.scx.rac.to.RacOrgModifyTo;
import rebue.scx.rac.to.RacOrgOneTo;

// @Validated注解按规范应该写在接口上；@Valid注解在参数是POJO类时写在参数类型的前面，具体约束的注解写在参数类型的属性的上方；而普通参数则写在方法的上方，具体约束的注解直接写在参数类型的前面
@Validated
public interface RacOrgSvc extends BaseSvc<java.lang.Long, RacOrgAddTo, RacOrgModifyTo, RacOrgDelTo, RacOrgOneTo, RacOrgListTo, RacOrgMo, RacOrgJo> {
}
