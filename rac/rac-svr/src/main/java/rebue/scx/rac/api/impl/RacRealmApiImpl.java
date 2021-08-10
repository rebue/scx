package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacRealmApi;
import rebue.scx.rac.jo.RacRealmJo;
import rebue.scx.rac.mo.RacRealmMo;
import rebue.scx.rac.svc.RacRealmSvc;
import rebue.scx.rac.to.RacRealmAddTo;
import rebue.scx.rac.to.RacRealmDelTo;
import rebue.scx.rac.to.RacRealmListTo;
import rebue.scx.rac.to.RacRealmModifyTo;
import rebue.scx.rac.to.RacRealmOneTo;
import rebue.scx.rac.to.RacRealmPageTo;

/**
 * 领域API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacRealmApiImpl
        extends BaseApiImpl<java.lang.String, RacRealmAddTo, RacRealmModifyTo, RacRealmDelTo, RacRealmOneTo, RacRealmListTo, RacRealmPageTo, RacRealmMo, RacRealmJo, RacRealmSvc>
        implements RacRealmApi {

    /**
     * 查询所有记录
     */
    @Override
    public Ro<ListRa<RacRealmMo>> listAll() {
        return new Ro<>(ResultDic.SUCCESS, "查询列表成功", new ListRa<>(_svc.listAll()));
    }

}
