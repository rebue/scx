package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rac.api.RacStatusApi;
import rebue.scx.rac.jo.RacStatusJo;
import rebue.scx.rac.mo.RacStatusMo;
import rebue.scx.rac.svc.RacStatusSvc;
import rebue.scx.rac.to.RacStatusAddTo;
import rebue.scx.rac.to.RacStatusDelTo;
import rebue.scx.rac.to.RacStatusListTo;
import rebue.scx.rac.to.RacStatusModifyTo;
import rebue.scx.rac.to.RacStatusOneTo;
import rebue.scx.rac.to.RacStatusPageTo;

/**
 * 身份API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacStatusApiImpl
    extends BaseApiImpl<java.lang.Long, RacStatusAddTo, RacStatusModifyTo, RacStatusDelTo, RacStatusOneTo, RacStatusListTo, RacStatusPageTo, RacStatusMo, RacStatusJo, RacStatusSvc>
    implements RacStatusApi {

}
