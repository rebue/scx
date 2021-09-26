package rebue.scx.rac.api;

import java.io.InputStream;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.GetCurAccountInfoRa;
import rebue.scx.rac.ra.ListTransferOfOrgRa;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.RacAccountModifySignInByOldPswdTo;
import rebue.scx.rac.to.RacAccountModifySignInPswdTo;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.scx.rac.to.RacDisableLogAddTo;
import rebue.scx.rac.to.RacDisableLogModifyTo;
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
    Ro<?> uploadAvatar(Long accountId, String fileName, String contentDisposition, String contentType, InputStream inputStream);

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
}
