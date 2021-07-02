package rebue.wxx.api.impl.ex;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.wxx.api.ex.WxxRespApi;
import rebue.wxx.svc.ex.WxxRespSvc;
import rebue.wxx.to.ex.WxxRespAuthorizeTo;

/**
 * 响应微信发过来请求的API的实现
 */
@DubboService
public class WxxRespApiImpl implements WxxRespApi {
    @Resource
    protected WxxRespSvc _svc;

    @Override
    public String authorize(final String appId, final WxxRespAuthorizeTo to) {
        return _svc.authorize(appId, to);
    }

}
