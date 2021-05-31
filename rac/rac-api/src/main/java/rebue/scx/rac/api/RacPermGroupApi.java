package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacPermGroupMo;
import rebue.scx.rac.to.RacPermGroupAddTo;
import rebue.scx.rac.to.RacPermGroupModifyTo;
import rebue.scx.rac.to.RacPermGroupPageTo;

/**
 * 权限分组API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacPermGroupApi extends BaseApi<java.lang.Long, RacPermGroupAddTo, RacPermGroupModifyTo, RacPermGroupPageTo, RacPermGroupMo> {

    /**
     * 上移
     */
    Ro<?> moveUp(RacPermGroupModifyTo qo);

    /**
     * 下移
     */
    Ro<?> moveDown(RacPermGroupModifyTo qo);

    /**
     * 启用权限
     */
    Ro<?> enable(RacPermGroupModifyTo qo);

    /**
     * 禁用权限
     */
    Ro<?> disable(RacPermGroupModifyTo qo);
}
