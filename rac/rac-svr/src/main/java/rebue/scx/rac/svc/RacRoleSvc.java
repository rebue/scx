package rebue.scx.rac.svc;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacRoleJo;
import rebue.scx.rac.mo.RacRoleAppMo;
import rebue.scx.rac.mo.RacRoleMo;
import rebue.scx.rac.mo.RacRolePermMo;
import rebue.scx.rac.ra.ListTransferOfRoleRa;
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
 * 角色服务接口
 *
 * <pre>
 * 1. 在接口上方必须写上 @Validated 注解
 * 2. 参数是POJO类时用 @Valid 注解在参数类型的前面进行修饰
 *    参数是普通参数时，直接在参数类型的前面加上具体约束的注解
 * 3. (待验证)有分组时，在方法上方必须写上 @Validated 注解及分组
 * 4. 踩坑留痕：
 *    如果方法的返回值为void，在方法上方加上 @Valid 注解会出现异常，报HV000132错误
 * </pre>
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Validated
public interface RacRoleSvc extends BaseSvc<java.lang.Long, RacRoleAddTo, RacRoleModifyTo, RacRoleDelTo, RacRoleOneTo, RacRoleListTo, RacRolePageTo, RacRoleMo, RacRoleJo> {

    void moveUp(RacRoleModifyTo qo);

    void moveDown(RacRoleModifyTo qo);

    void enable(RacRoleModifyTo qo);

    void disable(RacRoleModifyTo qo);

    void addRolePerm(RacRolePermAddTo to);

    /**
     * 查询角色已有的权限的关系
     *
     * @param to 添加的具体信息
     */
    List<RacRolePermMo> listRolePerm(Long roleId);

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
    void addAccountRole(RacAccountRoleAddTo to);

    /**
     * 删除角色和账户的关系
     *
     * @param to 删除的具体信息
     */
    void delAccountRole(RacAccountRoleDelTo to);

    /**
     * 添加/修改角色和应用的关系
     *
     * @param to 添加的具体信息
     */
    void addRolePerm(RacRoleAppAddTo to);

    /**
     * 查询角色已有的应用的关系
     *
     * @param to 添加的具体信息
     */
    List<RacRoleAppMo> listRoleApp(Long roleId);
}
