package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacOrgMo;
import rebue.scx.rac.to.RacOrgAccountAddTo;
import rebue.scx.rac.to.RacOrgAccountDelTo;
import rebue.scx.rac.to.RacOrgAddTo;
import rebue.scx.rac.to.RacOrgListTo;
import rebue.scx.rac.to.RacOrgModifyTo;
import rebue.scx.rac.to.RacOrgPageTo;

/**
 * 组织API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacOrgApi extends BaseApi<java.lang.Long, RacOrgAddTo, RacOrgModifyTo, RacOrgPageTo, RacOrgMo> {

	/**
	 * 添加组织账户关系
	 *
	 * @param to 添加的具体信息
	 */
	Ro<?> addOrgAccount(RacOrgAccountAddTo to);

	/**
	 * 删除组织账户关系
	 *
	 * @param to 添加的具体信息
	 */
	Ro<?> delOrgAccount(RacOrgAccountDelTo to);

	/**
	 * 查询组织的信息
	 *
	 * @param qo 查询的具体条件
	 */
	Ro<ListRa<RacOrgMo>> list(RacOrgListTo qo);
}
