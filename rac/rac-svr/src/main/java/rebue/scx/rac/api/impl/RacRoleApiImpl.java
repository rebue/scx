package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacRoleApi;
import rebue.scx.rac.jo.RacRoleJo;
import rebue.scx.rac.mo.RacRoleMo;
import rebue.scx.rac.svc.RacRoleSvc;
import rebue.scx.rac.to.RacRoleAddTo;
import rebue.scx.rac.to.RacRoleDelTo;
import rebue.scx.rac.to.RacRoleListTo;
import rebue.scx.rac.to.RacRoleModifyTo;
import rebue.scx.rac.to.RacRoleOneTo;
import rebue.scx.rac.to.RacRolePageTo;

/**
 * 角色API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacRoleApiImpl extends
    BaseApiImpl<java.lang.Long, RacRoleAddTo, RacRoleModifyTo, RacRoleDelTo, RacRoleOneTo, RacRoleListTo, RacRolePageTo, RacRoleMo, RacRoleJo, RacRoleSvc> implements RacRoleApi {

    /**
     * 查询角色
     */
    @Override
    public Ro<ListRa<RacRoleMo>> list(RacRoleListTo qo) {
        return new Ro<>(ResultDic.SUCCESS, "查询成功", new ListRa<>(_svc.list(qo)));
    }

    /**
     * 上移动
     */
    @Override
    public Ro<?> moveUp(RacRoleModifyTo qo) {
        _svc.moveUp(qo);
        return new Ro<>(ResultDic.SUCCESS, "上移成功");
    }

    /**
     * 下移动
     */
    @Override
    public Ro<?> moveDown(RacRoleModifyTo qo) {
        _svc.moveDown(qo);
        return new Ro<>(ResultDic.SUCCESS, "下移成功");
    }

    /**
     * 启用角色
     */
    @Override
    public Ro<?> enable(RacRoleModifyTo qo) {
        _svc.enable(qo);
        return new Ro<>(ResultDic.SUCCESS, "启用角色成功");
    }

    /**
     * 禁用角色
     */
    @Override
    public Ro<?> disable(RacRoleModifyTo qo) {
        _svc.disable(qo);
        return new Ro<>(ResultDic.SUCCESS, "禁用角色成功");
    }
}
