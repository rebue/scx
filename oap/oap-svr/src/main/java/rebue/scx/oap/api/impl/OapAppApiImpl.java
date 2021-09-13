package rebue.scx.oap.api.impl;

import java.util.Optional;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.scx.oap.api.OapAppApi;
import rebue.scx.oap.jo.OapAppJo;
import rebue.scx.oap.mo.OapAppMo;
import rebue.scx.oap.mo.ex.OapAppListAndRacAppListRa;
import rebue.scx.oap.svc.OapAppSvc;
import rebue.scx.oap.to.OapAppAddTo;
import rebue.scx.oap.to.OapAppDelTo;
import rebue.scx.oap.to.OapAppListTo;
import rebue.scx.oap.to.OapAppModifyTo;
import rebue.scx.oap.to.OapAppOneTo;
import rebue.scx.oap.to.OapAppPageTo;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.ro.Ro;

/**
 * 第三方应用API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class OapAppApiImpl extends BaseApiImpl<java.lang.Long, OapAppAddTo, OapAppModifyTo, OapAppDelTo, OapAppOneTo, OapAppListTo, OapAppPageTo, OapAppMo, OapAppJo, OapAppSvc>
        implements OapAppApi {
    /**
     * 请添加文档注释
     */
    @Override
    public Optional<OapAppMo> selectOneByClientId(String clientId) {
        OapAppOneTo oneTo = new OapAppOneTo();
        oneTo.setClientId(clientId);
        return Optional.ofNullable(_svc.getOne(oneTo));
    }

    /**
     * 获取单个第三方应用的信息
     *
     * @param id 通过rac_app的ID关联查询
     */
    @Override
    public Ro<?> getByAppId(String id) {
        return _svc.getByAppId(id);
    }

    /**
     * 查询应用的信息并附带第三方应用的信息
     *
     * @param qo 查询的具体条件(查询所有，及条件为空)
     * 
     */
    @Override
    public Ro<OapAppListAndRacAppListRa> listAndTripartite(OapAppListTo qo) {
        return _svc.listAndTripartite(qo);
    }

}
