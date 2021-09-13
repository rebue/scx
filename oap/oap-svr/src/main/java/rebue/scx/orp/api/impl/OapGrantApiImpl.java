package rebue.scx.orp.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.scx.orp.api.OapGrantApi;
import rebue.scx.orp.jo.OapGrantJo;
import rebue.scx.orp.mo.OapGrantMo;
import rebue.scx.orp.svc.OapGrantSvc;
import rebue.scx.orp.to.OapGrantAddTo;
import rebue.scx.orp.to.OapGrantDelTo;
import rebue.scx.orp.to.OapGrantListTo;
import rebue.scx.orp.to.OapGrantModifyTo;
import rebue.scx.orp.to.OapGrantOneTo;
import rebue.scx.orp.to.OapGrantPageTo;

import rebue.robotech.api.impl.BaseApiImpl;

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
