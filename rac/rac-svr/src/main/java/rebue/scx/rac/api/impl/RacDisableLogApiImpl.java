package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rac.api.RacDisableLogApi;
import rebue.scx.rac.jo.RacDisableLogJo;
import rebue.scx.rac.mo.RacDisableLogMo;
import rebue.scx.rac.svc.RacDisableLogSvc;
import rebue.scx.rac.to.RacDisableLogAddTo;
import rebue.scx.rac.to.RacDisableLogDelTo;
import rebue.scx.rac.to.RacDisableLogListTo;
import rebue.scx.rac.to.RacDisableLogModifyTo;
import rebue.scx.rac.to.RacDisableLogOneTo;
import rebue.scx.rac.to.RacDisableLogPageTo;

/**
 * 账户启/禁用日志API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacDisableLogApiImpl extends
    BaseApiImpl<java.lang.Long, RacDisableLogAddTo, RacDisableLogModifyTo, RacDisableLogDelTo, RacDisableLogOneTo, RacDisableLogListTo, RacDisableLogPageTo, RacDisableLogMo, RacDisableLogJo, RacDisableLogSvc>
    implements RacDisableLogApi {
}
