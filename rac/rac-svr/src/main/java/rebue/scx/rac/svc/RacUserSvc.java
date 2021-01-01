package rebue.scx.rac.svc;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacUserJo;
import rebue.scx.rac.mo.RacUserMo;
import rebue.scx.rac.ra.GetCurUserInfoRa;
import rebue.scx.rac.to.*;

/**
 * 用户服务接口
 *
 * Validated注解按规范应该写在接口上
 *
 * Valid注解在参数是POJO类时，写在参数类型的前面，具体约束的注解写在参数类型的属性的上方
 * Valid注解在参数是普通参数时，写在方法的上方，具体约束的注解直接写在参数类型的前面
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Validated
public interface RacUserSvc extends BaseSvc<java.lang.Long, RacUserAddTo, RacUserModifyTo, RacUserDelTo, RacUserOneTo, RacUserListTo, RacUserPageTo, RacUserMo, RacUserJo> {

    /**
     * 通过email获取用户信息
     *
     * @param domainId 领域ID
     * @param orgId    组织ID
     * @param email    电子邮箱
     *
     * @return 用户信息
     */
    @Valid
    RacUserMo getOneByEmail(@NotBlank String domainId, Long orgId, @NotBlank String email);

    /**
     * 通过手机号获取用户信息
     *
     * @param domainId 领域ID
     * @param orgId    组织ID
     * @param mobile   手机号
     *
     * @return 用户信息
     */
    @Valid
    RacUserMo getOneByMobile(@NotBlank String domainId, Long orgId, @NotBlank String mobile);

    /**
     * 通过登录名称获取用户信息
     *
     * @param domainId   领域ID
     * @param orgId      组织ID
     * @param signInName 登录名称
     *
     * @return 用户信息
     */
    @Valid
    RacUserMo getOneBySignInName(@NotBlank String domainId, Long orgId, @NotBlank String signInName);

    /**
     * 获取当前用户信息
     * 
     * @param curUserId 当前用户ID
     * @param sysId     系统ID
     * 
     * @return 当前用户信息
     */
    @Valid
    Ro<GetCurUserInfoRa> getCurUserInfo(@NotNull Long curUserId, @NotBlank String sysId);
}
