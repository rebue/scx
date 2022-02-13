package rebue.scx.oap.api;

import java.util.Optional;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ro.Ro;
import rebue.scx.oap.mo.OapAppMo;
import rebue.scx.oap.mo.ex.OapAppListAndRacAppListRa;
import rebue.scx.oap.to.OapAppAddTo;
import rebue.scx.oap.to.OapAppListTo;
import rebue.scx.oap.to.OapAppModifyTo;
import rebue.scx.oap.to.OapAppOneTo;
import rebue.scx.oap.to.OapAppPageTo;

/**
 * 第三方应用的API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface OapAppApi extends BaseApi<java.lang.Long, OapAppAddTo, OapAppModifyTo, OapAppOneTo, OapAppPageTo, OapAppMo> {

    Optional<OapAppMo> selectOneByClientId(String clientId);

    /**
     * 获取单个第三方应用的信息
     *
     * @param id 通过rac_app的ID关联查询
     */
    Ro<?> getByAppId(String id);

    /**
     * 查询应用的信息并附带第三方应用的信息
     *
     * @param qo 查询的具体条件(查询所有，及条件为空)
     */
    Ro<OapAppListAndRacAppListRa> listAndTripartite(OapAppListTo qo);
}
