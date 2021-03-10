package rebue.scx.rac.svc;

import org.springframework.validation.annotation.Validated;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacAccountJo;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.GetCurAccountInfoRa;
import rebue.scx.rac.to.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 账户服务接口
 *
 * Validated注解按规范应该写在接口上
 *
 * Valid注解在参数是POJO类时，写在参数类型的前面，具体约束的注解写在参数类型的属性的上方
 * Valid注解在参数是普通参数时，写在方法的上方，具体约束的注解直接写在参数类型的前面
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Validated
public interface RacAccountSvc
    extends BaseSvc<java.lang.Long, RacAccountAddTo, RacAccountModifyTo, RacAccountDelTo, RacAccountOneTo, RacAccountListTo, RacAccountPageTo, RacAccountMo, RacAccountJo> {

    /**
     * 通过email获取账户信息
     *
     * @param domainId 领域ID
     * @param email    电子邮箱
     *
     * @return 账户信息
     */
    @Valid
    RacAccountMo getOneByEmail(@NotBlank String domainId, @NotBlank String email);

    /**
     * 通过手机号获取账户信息
     *
     * @param domainId 领域ID
     * @param mobile   手机号
     *
     * @return 账户信息
     */
    @Valid
    RacAccountMo getOneByMobile(@NotBlank String domainId, @NotBlank String mobile);

    /**
     * 通过登录名称获取账户信息
     *
     * @param domainId   领域ID
     * @param signInName 登录名称
     *
     * @return 账户信息
     */
    @Valid
    RacAccountMo getOneBySignInName(@NotBlank String domainId, @NotBlank String signInName);

    /**
     * 获取当前账户信息
     *
     * @param curAccountId 当前账户ID
     * @param sysId        系统ID
     *
     * @return 当前账户信息
     */
    @Valid
    Ro<GetCurAccountInfoRa> getCurAccountInfo(@NotNull Long curAccountId, @NotBlank String sysId);
}
