package rebue.scx.rac.svc;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacPermMenuJo;
import rebue.scx.rac.mo.RacPermMenuMo;
import rebue.scx.rac.to.RacPermMenuAddTo;
import rebue.scx.rac.to.RacPermMenuDelTo;
import rebue.scx.rac.to.RacPermMenuListTo;
import rebue.scx.rac.to.RacPermMenuModifyTo;
import rebue.scx.rac.to.RacPermMenuOneTo;
import rebue.scx.rac.to.RacPermMenuPageTo;

/**
 * 权限菜单服务接口
 *
 * Validated注解按规范应该写在接口上
 *
 * Valid注解在参数是POJO类时，写在参数类型的前面，具体约束的注解写在参数类型的属性的上方
 * Valid注解在参数是普通参数时，写在方法的上方，具体约束的注解直接写在参数类型的前面
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Validated
public interface RacPermMenuSvc
    extends BaseSvc<java.lang.Long, RacPermMenuAddTo, RacPermMenuModifyTo, RacPermMenuDelTo, RacPermMenuOneTo, RacPermMenuListTo, RacPermMenuPageTo, RacPermMenuMo, RacPermMenuJo> {

    List<String> getMenusOfAccount(Long accountId, String sysId);
}
