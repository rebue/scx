package com.github.rebue.scx.api.impl;

import java.util.Optional;

import org.apache.dubbo.config.annotation.DubboService;

import com.github.rebue.scx.api.OapAppApi;
import com.github.rebue.scx.jo.OapAppJo;
import com.github.rebue.scx.mo.OapAppMo;
import com.github.rebue.scx.mo.ex.OapAppListAndRacAppListRa;
import com.github.rebue.scx.svc.OapAppSvc;
import com.github.rebue.scx.to.OapAppAddTo;
import com.github.rebue.scx.to.OapAppDelTo;
import com.github.rebue.scx.to.OapAppListTo;
import com.github.rebue.scx.to.OapAppModifyTo;
import com.github.rebue.scx.to.OapAppOneTo;
import com.github.rebue.scx.to.OapAppPageTo;

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
