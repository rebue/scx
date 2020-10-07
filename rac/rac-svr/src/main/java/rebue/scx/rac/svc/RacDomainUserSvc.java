package rebue.scx.rac.svc;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacDomainUserJo;
import rebue.scx.rac.mo.RacDomainUserMo;
import rebue.scx.rac.to.RacDomainUserAddTo;
import rebue.scx.rac.to.RacDomainUserDelTo;
import rebue.scx.rac.to.RacDomainUserListTo;
import rebue.scx.rac.to.RacDomainUserModifyTo;
import rebue.scx.rac.to.RacDomainUserOneTo;

// @Validated注解按规范应该写在接口上；@Valid注解在参数是POJO类时写在参数类型的前面，具体约束的注解写在参数类型的属性的上方；而普通参数则写在方法的上方，具体约束的注解直接写在参数类型的前面
@Validated
public interface RacDomainUserSvc
        extends BaseSvc<java.lang.Long, RacDomainUserAddTo, RacDomainUserModifyTo, RacDomainUserDelTo, RacDomainUserOneTo, RacDomainUserListTo, RacDomainUserMo, RacDomainUserJo> {
}
