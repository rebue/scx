package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacDomainApi;
import rebue.scx.rac.jo.RacDomainJo;
import rebue.scx.rac.mo.RacDomainMo;
import rebue.scx.rac.svc.RacDomainSvc;
import rebue.scx.rac.to.RacDomainAddTo;
import rebue.scx.rac.to.RacDomainDelTo;
import rebue.scx.rac.to.RacDomainListTo;
import rebue.scx.rac.to.RacDomainModifyTo;
import rebue.scx.rac.to.RacDomainOneTo;
import rebue.scx.rac.to.RacDomainPageTo;

/**
 * 领域API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacDomainApiImpl extends
    BaseApiImpl<java.lang.String, RacDomainAddTo, RacDomainModifyTo, RacDomainDelTo, RacDomainOneTo, RacDomainListTo, RacDomainPageTo, RacDomainMo, RacDomainJo, RacDomainSvc>
    implements RacDomainApi {

    @Override
    public Ro<ListRa<RacDomainMo>> listAll() {
        return new Ro<>(ResultDic.SUCCESS, "查询列表成功", new ListRa<>(_svc.listAll()));
    }
}
