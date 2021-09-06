package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.to.RacAppAddTo;
import rebue.scx.rac.to.RacAppEnabledTo;
import rebue.scx.rac.to.RacAppListTo;
import rebue.scx.rac.to.RacAppModifyTo;
import rebue.scx.rac.to.RacAppPageTo;

/**
 * 应用的API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacAppApi extends BaseApi<java.lang.String, RacAppAddTo, RacAppModifyTo, RacAppPageTo, RacAppMo> {

    Ro<ListRa<RacAppMo>> list(final RacAppListTo qo);

    Ro<ListRa<RacAppMo>> listOrderBySeqNo(final RacAppListTo qo);

    /**
     * 是否启用应用
     *
     * @param to 修改的具体数据
     */
    Ro<?> enable(RacAppEnabledTo to);

    /**
     * 上移
     */
    Ro<?> moveUp(RacAppModifyTo qo);

    /**
     * 下移
     */
    Ro<?> moveDown(RacAppModifyTo qo);
}
