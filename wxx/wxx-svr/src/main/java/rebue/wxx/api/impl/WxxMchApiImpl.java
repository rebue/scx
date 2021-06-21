package rebue.wxx.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.wxx.api.WxxMchApi;
import rebue.wxx.jo.WxxMchJo;
import rebue.wxx.mo.WxxMchMo;
import rebue.wxx.svc.WxxMchSvc;
import rebue.wxx.to.WxxMchAddTo;
import rebue.wxx.to.WxxMchDelTo;
import rebue.wxx.to.WxxMchListTo;
import rebue.wxx.to.WxxMchModifyTo;
import rebue.wxx.to.WxxMchOneTo;
import rebue.wxx.to.WxxMchPageTo;

/**
 * 商户信息(微信支付账户信息)API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class WxxMchApiImpl extends BaseApiImpl<java.lang.String, WxxMchAddTo, WxxMchModifyTo, WxxMchDelTo, WxxMchOneTo, WxxMchListTo, WxxMchPageTo, WxxMchMo, WxxMchJo, WxxMchSvc>
    implements WxxMchApi {

}
