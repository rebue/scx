package rebue.scx.orp.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.scx.orp.api.OapRedirectUriApi;
import rebue.scx.orp.jo.OapRedirectUriJo;
import rebue.scx.orp.mo.OapRedirectUriMo;
import rebue.scx.orp.svc.OapRedirectUriSvc;
import rebue.scx.orp.to.OapRedirectUriAddTo;
import rebue.scx.orp.to.OapRedirectUriDelTo;
import rebue.scx.orp.to.OapRedirectUriListTo;
import rebue.scx.orp.to.OapRedirectUriModifyTo;
import rebue.scx.orp.to.OapRedirectUriOneTo;
import rebue.scx.orp.to.OapRedirectUriPageTo;

import rebue.robotech.api.impl.BaseApiImpl;

/**
 * 第三方应用URLAPI实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class OapRedirectUriApiImpl extends
    BaseApiImpl<java.lang.Long, OapRedirectUriAddTo, OapRedirectUriModifyTo, OapRedirectUriDelTo, OapRedirectUriOneTo, OapRedirectUriListTo, OapRedirectUriPageTo, OapRedirectUriMo, OapRedirectUriJo, OapRedirectUriSvc>
    implements OapRedirectUriApi {
}
