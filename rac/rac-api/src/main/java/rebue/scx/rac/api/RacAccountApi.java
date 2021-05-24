package rebue.scx.rac.api;

import java.io.InputStream;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.GetCurAccountInfoRa;
import rebue.scx.rac.ra.ListTransferOfOrgRa;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.RacAccountDisableTo;
import rebue.scx.rac.to.RacAccountEnableTo;
import rebue.scx.rac.to.RacAccountModifySignInPswdTo;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.RacAccountPageTo;

/**
 * 账户API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacAccountApi
    extends BaseApi<java.lang.Long, RacAccountAddTo, RacAccountModifyTo, RacAccountPageTo, RacAccountMo> {

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
    Ro<?> enable(RacAccountEnableTo to);

    /**
     * 禁用账户
     *
     * @param to 禁用的具体数据
     */
    Ro<?> disable(RacAccountDisableTo to);

    /**
     * 上传头像
     */
    Ro<?> uploadAvatar(InputStream inputStream);

    /**
     * 获取当前账户信息
     *
     * @param curAccountId 当前账户ID
     * @param sysId        系统ID
     *
     * @return 当前账户信息
     */
    Ro<GetCurAccountInfoRa> getCurAccountInfo(Long curAccountId, String sysId);

    /**
     * 查询账户的信息
     *
     * @param qo 查询的具体条件
     *
     */
    Ro<ListTransferOfOrgRa> listTransferOfOrg(RacAccountPageTo qo);
}
