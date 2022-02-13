package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacUserMo;
import rebue.scx.rac.to.RacUserAddTo;
import rebue.scx.rac.to.RacUserModifyTo;
import rebue.scx.rac.to.RacUserOneTo;
import rebue.scx.rac.to.RacUserPageTo;

/**
 * 用户的API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacUserApi extends BaseApi<java.lang.Long, RacUserAddTo, RacUserModifyTo, RacUserOneTo, RacUserPageTo, RacUserMo> {

    /**
     * 根据姓名和身份张号查询用户信息
     *
     * @param id
     *
     * @return
     */
    Ro<RacUserMo> getOneByRealNameIdCard(RacUserOneTo to);
}
