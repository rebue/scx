package com.github.rebue.scx.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import com.github.rebue.scx.mo.OapGrantMo;
import com.github.rebue.scx.to.OapGrantAddTo;
import com.github.rebue.scx.to.OapGrantModifyTo;
import com.github.rebue.scx.to.OapGrantDelTo;
import com.github.rebue.scx.to.OapGrantOneTo;
import com.github.rebue.scx.to.OapGrantListTo;
import com.github.rebue.scx.to.OapGrantPageTo;
import com.github.rebue.scx.api.OapGrantApi;
import com.github.rebue.scx.jo.OapGrantJo;
import com.github.rebue.scx.svc.OapGrantSvc;
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
