package rebue.scx.rac.svc;

import java.io.InputStream;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacAccountJo;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.GetCurAccountInfoRa;
import rebue.scx.rac.ra.ListTransferOfOrgRa;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.RacAccountDelTo;
import rebue.scx.rac.to.RacAccountListTo;
import rebue.scx.rac.to.RacAccountModifySignInByOldPswdTo;
import rebue.scx.rac.to.RacAccountModifySignInPswdTo;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.RacAccountOneTo;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.scx.rac.to.RacDisableLogAddTo;
import rebue.scx.rac.to.RacDisableLogModifyTo;
import rebue.scx.rac.to.ex.RacListTransferOfOrgTo;

/**
 * 账户服务接口
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
public interface RacAccountSvc
    extends BaseSvc<java.lang.Long, RacAccountAddTo, RacAccountModifyTo, RacAccountDelTo, RacAccountOneTo, RacAccountListTo, RacAccountPageTo, RacAccountMo, RacAccountJo> {

    /**
     * 修改账户登录密码
     *
     * @param to 修改账户登录密码的具体数据
     */
    void modifySignInPswd(@Valid RacAccountModifySignInPswdTo to);

    /**
     * 启用账户
     *
     * @param to 启用的具体数据
     */
    void enable(@Valid RacDisableLogModifyTo to);

    /**
     * 禁用账户
     *
     * @param to 禁用的具体数据
     */
    void disable(RacDisableLogAddTo to);

    /**
     * 上传头像
     */
    Ro<?> uploadAvatar(Long accountId, String fileName, String contentDisposition, String contentType, InputStream inputStream);

    /**
     * 通过email获取账户信息
     *
     * @param realmId 领域ID
     * @param email   电子邮箱
     *
     * @return 账户信息
     */
    RacAccountMo getOneByEmail(@NotBlank String realmId, @NotBlank String email);

    /**
     * 通过手机号获取账户信息
     *
     * @param realmId 领域ID
     * @param mobile  手机号
     *
     * @return 账户信息
     */
    RacAccountMo getOneByMobile(@NotBlank String realmId, @NotBlank String mobile);

    /**
     * 通过登录名称获取账户信息
     *
     * @param realmId    领域ID
     * @param signInName 登录名称
     *
     * @return 账户信息
     */
    RacAccountMo getOneBySignInName(@NotBlank String realmId, @NotBlank String signInName);

    /**
     * 获取当前账户信息
     *
     * @param curAccountId   当前账户ID
     * @param agentAccountId 代理账户ID
     * @param appId          应用ID
     *
     * @return 当前账户信息
     */
    Ro<GetCurAccountInfoRa> getCurAccountInfo(@NotNull Long curAccountId, Long agentAccountId, @NotBlank String appId);

    /**
     * 查询账户的信息
     *
     * @param qo 查询的具体条件
     */
    Ro<ListTransferOfOrgRa> listTransferOfOrg(RacListTransferOfOrgTo qo);

    /**
     * 根据账户ID绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     */
    void bindModify(RacAccountModifyTo to);

    /**
     * 解除绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     */
    void unbindModify(RacAccountModifyTo to);

    /**
     * 根据旧登录密码更新新登录密码
     *
     * @param to 修改账户登录密码的具体数据
     *
     * @return
     */
    Ro<?> modifySignInByOldPswd(RacAccountModifySignInByOldPswdTo to);

    /**
     * 根据用户ID查询用户下的账户的信息
     *
     * @param id
     *
     * @return
     */
    Ro<ListRa<RacAccountMo>> getByUserId(Long id);
}
