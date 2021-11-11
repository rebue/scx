package rebue.scx.oap.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.oap.api.OapGrantApi;
import rebue.scx.oap.jo.OapGrantJo;
import rebue.scx.oap.mo.OapGrantMo;
import rebue.scx.oap.svc.OapGrantSvc;
import rebue.scx.oap.to.OapGrantAddTo;
import rebue.scx.oap.to.OapGrantDelTo;
import rebue.scx.oap.to.OapGrantListTo;
import rebue.scx.oap.to.OapGrantModifyTo;
import rebue.scx.oap.to.OapGrantOneTo;
import rebue.scx.oap.to.OapGrantPageTo;

/**
 * 三方应用账户信息API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class OapGrantApiImpl
    extends BaseApiImpl<java.lang.Long, OapGrantAddTo, OapGrantModifyTo, OapGrantDelTo, OapGrantOneTo, OapGrantListTo, OapGrantPageTo, OapGrantMo, OapGrantJo, OapGrantSvc>
    implements OapGrantApi {
}
