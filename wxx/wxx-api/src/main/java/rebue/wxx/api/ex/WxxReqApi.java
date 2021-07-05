package rebue.wxx.api.ex;

import rebue.robotech.ro.Ro;
import rebue.wxx.fto.WxxMessageTemplateSendFto;

/**
 * 向微信发送请求的API
 */
public interface WxxReqApi {
    /**
     * 刷新Access token
     *
     * @param appId 微信的AppId
     */
    Ro<?> refreshAccessToken(String appId);

    /**
     * 发送模板类的消息
     */
    Ro<?> sendTemplateMessage(String appId, WxxMessageTemplateSendFto to);

}
