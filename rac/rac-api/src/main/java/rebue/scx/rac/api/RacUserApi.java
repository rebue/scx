package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacUserMo;
import rebue.scx.rac.ra.GetCurUserInfoRa;
import rebue.scx.rac.to.RacUserAddTo;
import rebue.scx.rac.to.RacUserModifyTo;
import rebue.scx.rac.to.RacUserPageTo;

/**
 * 用户API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacUserApi extends BaseApi<java.lang.Long, RacUserAddTo, RacUserModifyTo, RacUserPageTo, RacUserMo> {

    /**
     * 获取当前用户信息
     *
     * @param curUserId 当前用户ID
     * @param sysId     系统ID
     *
     * @return 当前用户信息
     */
    Ro<GetCurUserInfoRa> getCurUserInfo(Long curUserId, String sysId);
}
