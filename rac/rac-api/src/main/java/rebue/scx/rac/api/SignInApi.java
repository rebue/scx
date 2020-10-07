package rebue.scx.rac.api;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.ex.SignInByUserNameTo;

/**
 * 用户登录API
 */
// 在接口上方必须写上 @Validated 注解；
// 有分组时，在方法上方必须写上 @Validated 注解及分组；
// 参数是POJO类时用 @Valid 注解在参数类型的前面进行修饰；
// 而如果是普通参数，则在方法的上方写上 @Validated 注解，具体约束的注解直接写在参数类型的前面
@Validated
public interface SignInApi {

    /**
     * 通过用户名称登录
     */
    Ro<SignUpOrInRa> signInByUserName(@Valid SignInByUserNameTo to);

}
