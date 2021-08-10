package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacSysMo;
import rebue.scx.rac.to.RacSysAddTo;
import rebue.scx.rac.to.RacSysListTo;
import rebue.scx.rac.to.RacSysModifyTo;
import rebue.scx.rac.to.RacSysPageTo;

/**
 * 系统的API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacSysApi extends BaseApi<java.lang.String, RacSysAddTo, RacSysModifyTo, RacSysPageTo, RacSysMo> {

    Ro<ListRa<RacSysMo>> list(final RacSysListTo qo);
}
