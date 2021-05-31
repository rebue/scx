package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacPermGroupApi;
import rebue.scx.rac.jo.RacPermGroupJo;
import rebue.scx.rac.mo.RacPermGroupMo;
import rebue.scx.rac.svc.RacPermGroupSvc;
import rebue.scx.rac.to.RacPermGroupAddTo;
import rebue.scx.rac.to.RacPermGroupDelTo;
import rebue.scx.rac.to.RacPermGroupListTo;
import rebue.scx.rac.to.RacPermGroupModifyTo;
import rebue.scx.rac.to.RacPermGroupOneTo;
import rebue.scx.rac.to.RacPermGroupPageTo;

/**
 * 权限分组API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacPermGroupApiImpl extends
    BaseApiImpl<java.lang.Long, RacPermGroupAddTo, RacPermGroupModifyTo, RacPermGroupDelTo, RacPermGroupOneTo, RacPermGroupListTo, RacPermGroupPageTo, RacPermGroupMo, RacPermGroupJo, RacPermGroupSvc>
    implements RacPermGroupApi {

    /**
     * 上移
     */
    @Override
    public Ro<?> moveUp(RacPermGroupModifyTo qo) {
        _svc.moveUp(qo);
        return new Ro<>(ResultDic.SUCCESS, "上移成功");
    }

    /**
     * 下移
     */
    @Override
    public Ro<?> moveDown(RacPermGroupModifyTo qo) {
        _svc.moveDown(qo);
        return new Ro<>(ResultDic.SUCCESS, "下移成功");
    }

    /**
     * 启用权限
     */
    @Override
    public Ro<?> enable(RacPermGroupModifyTo qo) {
        _svc.enableLinkage(qo);
        return new Ro<>(ResultDic.SUCCESS, "启用权限分组成功");
    }

    /**
     * 禁用权限
     */
    @Override
    public Ro<?> disable(RacPermGroupModifyTo qo) {
        _svc.disableLinkage(qo);
        return new Ro<>(ResultDic.SUCCESS, "禁用权限分组成功");
    }
}
