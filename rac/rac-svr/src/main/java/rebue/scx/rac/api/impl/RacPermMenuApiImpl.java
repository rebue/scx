package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacPermMenuApi;
import rebue.scx.rac.jo.RacPermMenuJo;
import rebue.scx.rac.mo.RacPermMenuMo;
import rebue.scx.rac.svc.RacPermMenuSvc;
import rebue.scx.rac.to.RacPermMenuAddTo;
import rebue.scx.rac.to.RacPermMenuDelTo;
import rebue.scx.rac.to.RacPermMenuListTo;
import rebue.scx.rac.to.RacPermMenuModifyTo;
import rebue.scx.rac.to.RacPermMenuOneTo;
import rebue.scx.rac.to.RacPermMenuPageTo;
import rebue.scx.rac.to.ex.RacPermMenusAddTo;

/**
 * 权限菜单API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacPermMenuApiImpl extends
        BaseApiImpl<java.lang.Long, RacPermMenuAddTo, RacPermMenuModifyTo, RacPermMenuDelTo, RacPermMenuOneTo, RacPermMenuListTo, RacPermMenuPageTo, RacPermMenuMo, RacPermMenuJo, RacPermMenuSvc>
        implements RacPermMenuApi {

    /**
     * 添加/修改权限菜单
     *
     * @param to 添加的具体信息
     * 
     */
    @Override
    public Ro<IdRa<Long>> addPermMenuUrn(final RacPermMenusAddTo to) {
        _svc.addPermMenuUrn(to);
        return new Ro<>(ResultDic.SUCCESS, "添加/修改成功");
    }

    /**
     * 查询权限菜单的信息
     *
     * @param qo 查询的具体条件
     */
    @Override
    public Ro<ListRa<RacPermMenuMo>> listPermMenu(final RacPermMenuListTo qo) {
        return new Ro<>(ResultDic.SUCCESS, "查询成功", new ListRa<>(_svc.listPermMenu(qo)));
    }
}
