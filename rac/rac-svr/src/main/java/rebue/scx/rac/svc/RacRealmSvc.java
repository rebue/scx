package rebue.scx.rac.svc;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacRealmJo;
import rebue.scx.rac.mo.RacRealmMo;
import rebue.scx.rac.to.RacRealmAddTo;
import rebue.scx.rac.to.RacRealmDelTo;
import rebue.scx.rac.to.RacRealmListTo;
import rebue.scx.rac.to.RacRealmModifyTo;
import rebue.scx.rac.to.RacRealmOneTo;
import rebue.scx.rac.to.RacRealmPageTo;

/**
 * 领域服务接口
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
public interface RacRealmSvc
    extends BaseSvc<java.lang.String, RacRealmAddTo, RacRealmModifyTo, RacRealmDelTo, RacRealmOneTo, RacRealmListTo, RacRealmPageTo, RacRealmMo, RacRealmJo> {
}
