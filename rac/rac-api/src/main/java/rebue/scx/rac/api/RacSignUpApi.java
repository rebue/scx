package rebue.scx.rac.api;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.ra.SignUpRa;
import rebue.scx.rac.to.SignUpByUserNameTo;

/**
 * API用户注册服务
 */
//在接口上方必须写上 @Validated 注解；
//参数是POJO类时用 @Validated 注解在参数类型的前面进行修饰；
//而如果是普通参数，则在方法的上方写上 @Validated 注解，具体约束的注解直接写在参数类型的前面
@Validated
public interface RacSignUpApi {

    /**
     * 通过用户名称注册
     */
    Ro<SignUpRa> signUpByUserName(@Validated SignUpByUserNameTo to);

}
