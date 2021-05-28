package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacOrgMo;
import rebue.scx.rac.to.RacOrgAccountAddTo;
import rebue.scx.rac.to.RacOrgAccountDelTo;
import rebue.scx.rac.to.RacOrgAddTo;
import rebue.scx.rac.to.RacOrgListTo;
import rebue.scx.rac.to.RacOrgModifyTo;
import rebue.scx.rac.to.RacOrgPageTo;
import rebue.scx.rac.to.ex.RacModifyOrgAccountTo;
import rebue.scx.rac.to.ex.RacOrgListByAccountIdTo;
import rebue.scx.rac.to.ex.RacOrgModifyDefaultOrgTo;

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

	/**
	 * 查询当前账户所在的组织的信息
	 *
	 * @param qo 查询的具体条件
	 */
	Ro<ListRa<RacOrgMo>> listByAccountId(RacOrgListByAccountIdTo qo);

	/**
	 * 修改账户默认组织的信息
	 *
	 * @param to 修改的具体数据
	 */
	Ro<?> modifyDefaultOrg(RacOrgModifyDefaultOrgTo to);

	/**
	 * 查询可以添加的组织信息
	 *
	 * @param qo 查询的具体条件
	 */
	Ro<PageRa<RacOrgMo>> listAddableOrg(RacOrgListByAccountIdTo qo);
	
	/**
	 * 更改组织与账户的关系
	 *
	 * @param to 修改的具体数据
	 */
	Ro<?> modifyOrgAccount(RacModifyOrgAccountTo to);

}
