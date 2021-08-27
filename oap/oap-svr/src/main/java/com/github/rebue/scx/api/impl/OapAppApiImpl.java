package com.github.rebue.scx.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import com.github.rebue.scx.mo.OapAppMo;
import com.github.rebue.scx.to.OapAppAddTo;
import com.github.rebue.scx.to.OapAppModifyTo;
import com.github.rebue.scx.to.OapAppDelTo;
import com.github.rebue.scx.to.OapAppOneTo;
import com.github.rebue.scx.to.OapAppListTo;
import com.github.rebue.scx.to.OapAppPageTo;
import com.github.rebue.scx.api.OapAppApi;
import com.github.rebue.scx.jo.OapAppJo;
import com.github.rebue.scx.svc.OapAppSvc;
import rebue.robotech.api.impl.BaseApiImpl;

/**
 * 第三方应用API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class OapAppApiImpl extends BaseApiImpl<java.lang.Long, OapAppAddTo, OapAppModifyTo, OapAppDelTo, OapAppOneTo, OapAppListTo, OapAppPageTo, OapAppMo, OapAppJo, OapAppSvc>
    implements OapAppApi {
}
