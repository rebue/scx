package com.github.rebue.scx.svc;

import com.github.rebue.scx.mo.OapRedirectUriMo;
import com.github.rebue.scx.to.OapRedirectUriAddTo;
import com.github.rebue.scx.to.OapRedirectUriModifyTo;
import com.github.rebue.scx.to.OapRedirectUriDelTo;
import com.github.rebue.scx.to.OapRedirectUriOneTo;
import com.github.rebue.scx.to.OapRedirectUriListTo;
import com.github.rebue.scx.to.OapRedirectUriPageTo;
import com.github.rebue.scx.jo.OapRedirectUriJo;
import org.springframework.validation.annotation.Validated;
import rebue.robotech.svc.BaseSvc;

/**
 * 服务接口
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
