package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacPermMo;
import rebue.scx.rac.ra.PermListWithGroupRa;
import rebue.scx.rac.to.RacPermAddTo;
import rebue.scx.rac.to.RacPermModifyTo;
import rebue.scx.rac.to.RacPermPageTo;

/**
 * 权限API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacPermApi extends BaseApi<java.lang.Long, RacPermAddTo, RacPermModifyTo, RacPermPageTo, RacPermMo> {

    /**
     * 查询带分组的权限列表
     *
     * @param domainId 领域ID
     */
    Ro<PermListWithGroupRa> listWithGroup(String domainId);

    /**
     * 上移动
     */
    Ro<?> moveUp(RacPermModifyTo qo);

    /**
     * 下移动
     */
    Ro<?> moveDown(RacPermModifyTo qo);

    /**
     * 启用权限
     */
    Ro<?> enable(RacPermModifyTo qo);

    /**
     * 禁用权限
     */
    Ro<?> disable(RacPermModifyTo qo);
}
