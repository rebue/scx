package rebue.scx.rac.api.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacAppApi;
import rebue.scx.rac.jo.RacAppJo;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.mo.RacAppTagMo;
import rebue.scx.rac.svc.RacAppSvc;
import rebue.scx.rac.to.RacAppAddTo;
import rebue.scx.rac.to.RacAppDelTo;
import rebue.scx.rac.to.RacAppEnabledTo;
import rebue.scx.rac.to.RacAppListTo;
import rebue.scx.rac.to.RacAppModifyTo;
import rebue.scx.rac.to.RacAppOneTo;
import rebue.scx.rac.to.RacAppPageTo;

/**
 * 应用API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacAppApiImpl extends BaseApiImpl<java.lang.String, RacAppAddTo, RacAppModifyTo, RacAppDelTo, RacAppOneTo, RacAppListTo, RacAppPageTo, RacAppMo, RacAppJo, RacAppSvc>
        implements RacAppApi {

    @Override
    public Ro<ListRa<RacAppMo>> list(final RacAppListTo qo) {
        return new Ro<>(ResultDic.SUCCESS, "列表查询成功", new ListRa<>(_svc.list(qo)));
    }

    /**
     * 上移
     */
    @Override
    public Ro<?> moveUp(RacAppModifyTo qo) {
        _svc.moveUp(qo);
        return new Ro<>(ResultDic.SUCCESS, "上移成功");
    }

    /**
     * 下移
     */
    @Override
    public Ro<?> moveDown(RacAppModifyTo qo) {
        _svc.moveDown(qo);
        return new Ro<>(ResultDic.SUCCESS, "下移成功");
    }

    /**
     * 是否启用应用
     *
     * @param to 修改的具体数据
     */
    @Override
    public Ro<?> enable(RacAppEnabledTo to) {
        _svc.enable(to);
        if (to.getIsEnabled()) {
            return new Ro<>(ResultDic.SUCCESS, "启用应用成功");
        }
        else {
            return new Ro<>(ResultDic.SUCCESS, "禁用应用成功");
        }
    }

    /**
     * 根据帐号ID查询他可以看到的应用
     *
     * @param accountIds 账户ID集合
     *
     * @return
     */
    @Override
    public Ro<ListRa<RacAppMo>> selectAppByAccountIds(List<Long> accountIds) {
        return new Ro<>(ResultDic.SUCCESS, "列表查询成功", new ListRa<>(_svc.selectAppByAccountIds(accountIds)));
    }

    /**
     * 根据应用ID查询对应的标签信息
     */
    @Override
    public Ro<ListRa<RacAppTagMo>> listInAppIdList(List<String> appIds) {
        return new Ro<>(ResultDic.SUCCESS, "查询成功", new ListRa<>(_svc.listInAppIdList(appIds)));
    }

    @Override
    public Ro<ListRa<RacAppMo>> listOrderBySeqNo(RacAppListTo qo) {
        return new Ro<>(ResultDic.SUCCESS, "列表查询成功", new ListRa<>(_svc.listOrderBySeqNo(qo)));
    }
}
