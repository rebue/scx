package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacPermCommandApi;
import rebue.scx.rac.jo.RacPermCommandJo;
import rebue.scx.rac.mo.RacPermCommandMo;
import rebue.scx.rac.svc.RacPermCommandSvc;
import rebue.scx.rac.to.RacPermCommandAddTo;
import rebue.scx.rac.to.RacPermCommandDelTo;
import rebue.scx.rac.to.RacPermCommandListTo;
import rebue.scx.rac.to.RacPermCommandModifyTo;
import rebue.scx.rac.to.RacPermCommandOneTo;
import rebue.scx.rac.to.RacPermCommandPageTo;

/**
 * 权限命令API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacPermCommandApiImpl extends
    BaseApiImpl<java.lang.Long, RacPermCommandAddTo, RacPermCommandModifyTo, RacPermCommandDelTo, RacPermCommandOneTo, RacPermCommandListTo, RacPermCommandPageTo, RacPermCommandMo, RacPermCommandJo, RacPermCommandSvc>
    implements RacPermCommandApi {

    /**
     * 查询权限命令的信息
     *
     * @param qo 查询的具体条件
     */
    @Override
    public Ro<ListRa<RacPermCommandMo>> list(final RacPermCommandListTo qo) {
        return new Ro<>(ResultDic.SUCCESS, "分页查询成功", new ListRa<>(_svc.list(qo)));
    }
}
