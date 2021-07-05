package rebue.wxx.api.impl.ex;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.wxx.api.ex.WxxReqApi;
import rebue.wxx.fto.WxxMessageTemplateSendFto;
import rebue.wxx.svc.ex.WxxReqSvc;

/**
 * 向微信发送请求的API的实现
 */
@DubboService
public class WxxReqApiImpl implements WxxReqApi {
    @Resource
    protected WxxReqSvc _svc;

    /**
     * 刷新Access token
     *
     * @param appId 微信的AppId
     */
    @Override
    public Ro<?> refreshAccessToken(final String appId) {
        _svc.refreshAccessToken(appId, null, true);
        return new Ro<>(ResultDic.SUCCESS, "刷新成功");
    }

    /**
     * 发送模板类的消息
     */
    @Override
    public Ro<?> sendTemplateMessage(final String appId, final WxxMessageTemplateSendFto to) {
        return _svc.sendTemplateMessage(appId, to);
    }

}
