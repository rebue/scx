package rebue.scx.rac.svc;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacStatusJo;
import rebue.scx.rac.mo.RacStatusMo;
import rebue.scx.rac.to.RacStatusAddTo;
import rebue.scx.rac.to.RacStatusDelTo;
import rebue.scx.rac.to.RacStatusListTo;
import rebue.scx.rac.to.RacStatusModifyTo;
import rebue.scx.rac.to.RacStatusOneTo;
import rebue.scx.rac.to.RacStatusPageTo;

/**
 * 身份服务接口
 *
 * <pre>
 * 1. 在接口上方必须写上 @Validated 注解
 * 2. 参数是POJO类时用 @Valid 注解在参数类型的前面进行修饰
 *    参数是普通参数时，直接在参数类型的前面加上具体约束的注解
 * 3. (待验证)有分组时，在方法上方必须写上 @Validated 注解及分组
 * 4. 踩坑留痕：
 *    如果方法的返回值为void，在方法上方加上 @Valid 注解会出现异常，报HV000132错误
 * </pre>
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Validated
public interface RacStatusSvc
    extends BaseSvc<java.lang.Long, RacStatusAddTo, RacStatusModifyTo, RacStatusDelTo, RacStatusOneTo, RacStatusListTo, RacStatusPageTo, RacStatusMo, RacStatusJo> {

}