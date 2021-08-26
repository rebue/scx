package com.github.rebue.scx.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import com.github.rebue.scx.mo.OapRedirectUriMo;
import com.github.rebue.scx.to.OapRedirectUriAddTo;
import com.github.rebue.scx.to.OapRedirectUriModifyTo;
import com.github.rebue.scx.to.OapRedirectUriDelTo;
import com.github.rebue.scx.to.OapRedirectUriOneTo;
import com.github.rebue.scx.to.OapRedirectUriListTo;
import com.github.rebue.scx.to.OapRedirectUriPageTo;
import com.github.rebue.scx.api.OapRedirectUriApi;
import com.github.rebue.scx.jo.OapRedirectUriJo;
import com.github.rebue.scx.svc.OapRedirectUriSvc;

import rebue.robotech.api.impl.BaseApiImpl;

/**
 * API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class OapRedirectUriApiImpl extends
    BaseApiImpl<java.lang.Long, OapRedirectUriAddTo, OapRedirectUriModifyTo, OapRedirectUriDelTo, OapRedirectUriOneTo, OapRedirectUriListTo, OapRedirectUriPageTo, OapRedirectUriMo, OapRedirectUriJo, OapRedirectUriSvc>
    implements OapRedirectUriApi {

}
