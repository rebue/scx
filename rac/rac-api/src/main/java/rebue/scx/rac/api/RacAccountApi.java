package rebue.scx.rac.api;

import java.io.InputStream;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.GetCurAccountInfoRa;
import rebue.scx.rac.ra.ListTransferOfOrgRa;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.RacAccountModifySignInByOldPswdTo;
import rebue.scx.rac.to.RacAccountModifySignInPswdTo;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.RacAccountOneTo;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.scx.rac.to.RacDisableLogAddTo;
import rebue.scx.rac.to.RacDisableLogModifyTo;
import rebue.scx.rac.to.ex.RacAccountByUserTo;
import rebue.scx.rac.to.ex.RacAccountMobileTo;
import rebue.scx.rac.to.ex.RacAccountResetPasswordTo;
import rebue.scx.rac.to.ex.RacAccountUnionIdTo;
import rebue.scx.rac.to.ex.RacListTransferOfOrgTo;

/**
 * 账户的API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacAccountApi extends BaseApi<java.lang.Long, RacAccountAddTo, RacAccountModifyTo, RacAccountPageTo, RacAccountMo> {

    /**
     * 修改账户登录密码
     *
     * @param to 修改账户登录密码的具体数据
     */
    Ro<?> modifySignInPswd(RacAccountModifySignInPswdTo to);

    /**
     * 启用账户
     *
     * @param to 启用的具体数据
     */
    Ro<?> enable(RacDisableLogModifyTo to);

    /**
     * 禁用账户
     *
     * @param qo 禁用的具体数据
     */
    Ro<?> disable(RacDisableLogAddTo qo);

    /**
     * 上传头像
     */
    Ro<?> uploadAvatar(Long accountId, String appId, String fileName, String contentDisposition, String contentType, InputStream inputStream);

    /**
     * 获取当前账户信息
     *
     * @param curAccountId 当前账户ID
     * @param appId        应用ID
     *
     * @return 当前账户信息
     */
    Ro<GetCurAccountInfoRa> getCurAccountInfo(Long curAccountId, Long agentAccountId, String appId);

    /**
     * 查询账户的信息
     *
     * @param qo 查询的具体条件
     */
    Ro<ListTransferOfOrgRa> listTransferOfOrg(RacListTransferOfOrgTo qo);

    /**
     * 解除绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     */
    Ro<?> unbindModify(RacAccountModifyTo to);

    /**
     * 根据账户ID绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     */
    Ro<?> bindModify(RacAccountModifyTo to);

    /**
     * 根据旧登录密码更新新登录密码
     *
     * @param to 修改账户登录密码的具体数据
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

    /**
     * 重置账户登录密码
     *
     * @param to 修改账户登录密码的具体数据
     */
    Ro<?> resetPassword(RacAccountResetPasswordTo to);

    RacAccountMo getOne(RacAccountOneTo oneTo);

    /**
     * 根据账户ID领域ID关键字查询该领域下账户(用户的下帐号)的信息
     *
     * @param to 查询的具体条件
     */
    Ro<PageRa<RacAccountMo>> getAccountByUser(RacAccountByUserTo to);

    /**
     * 添加账户unionId映射
     *
     * @param to 添加的具体信息
     */
    Ro<?> addUnionIdMapper(RacAccountUnionIdTo to);

    /**
     * 删除账户unionId映射
     *
     * @param to 删除的具体信息
     */
    Ro<?> delUnionIdMapper(RacAccountUnionIdTo to);

    /**
     * 通过unionId查询账户
     *
     * @param unionId
     *
     * @return
     */
    Ro<ListRa<RacAccountMo>> getAccountByUnionId(Long unionId);

    /**
     * 管理员解除账户绑定钉钉
     *
     * @param id 被解绑的账户ID
     */
    Ro<?> unbindDdModify(Long id);

    /**
     * 管理员解除账户绑定微信
     *
     * @param id 被解绑的账户ID
     */
    Ro<?> unbindWxModify(Long id);

    /**
     * 账户解除关联用户
     *
     * @param id 需要解除的账户ID
     */
    Ro<?> disassociateUser(Long id);

    /**
     * 账户绑定手机号
     *
     * @param to 账户ID/手机号/校验码
     */
    Ro<?> bindMobile(RacAccountMobileTo to);

    /**
     * 判断手机号是否已被绑定注册
     *
     * @param id     账户ID
     * @param mobile 手机号
     */
    Ro<BooleanRa> existMobileById(Long id, int mobile);

    /**
     * 管理员解除账户绑定手机号
     *
     * @param id 被解绑的账户ID
     */
    Ro<?> unbindMobile(Long id);

    RacAccountMo getAccountMoById(Long id);

    Ro<ListRa<RacAccountMo>> listAll();
}
