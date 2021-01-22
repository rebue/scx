package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.GetCurAccountInfoRa;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.RacAccountPageTo;

/**
 * 账户API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacAccountApi extends BaseApi<java.lang.Long, RacAccountAddTo, RacAccountModifyTo, RacAccountPageTo, RacAccountMo> {

    /**
     * 获取当前账户信息
     *
     * @param curAccountId 当前账户ID
     * @param sysId     系统ID
     *
     * @return 当前账户信息
     */
    Ro<GetCurAccountInfoRa> getCurAccountInfo(Long curAccountId, String sysId);
}
