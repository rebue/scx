package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacRoleMo;
import rebue.scx.rac.to.RacRoleAddTo;
import rebue.scx.rac.to.RacRoleListTo;
import rebue.scx.rac.to.RacRoleModifyTo;
import rebue.scx.rac.to.RacRolePageTo;

/**
 * 角色API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacRoleApi extends BaseApi<java.lang.Long, RacRoleAddTo, RacRoleModifyTo, RacRolePageTo, RacRoleMo> {
	 Ro<ListRa<RacRoleMo>> list(RacRoleListTo qo);
}
