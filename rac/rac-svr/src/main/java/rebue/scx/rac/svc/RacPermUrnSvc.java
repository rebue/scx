package rebue.scx.rac.svc;

import org.springframework.validation.annotation.Validated;
import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacPermUrnJo;
import rebue.scx.rac.mo.RacPermUrnMo;
import rebue.scx.rac.to.RacPermUrnAddTo;
import rebue.scx.rac.to.RacPermUrnDelTo;
import rebue.scx.rac.to.RacPermUrnModifyTo;
import rebue.scx.rac.to.RacPermUrnOneTo;
import rebue.scx.rac.to.RacPermUrnPageTo;

/**
 * 权限URN服务接口
 *
 * Validated注解按规范应该写在接口上
 *
 * Valid注解在参数是POJO类时，写在参数类型的前面，具体约束的注解写在参数类型的属性的上方
 * Valid注解在参数是普通参数时，写在方法的上方，具体约束的注解直接写在参数类型的前面
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Validated
public interface RacPermUrnSvc
    extends BaseSvc<java.lang.Long, RacPermUrnAddTo, RacPermUrnModifyTo, RacPermUrnDelTo, RacPermUrnOneTo, RacPermUrnPageTo, RacPermUrnMo, RacPermUrnJo> {
}
