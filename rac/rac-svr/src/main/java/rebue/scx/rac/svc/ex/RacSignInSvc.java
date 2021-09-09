package rebue.scx.rac.svc.ex;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.github.pagehelper.PageInfo;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.scx.rac.to.UnifiedLoginTo;
import rebue.scx.rac.to.ex.SignInByAccountNameTo;

/**
 * 账户登录服务接口
 *
 * <pre>
 * 1. 在接口上方必须写上 @Validated 注解
 * 2. 参数是POJO类时用 @Valid 注解在参数类型的前面进行修饰
 *    参数是普通参数时，直接在参数类型的前面加上具体约束的注解
 * 3. (待验证)有分组时，在方法上方必须写上 @Validated 注解及分组
 * 4. 踩坑留痕：
 *    如果方法的返回值为void，在方法上方加上 @Valid 注解会出现异常，报HV000132错误
 * </pre>
 */
@Validated
public interface RacSignInSvc {

    Optional<RacAccountMo> unifiedLogin(UnifiedLoginTo to);

    /**
     * 通过账户名称注册
     */
    Ro<SignUpOrInRa> signInByAccountName(@Valid SignInByAccountNameTo to);

    /**
     * 获取输入密码错误而被锁定的账户记录
     * 
     * @return
     */
    PageInfo<RacAccountMo> getSignInLockRecord(RacAccountPageTo qo);

    /**
     * 手动删除输入登录密码错误次数
     */
    Boolean handDelWrongPswdTimesOfSignIn(Long accountId);

}
