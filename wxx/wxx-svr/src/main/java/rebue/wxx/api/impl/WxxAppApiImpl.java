package rebue.wxx.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.wxx.api.WxxAppApi;
import rebue.wxx.jo.WxxAppJo;
import rebue.wxx.mo.WxxAppMo;
import rebue.wxx.svc.WxxAppSvc;
import rebue.wxx.to.WxxAppAddTo;
import rebue.wxx.to.WxxAppDelTo;
import rebue.wxx.to.WxxAppListTo;
import rebue.wxx.to.WxxAppModifyTo;
import rebue.wxx.to.WxxAppOneTo;
import rebue.wxx.to.WxxAppPageTo;

/**
 * API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class WxxAppApiImpl extends BaseApiImpl<java.lang.String, WxxAppAddTo, WxxAppModifyTo, WxxAppDelTo, WxxAppOneTo, WxxAppListTo, WxxAppPageTo, WxxAppMo, WxxAppJo, WxxAppSvc>
    implements WxxAppApi {
}
