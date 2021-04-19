package rebue.scx.rrl.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rrl.api.RrlRespLogApi;
import rebue.scx.rrl.jo.RrlRespLogJo;
import rebue.scx.rrl.mo.RrlRespLogMo;
import rebue.scx.rrl.svc.RrlRespLogSvc;
import rebue.scx.rrl.to.RrlRespLogAddTo;
import rebue.scx.rrl.to.RrlRespLogDelTo;
import rebue.scx.rrl.to.RrlRespLogListTo;
import rebue.scx.rrl.to.RrlRespLogModifyTo;
import rebue.scx.rrl.to.RrlRespLogOneTo;
import rebue.scx.rrl.to.RrlRespLogPageTo;

/**
 * 响应日志API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RrlRespLogApiImpl extends
    BaseApiImpl<java.lang.Long, RrlRespLogAddTo, RrlRespLogModifyTo, RrlRespLogDelTo, RrlRespLogOneTo, RrlRespLogListTo, RrlRespLogPageTo, RrlRespLogMo, RrlRespLogJo, RrlRespLogSvc>
    implements RrlRespLogApi {

}
