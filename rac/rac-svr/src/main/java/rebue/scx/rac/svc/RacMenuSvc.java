package rebue.scx.rac.svc;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacMenuJo;
import rebue.scx.rac.mo.RacMenuMo;
import rebue.scx.rac.to.RacMenuAddTo;
import rebue.scx.rac.to.RacMenuDelTo;
import rebue.scx.rac.to.RacMenuListTo;
import rebue.scx.rac.to.RacMenuModifyTo;
import rebue.scx.rac.to.RacMenuOneTo;

// @Validated注解按规范应该写在接口上；@Valid注解在参数是POJO类时写在参数类型的前面，具体约束的注解写在参数类型的属性的上方；而普通参数则写在方法的上方，具体约束的注解直接写在参数类型的前面
@Validated
public interface RacMenuSvc extends BaseSvc<java.lang.Long, RacMenuAddTo, RacMenuModifyTo, RacMenuDelTo, RacMenuOneTo, RacMenuListTo, RacMenuMo, RacMenuJo> {
}
