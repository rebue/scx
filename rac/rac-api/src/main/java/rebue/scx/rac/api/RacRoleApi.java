package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacRoleMo;
import rebue.scx.rac.mo.RacRolePermMo;
import rebue.scx.rac.ra.ListTransferOfRoleRa;
import rebue.scx.rac.to.RacAccountRoleAddTo;
import rebue.scx.rac.to.RacAccountRoleDelTo;
import rebue.scx.rac.to.RacRoleAddTo;
import rebue.scx.rac.to.RacRoleListTo;
import rebue.scx.rac.to.RacRoleModifyTo;
import rebue.scx.rac.to.RacRolePageTo;
import rebue.scx.rac.to.RacRolePermAddTo;
import rebue.scx.rac.to.ex.RacListTransferOfRoleTo;

/**
 * 角色API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacRoleApi extends BaseApi<java.lang.Long, RacRoleAddTo, RacRoleModifyTo, RacRolePageTo, RacRoleMo> {

    /**
     * 查询角色
     */
    Ro<ListRa<RacRoleMo>> list(RacRoleListTo qo);

    /**
     * 上移动
     */
    Ro<?> moveUp(RacRoleModifyTo qo);

    /**
     * 下移动
     */
    Ro<?> moveDown(RacRoleModifyTo qo);

    /**
     * 启用角色
     */
    Ro<?> enable(RacRoleModifyTo qo);

    /**
     * 禁用角色
     */
    Ro<?> disable(RacRoleModifyTo qo);

    /**
     * 添加角色和权限的关系
     *
     * @param to 添加的具体信息
     */
    Ro<?> addRolePerm(RacRolePermAddTo to);

    /**
     * 查询角色已有的权限的关系
     *
     * @param to 添加的具体信息
     */
    Ro<ListRa<RacRolePermMo>> listRolePerm(Long roleId);

    /**
     * 查询角色的信息
     *
     * @param qo 查询的具体条件
     */
    Ro<ListTransferOfRoleRa> listTransferOfRole(RacListTransferOfRoleTo qo);

    /**
     * 添加角色和账户的关系
     *
     * @param to 添加的具体信息
     */
    Ro<?> addAccountRole(RacAccountRoleAddTo to);

    /**
     * 删除角色和账户的关系
     *
     * @param to 删除的具体信息
     */
    Ro<?> delAccountRole(RacAccountRoleDelTo to);
}
