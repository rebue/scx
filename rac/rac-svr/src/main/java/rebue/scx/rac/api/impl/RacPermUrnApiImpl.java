package rebue.scx.rac.api.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacPermUrnApi;
import rebue.scx.rac.jo.RacPermUrnJo;
import rebue.scx.rac.mo.RacPermUrnMo;
import rebue.scx.rac.svc.RacPermUrnSvc;
import rebue.scx.rac.to.RacPermUrnAddTo;
import rebue.scx.rac.to.RacPermUrnDelTo;
import rebue.scx.rac.to.RacPermUrnListTo;
import rebue.scx.rac.to.RacPermUrnModifyTo;
import rebue.scx.rac.to.RacPermUrnOneTo;
import rebue.scx.rac.to.RacPermUrnPageTo;

/**
 * 权限URNAPI实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacPermUrnApiImpl extends
    BaseApiImpl<java.lang.Long, RacPermUrnAddTo, RacPermUrnModifyTo, RacPermUrnDelTo, RacPermUrnOneTo, RacPermUrnListTo, RacPermUrnPageTo, RacPermUrnMo, RacPermUrnJo, RacPermUrnSvc>
    implements RacPermUrnApi {

    /**
     * 获取账户的链接列表
     *
     * @param accountId 账户ID
     *
     * @return 指定账户的链接列表
     */
    @Override
    public Ro<ListRa<String>> getUrnsOfAccount(final Long accountId) {
        final List<String> urns = _svc.getUrnsOfAccount(accountId);
        return new Ro<>(ResultDic.SUCCESS, "获取列表成功", new ListRa<>(urns));
    }

    /**
     * 添加修改URN
     */
    @Override
    public Ro<?> modifyByPermId(RacPermUrnAddTo to) {
        _svc.modifyByPermId(to);
        return new Ro<>(ResultDic.SUCCESS, "修改成功");
    }

    /**
     * 通过permId查询权限URN的信息
     *
     * @param qo 查询的具体条件
     */
    @Override
    public Ro<ListRa<RacPermUrnMo>> list(RacPermUrnListTo qo) {
        return new Ro<>(ResultDic.SUCCESS, "查询成功", new ListRa<>(_svc.list(qo)));
    }
}
