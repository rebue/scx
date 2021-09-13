package rebue.scx.orp.api;

import java.util.Optional;

import rebue.scx.orp.mo.OapAppMo;
import rebue.scx.orp.mo.ex.OapAppListAndRacAppListRa;
import rebue.scx.orp.to.OapAppAddTo;
import rebue.scx.orp.to.OapAppListTo;
import rebue.scx.orp.to.OapAppModifyTo;
import rebue.scx.orp.to.OapAppPageTo;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ro.Ro;

/**
 * 第三方应用的API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface OapAppApi extends BaseApi<java.lang.Long, OapAppAddTo, OapAppModifyTo, OapAppPageTo, OapAppMo> {

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
     * 
     */
    Ro<OapAppListAndRacAppListRa> listAndTripartite(OapAppListTo qo);
}
