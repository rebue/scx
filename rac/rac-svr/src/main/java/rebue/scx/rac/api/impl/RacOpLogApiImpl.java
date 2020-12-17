package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rac.api.RacOpLogApi;
import rebue.scx.rac.jo.RacOpLogJo;
import rebue.scx.rac.mo.RacOpLogMo;
import rebue.scx.rac.svc.RacOpLogSvc;
import rebue.scx.rac.to.RacOpLogAddTo;
import rebue.scx.rac.to.RacOpLogDelTo;
import rebue.scx.rac.to.RacOpLogModifyTo;
import rebue.scx.rac.to.RacOpLogOneTo;
import rebue.scx.rac.to.RacOpLogPageTo;

/**
 * 操作日志API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacOpLogApiImpl extends BaseApiImpl<java.lang.Long, RacOpLogAddTo, RacOpLogModifyTo, RacOpLogDelTo, RacOpLogOneTo, RacOpLogPageTo, RacOpLogMo, RacOpLogJo, RacOpLogSvc>
    implements RacOpLogApi {
}
