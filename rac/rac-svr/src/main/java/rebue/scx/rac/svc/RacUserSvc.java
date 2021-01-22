package rebue.scx.rac.svc;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacUserJo;
import rebue.scx.rac.mo.RacUserMo;
import rebue.scx.rac.to.RacUserAddTo;
import rebue.scx.rac.to.RacUserDelTo;
import rebue.scx.rac.to.RacUserListTo;
import rebue.scx.rac.to.RacUserModifyTo;
import rebue.scx.rac.to.RacUserOneTo;
import rebue.scx.rac.to.RacUserPageTo;

/**
 * 用户服务接口
 *
 * Validated注解按规范应该写在接口上
 *
 * Valid注解在参数是POJO类时，写在参数类型的前面，具体约束的注解写在参数类型的属性的上方
 * Valid注解在参数是普通参数时，写在方法的上方，具体约束的注解直接写在参数类型的前面
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Validated
public interface RacUserSvc extends BaseSvc<java.lang.Long, RacUserAddTo, RacUserModifyTo, RacUserDelTo, RacUserOneTo, RacUserListTo, RacUserPageTo, RacUserMo, RacUserJo> {
}
