package rebue.scx.rac.api.impl;

import java.io.InputStream;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacAccountApi;
import rebue.scx.rac.jo.RacAccountJo;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.GetCurAccountInfoRa;
import rebue.scx.rac.ra.ListTransferOfOrgRa;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.RacAccountDelTo;
import rebue.scx.rac.to.RacAccountDisableTo;
import rebue.scx.rac.to.RacAccountEnableTo;
import rebue.scx.rac.to.RacAccountListTo;
import rebue.scx.rac.to.RacAccountModifySignInPswdTo;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.RacAccountOneTo;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.scx.rac.to.ex.RacListTransferOfOrgTo;

/**
 * 账户API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacAccountApiImpl extends
    BaseApiImpl<java.lang.Long, RacAccountAddTo, RacAccountModifyTo, RacAccountDelTo, RacAccountOneTo, RacAccountListTo, RacAccountPageTo, RacAccountMo, RacAccountJo, RacAccountSvc>
    implements RacAccountApi {

    /**
     * 修改账户登录密码
     *
     * @param to 修改账户登录密码的具体数据
     */
    @Override
    public Ro<?> modifySignInPswd(final RacAccountModifySignInPswdTo to) {
        _svc.modifySignInPswd(to);
        return new Ro<>(ResultDic.SUCCESS, "修改成功");
    }

    /**
     * 启用账户
     *
     * @param to 启用的具体数据
     */
    @Override
    public Ro<?> enable(final RacAccountEnableTo to) {
        _svc.enable(to);
        return new Ro<>(ResultDic.SUCCESS, "启用账户成功");
    }

    /**
     * 禁用账户
     *
     * @param to 禁用的具体数据
     */
    @Override
    public Ro<?> disable(final RacAccountDisableTo to) {
        _svc.disable(to);
        return new Ro<>(ResultDic.SUCCESS, "禁用账户成功");
    }

    /**
     * 上传头像
     */
    @Override
    public Ro<?> uploadAvatar(final Long accountId, final String fileName, final ContentDisposition contentDisposition, final MediaType contentType,
        final InputStream inputStream) {
        return _svc.uploadAvatar(accountId, fileName, contentDisposition, contentType, inputStream);
    }

    /**
     * 查询账户的信息
     *
     * @param qo 查询的具体条件
     */
    @Override
    public Ro<ListTransferOfOrgRa> listTransferOfOrg(final RacListTransferOfOrgTo qo) {
        return _svc.listTransferOfOrg(qo);
    }

    /**
     * 获取当前账户信息
     *
     * @param curAccountId   当前账户ID
     * @param agentAccountId 代理账户ID
     * @param sysId          系统ID
     *
     * @return 当前账户信息
     */
    @Override
    public Ro<GetCurAccountInfoRa> getCurAccountInfo(final Long curAccountId, final Long agentAccountId, final String sysId) {
        return _svc.getCurAccountInfo(curAccountId, agentAccountId, sysId);
    }
}
