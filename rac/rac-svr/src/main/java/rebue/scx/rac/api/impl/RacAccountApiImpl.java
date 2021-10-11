package rebue.scx.rac.api.impl;

import java.io.InputStream;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacAccountApi;
import rebue.scx.rac.jo.RacAccountJo;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.GetCurAccountInfoRa;
import rebue.scx.rac.ra.ListTransferOfOrgRa;
import rebue.scx.rac.svc.RacAccountSvc;
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
import rebue.scx.rac.to.ex.RacAccountResetPasswordTo;
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
     * 重置账户登录密码
     *
     * @param to 修改账户登录密码的具体数据
     */
    @Override
    public Ro<?> resetPassword(RacAccountResetPasswordTo to) {
        _svc.resetPassword(to);
        return new Ro<>(ResultDic.SUCCESS, "重置成功");
    }

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
     * 根据旧登录密码更新新登录密码
     *
     * @param to 修改账户登录密码的具体数据
     */
    @Override
    public Ro<?> modifySignInByOldPswd(RacAccountModifySignInByOldPswdTo to) {
        return _svc.modifySignInByOldPswd(to);
    }

    /**
     * 根据账户ID绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     */
    @Override
    public Ro<?> bindModify(RacAccountModifyTo to) {
        _svc.bindModify(to);
        return new Ro<>(ResultDic.SUCCESS, "绑定成功");
    }

    /**
     * 解除绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     */
    @Override
    public Ro<?> unbindModify(RacAccountModifyTo to) {
        _svc.unbindModify(to);
        return new Ro<>(ResultDic.SUCCESS, "解除绑定成功");
    }

    /**
     * 启用账户
     *
     * @param to 启用的具体数据
     */
    @Override
    public Ro<?> enable(final RacDisableLogModifyTo to) {
        _svc.enable(to);
        return new Ro<>(ResultDic.SUCCESS, "启用账户成功");
    }

    /**
     * 禁用账户
     *
     * @param to 禁用的具体数据
     */
    @Override
    public Ro<?> disable(final RacDisableLogAddTo to) {
        _svc.disable(to);
        return new Ro<>(ResultDic.SUCCESS, "禁用账户成功");
    }

    /**
     * 上传头像
     */
    @Override
    public Ro<?> uploadAvatar(final Long accountId, final String fileName, final String contentDisposition, final String contentType, final InputStream inputStream) {
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
     * 根据用户ID查询用户下的账户的信息
     *
     * @param id
     *
     * @return
     */
    @Override
    public Ro<ListRa<RacAccountMo>> getByUserId(Long id) {
        return _svc.getByUserId(id);
    }

    @Override
    public RacAccountMo getOne(RacAccountOneTo oneTo) {
        _svc.getOne(oneTo);
        return _svc.getOne(oneTo);
    }

    /**
     * 获取当前账户信息
     *
     * @param curAccountId   当前账户ID
     * @param agentAccountId 代理账户ID
     * @param appId          应用ID
     *
     * @return 当前账户信息
     */
    @Override
    public Ro<GetCurAccountInfoRa> getCurAccountInfo(final Long curAccountId, final Long agentAccountId, final String appId, final Long unionId) {
        return _svc.getCurAccountInfo(curAccountId, agentAccountId, appId, unionId);
    }
}
