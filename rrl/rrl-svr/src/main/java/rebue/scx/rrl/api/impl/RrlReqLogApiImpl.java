package rebue.scx.rrl.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rrl.api.RrlReqLogApi;
import rebue.scx.rrl.jo.RrlReqLogJo;
import rebue.scx.rrl.mo.RrlReqLogMo;
import rebue.scx.rrl.svc.RrlReqLogSvc;
import rebue.scx.rrl.to.RrlReqLogAddTo;
import rebue.scx.rrl.to.RrlReqLogDelTo;
import rebue.scx.rrl.to.RrlReqLogListTo;
import rebue.scx.rrl.to.RrlReqLogModifyTo;
import rebue.scx.rrl.to.RrlReqLogOneTo;
import rebue.scx.rrl.to.RrlReqLogPageTo;

/**
 * 请求日志API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RrlReqLogApiImpl
    extends BaseApiImpl<java.lang.Long, RrlReqLogAddTo, RrlReqLogModifyTo, RrlReqLogDelTo, RrlReqLogOneTo, RrlReqLogListTo, RrlReqLogPageTo, RrlReqLogMo, RrlReqLogJo, RrlReqLogSvc>
    implements RrlReqLogApi {
}
