package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacPermUrnMo;
import rebue.scx.rac.to.RacPermUrnAddTo;
import rebue.scx.rac.to.RacPermUrnModifyTo;
import rebue.scx.rac.to.RacPermUrnPageTo;

/**
 * 权限URNAPI
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacPermUrnApi
		extends BaseApi<java.lang.Long, RacPermUrnAddTo, RacPermUrnModifyTo, RacPermUrnPageTo, RacPermUrnMo> {

	/**
	 * 获取账户的链接列表
	 *
	 * @param accountId 账户ID
	 *
	 * @return 指定账户的链接列表
	 */
	Ro<ListRa<String>> getUrnsOfAccount(Long accountId);

	/**
	 * 添加修改URN
	 */
	Ro<?> modifyByPermId(RacPermUrnAddTo to);

}
