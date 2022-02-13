package rebue.scx.rac.api;

import java.util.List;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.mo.RacAppTagMo;
import rebue.scx.rac.to.RacAppAddTo;
import rebue.scx.rac.to.RacAppEnabledTo;
import rebue.scx.rac.to.RacAppListTo;
import rebue.scx.rac.to.RacAppModifyTo;
import rebue.scx.rac.to.RacAppOneTo;
import rebue.scx.rac.to.RacAppPageTo;

/**
 * 应用的API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacAppApi extends BaseApi<java.lang.String, RacAppAddTo, RacAppModifyTo, RacAppOneTo, RacAppPageTo, RacAppMo> {

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

    /**
     * 根据帐号ID查询他可以看到的应用
     *
     * @param accountIds 账户ID集合
     *
     * @return
     */
    Ro<ListRa<RacAppMo>> selectAppByAccountIds(List<Long> accountIds);

    /**
     * 根据应用ID查询对应的标签信息
     */
    Ro<ListRa<RacAppTagMo>> listInAppIdList(List<String> appIds);

    /**
     * 通过ID修改记录内容(oap服务调用修改)
     *
     * @param to 修改的参数，必须包含ID
     *
     * @return 如果成功，且仅修改一条记录，正常返回，否则会抛出运行时异常
     */
    Ro<?> oapModifyById(RacAppModifyTo qo);
}
