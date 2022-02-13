package rebue.scx.oap.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.oap.api.OapRedirectUriApi;
import rebue.scx.oap.jo.OapRedirectUriJo;
import rebue.scx.oap.mo.OapRedirectUriMo;
import rebue.scx.oap.svc.OapRedirectUriSvc;
import rebue.scx.oap.to.OapRedirectUriAddTo;
import rebue.scx.oap.to.OapRedirectUriDelTo;
import rebue.scx.oap.to.OapRedirectUriListTo;
import rebue.scx.oap.to.OapRedirectUriModifyTo;
import rebue.scx.oap.to.OapRedirectUriOneTo;
import rebue.scx.oap.to.OapRedirectUriPageTo;

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
