package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacOrgApi;
import rebue.scx.rac.jo.RacOrgJo;
import rebue.scx.rac.mo.RacOrgMo;
import rebue.scx.rac.svc.RacOrgSvc;
import rebue.scx.rac.to.RacOrgAccountAddTo;
import rebue.scx.rac.to.RacOrgAccountDelTo;
import rebue.scx.rac.to.RacOrgAddTo;
import rebue.scx.rac.to.RacOrgDelTo;
import rebue.scx.rac.to.RacOrgListTo;
import rebue.scx.rac.to.RacOrgModifyTo;
import rebue.scx.rac.to.RacOrgOneTo;
import rebue.scx.rac.to.RacOrgPageTo;

/**
 * 组织API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacOrgApiImpl extends
		BaseApiImpl<java.lang.Long, RacOrgAddTo, RacOrgModifyTo, RacOrgDelTo, RacOrgOneTo, RacOrgListTo, RacOrgPageTo, RacOrgMo, RacOrgJo, RacOrgSvc>
		implements RacOrgApi {

	/**
	 * 添加组织账户关系
	 *
	 * @param to 添加的具体信息
	 */
	@Override
	public Ro<?> addOrgAccount(RacOrgAccountAddTo to) {
		_svc.addOrgAccount(to);
		return new Ro<>(ResultDic.SUCCESS, "添加成功");
	}

	/**
	 * 删除组织账户关系
	 *
	 * @param to 添加的具体信息
	 */
	@Override
	public Ro<?> delOrgAccount(RacOrgAccountDelTo to) {
		_svc.delOrgAccount(to);
		return new Ro<>(ResultDic.SUCCESS, "移除成功");
	}

	/**
	 * 查询组织的信息
	 *
	 * @param qo 查询的具体条件
	 */
	@Override
	public Ro<ListRa<RacOrgMo>> list(RacOrgListTo qo) {
		return new Ro<>(ResultDic.SUCCESS, "查询成功", new ListRa<>(_svc.list(qo)));
	}

}
