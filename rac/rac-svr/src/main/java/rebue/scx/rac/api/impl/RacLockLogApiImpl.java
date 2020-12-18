package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rac.api.RacLockLogApi;
import rebue.scx.rac.jo.RacLockLogJo;
import rebue.scx.rac.mo.RacLockLogMo;
import rebue.scx.rac.svc.RacLockLogSvc;
import rebue.scx.rac.to.RacLockLogAddTo;
import rebue.scx.rac.to.RacLockLogDelTo;
import rebue.scx.rac.to.RacLockLogModifyTo;
import rebue.scx.rac.to.RacLockLogOneTo;
import rebue.scx.rac.to.RacLockLogPageTo;

/**
 * 锁定日志API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacLockLogApiImpl
    extends BaseApiImpl<java.lang.Long, RacLockLogAddTo, RacLockLogModifyTo, RacLockLogDelTo, RacLockLogOneTo, RacLockLogPageTo, RacLockLogMo, RacLockLogJo, RacLockLogSvc>
    implements RacLockLogApi {
}
