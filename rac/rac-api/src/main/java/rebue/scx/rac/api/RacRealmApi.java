package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacRealmMo;
import rebue.scx.rac.to.RacRealmAddTo;
import rebue.scx.rac.to.RacRealmModifyTo;
import rebue.scx.rac.to.RacRealmPageTo;

/**
 * 领域的API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacRealmApi extends BaseApi<java.lang.String, RacRealmAddTo, RacRealmModifyTo, RacRealmPageTo, RacRealmMo> {

    /**
     * 查询所有记录
     */
    Ro<ListRa<RacRealmMo>> listAll();
}