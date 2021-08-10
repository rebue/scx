package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacPermApi;
import rebue.scx.rac.jo.RacPermJo;
import rebue.scx.rac.mo.RacPermMo;
import rebue.scx.rac.ra.PermListWithGroupRa;
import rebue.scx.rac.svc.RacPermSvc;
import rebue.scx.rac.to.RacPermAddTo;
import rebue.scx.rac.to.RacPermDelTo;
import rebue.scx.rac.to.RacPermListTo;
import rebue.scx.rac.to.RacPermModifyTo;
import rebue.scx.rac.to.RacPermOneTo;
import rebue.scx.rac.to.RacPermPageTo;

/**
 * 权限API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacPermApiImpl extends
    BaseApiImpl<java.lang.Long, RacPermAddTo, RacPermModifyTo, RacPermDelTo, RacPermOneTo, RacPermListTo, RacPermPageTo, RacPermMo, RacPermJo, RacPermSvc> implements RacPermApi {

    /**
     * 查询带分组的权限列表
     *
     * @param realmId 领域ID
     */
    @Override
    public Ro<PermListWithGroupRa> listWithGroup(final String realmId) {
        return _svc.listWithGroup(realmId);
    }

    /**
     * 上移动
     */
    @Override
    public Ro<?> moveUp(RacPermModifyTo qo) {
        _svc.moveUp(qo);
        return new Ro<>(ResultDic.SUCCESS, "上移成功");
    }

    /**
     * 下移动
     */
    @Override
    public Ro<?> moveDown(RacPermModifyTo qo) {
        _svc.moveDown(qo);
        return new Ro<>(ResultDic.SUCCESS, "下移成功");
    }

    /**
     * 启用权限
     */
    @Override
    public Ro<?> enable(RacPermModifyTo qo) {
        _svc.enable(qo);
        return new Ro<>(ResultDic.SUCCESS, "启用权限成功");
    }

    /**
     * 禁用权限
     */
    @Override
    public Ro<?> disable(RacPermModifyTo qo) {
        _svc.disable(qo);
        return new Ro<>(ResultDic.SUCCESS, "禁用权限成功");
    }
}
