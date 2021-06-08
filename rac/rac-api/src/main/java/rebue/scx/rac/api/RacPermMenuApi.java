package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacPermMenuMo;
import rebue.scx.rac.to.RacPermMenuAddTo;
import rebue.scx.rac.to.RacPermMenuListTo;
import rebue.scx.rac.to.RacPermMenuModifyTo;
import rebue.scx.rac.to.RacPermMenuPageTo;
import rebue.scx.rac.to.ex.RacPermMenusAddTo;

/**
 * 权限菜单API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacPermMenuApi extends BaseApi<java.lang.Long, RacPermMenuAddTo, RacPermMenuModifyTo, RacPermMenuPageTo, RacPermMenuMo> {
    /**
     * 查询权限菜单的信息
     *
     * @param qo 查询的具体条件
     */
    Ro<ListRa<RacPermMenuMo>> listPermMenu(RacPermMenuListTo qo);

    /**
     * 添加/修改权限菜单
     *
     * @param to 添加的具体信息
     * 
     */
    Ro<IdRa<Long>> addPermMenuUrn(RacPermMenusAddTo to);
}
