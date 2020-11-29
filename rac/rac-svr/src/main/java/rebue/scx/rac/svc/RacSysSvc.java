package rebue.scx.rac.svc;

import org.springframework.validation.annotation.Validated;
import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacSysJo;
import rebue.scx.rac.mo.RacSysMo;
import rebue.scx.rac.to.RacSysAddTo;
import rebue.scx.rac.to.RacSysDelTo;
import rebue.scx.rac.to.RacSysListTo;
import rebue.scx.rac.to.RacSysModifyTo;
import rebue.scx.rac.to.RacSysOneTo;

/**
 * 系统服务接口
 * 
 * Validated注解按规范应该写在接口上
 * 
 * Valid注解在参数是POJO类时，写在参数类型的前面，具体约束的注解写在参数类型的属性的上方
 * Valid注解在参数是普通参数时，写在方法的上方，具体约束的注解直接写在参数类型的前面
 * 
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Validated
public interface RacSysSvc extends BaseSvc<java.lang.String, RacSysAddTo, RacSysModifyTo, RacSysDelTo, RacSysOneTo, RacSysListTo, RacSysMo, RacSysJo> {
}
