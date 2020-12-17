package rebue.scx.rac.api.ex;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.ex.SignInByUserNameTo;

/**
 * 用户登录API
 * 
 * Validated注解按规范应该写在接口上
 *
 * Valid注解在参数是POJO类时，写在参数类型的前面，具体约束的注解写在参数类型的属性的上方
 * Valid注解在参数是普通参数时，写在方法的上方，具体约束的注解直接写在参数类型的前面
 */
@Validated
public interface RacSignInApi {

    /**
     * 通过用户名称登录
     */
    Ro<SignUpOrInRa> signInByUserName(@Valid SignInByUserNameTo to);

}
