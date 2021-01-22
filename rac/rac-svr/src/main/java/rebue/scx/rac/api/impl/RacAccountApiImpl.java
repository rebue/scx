package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacAccountApi;
import rebue.scx.rac.jo.RacAccountJo;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.GetCurAccountInfoRa;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.RacAccountDelTo;
import rebue.scx.rac.to.RacAccountListTo;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.RacAccountOneTo;
import rebue.scx.rac.to.RacAccountPageTo;

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
     * 获取当前账户信息
     *
     * @param curAccountId 当前账户ID
     * @param sysId     系统ID
     *
     * @return 当前账户信息
     */
    @Override
    public Ro<GetCurAccountInfoRa> getCurAccountInfo(final Long curAccountId, final String sysId) {
        return _svc.getCurAccountInfo(curAccountId, sysId);
    }
}
