package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacDomainMo;
import rebue.scx.rac.to.RacDomainAddTo;
import rebue.scx.rac.to.RacDomainModifyTo;
import rebue.scx.rac.to.RacDomainPageTo;

/**
 * 领域API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacDomainApi extends BaseApi<java.lang.String, RacDomainAddTo, RacDomainModifyTo, RacDomainPageTo, RacDomainMo> {

    /**
     * 查询所有记录
     */
    Ro<ListRa<RacDomainMo>> listAll();
}
