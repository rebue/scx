package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacPermCommandMo;
import rebue.scx.rac.to.RacPermCommandAddTo;
import rebue.scx.rac.to.RacPermCommandListTo;
import rebue.scx.rac.to.RacPermCommandModifyTo;
import rebue.scx.rac.to.RacPermCommandPageTo;

/**
 * 权限命令的API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacPermCommandApi extends BaseApi<java.lang.Long, RacPermCommandAddTo, RacPermCommandModifyTo, RacPermCommandPageTo, RacPermCommandMo> {

    /**
     * 查询权限命令的信息
     *
     * @param qo 查询的具体条件
     */
    Ro<ListRa<RacPermCommandMo>> list(RacPermCommandListTo qo);
}
