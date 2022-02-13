package rebue.scx.oap.svc;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.svc.BaseSvc;
import rebue.scx.oap.jo.OapRedirectUriJo;
import rebue.scx.oap.mo.OapRedirectUriMo;
import rebue.scx.oap.to.OapRedirectUriAddTo;
import rebue.scx.oap.to.OapRedirectUriDelTo;
import rebue.scx.oap.to.OapRedirectUriListTo;
import rebue.scx.oap.to.OapRedirectUriModifyTo;
import rebue.scx.oap.to.OapRedirectUriOneTo;
import rebue.scx.oap.to.OapRedirectUriPageTo;

/**
 * 第三方应用URL服务接口
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
public interface OapRedirectUriSvc extends
    BaseSvc<java.lang.Long, OapRedirectUriAddTo, OapRedirectUriModifyTo, OapRedirectUriDelTo, OapRedirectUriOneTo, OapRedirectUriListTo, OapRedirectUriPageTo, OapRedirectUriMo, OapRedirectUriJo> {
}
