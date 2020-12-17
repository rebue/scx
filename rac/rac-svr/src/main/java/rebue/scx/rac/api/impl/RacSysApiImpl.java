package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rac.api.RacSysApi;
import rebue.scx.rac.jo.RacSysJo;
import rebue.scx.rac.mo.RacSysMo;
import rebue.scx.rac.svc.RacSysSvc;
import rebue.scx.rac.to.RacSysAddTo;
import rebue.scx.rac.to.RacSysDelTo;
import rebue.scx.rac.to.RacSysModifyTo;
import rebue.scx.rac.to.RacSysOneTo;
import rebue.scx.rac.to.RacSysPageTo;

/**
 * 系统API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacSysApiImpl extends BaseApiImpl<java.lang.String, RacSysAddTo, RacSysModifyTo, RacSysDelTo, RacSysOneTo, RacSysPageTo, RacSysMo, RacSysJo, RacSysSvc>
    implements RacSysApi {
}
