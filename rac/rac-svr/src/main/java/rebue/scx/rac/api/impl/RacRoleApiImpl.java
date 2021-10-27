package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacRoleApi;
import rebue.scx.rac.jo.RacRoleJo;
import rebue.scx.rac.mo.RacRoleAppMo;
import rebue.scx.rac.mo.RacRoleMo;
import rebue.scx.rac.mo.RacRolePermMo;
import rebue.scx.rac.ra.ListTransferOfRoleRa;
import rebue.scx.rac.svc.RacRoleSvc;
import rebue.scx.rac.to.RacAccountRoleAddTo;
import rebue.scx.rac.to.RacAccountRoleDelTo;
import rebue.scx.rac.to.RacRoleAddTo;
import rebue.scx.rac.to.RacRoleAppAddTo;
import rebue.scx.rac.to.RacRoleDelTo;
import rebue.scx.rac.to.RacRoleListTo;
import rebue.scx.rac.to.RacRoleModifyTo;
import rebue.scx.rac.to.RacRoleOneTo;
import rebue.scx.rac.to.RacRolePageTo;
import rebue.scx.rac.to.RacRolePermAddTo;
import rebue.scx.rac.to.ex.RacListTransferOfRoleTo;

/**
 * 角色API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacRoleApiImpl extends
    BaseApiImpl<java.lang.Long, RacRoleAddTo, RacRoleModifyTo, RacRoleDelTo, RacRoleOneTo, RacRoleListTo, RacRolePageTo, RacRoleMo, RacRoleJo, RacRoleSvc> implements RacRoleApi {

    /**
     * 添加角色和权限的关系
     *
     * @param to 添加的具体信息
     */
    @Override
    public Ro<?> addRolePerm(final RacRolePermAddTo to) {
        _svc.addRolePerm(to);
        return new Ro<>(ResultDic.SUCCESS, "添加/修改成功");
    }

    /**
     * 添加/修改角色和应用的关系
     *
     * @param to 添加的具体信息
     */
    @Override
    public Ro<?> addRoleApp(RacRoleAppAddTo to) {
        _svc.addRolePerm(to);
        return new Ro<>(ResultDic.SUCCESS, "添加/修改成功");
    }

    /**
     * 删除角色和账户的关系
     *
     * @param to 删除的具体信息
     */
    @Override
    public Ro<?> delAccountRole(final RacAccountRoleDelTo to) {
        _svc.delAccountRole(to);
        return new Ro<>(ResultDic.SUCCESS, "移除成功");
    }

    /**
     * 添加角色和账户的关系
     *
     * @param to 添加的具体信息
     */
    @Override
    public Ro<?> addAccountRole(final RacAccountRoleAddTo to) {
        _svc.addAccountRole(to);
        return new Ro<>(ResultDic.SUCCESS, "添加成功");
    }

    /**
     * 上移动
     */
    @Override
    public Ro<?> moveUp(final RacRoleModifyTo qo) {
        _svc.moveUp(qo);
        return new Ro<>(ResultDic.SUCCESS, "上移成功");
    }

    /**
     * 下移动
     */
    @Override
    public Ro<?> moveDown(final RacRoleModifyTo qo) {
        _svc.moveDown(qo);
        return new Ro<>(ResultDic.SUCCESS, "下移成功");
    }

    /**
     * 启用角色
     */
    @Override
    public Ro<?> enable(final RacRoleModifyTo qo) {
        _svc.enable(qo);
        return new Ro<>(ResultDic.SUCCESS, "启用角色成功");
    }

    /**
     * 禁用角色
     */
    @Override
    public Ro<?> disable(final RacRoleModifyTo qo) {
        _svc.disable(qo);
        return new Ro<>(ResultDic.SUCCESS, "禁用角色成功");
    }

    /**
     * 查询角色
     */
    @Override
    public Ro<ListRa<RacRoleMo>> list(final RacRoleListTo qo) {
        return new Ro<>(ResultDic.SUCCESS, "查询成功", new ListRa<>(_svc.list(qo)));
    }

    /**
     * 查询角色已有的权限的关系
     *
     * @param to 添加的具体信息
     */
    @Override
    public Ro<ListRa<RacRolePermMo>> listRolePerm(final Long roleId) {
        return new Ro<>(ResultDic.SUCCESS, "查询成功", new ListRa<>(_svc.listRolePerm(roleId)));
    }

    /**
     * 查询角色已有的应用的关系
     *
     * @param to 添加的具体信息
     */
    @Override
    public Ro<ListRa<RacRoleAppMo>> listRoleApp(Long roleId) {
        return new Ro<>(ResultDic.SUCCESS, "查询成功", new ListRa<>(_svc.listRoleApp(roleId)));
    }

    /**
     * 查询角色的信息
     *
     * @param qo 查询的具体条件
     */
    @Override
    public Ro<ListTransferOfRoleRa> listTransferOfRole(final RacListTransferOfRoleTo qo) {
        return _svc.listTransferOfRole(qo);
    }
}
