package rebue.wxx.fapi;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;

import rebue.wxx.fro.WxxGetAccessTokenFro;
import rebue.wxx.fro.WxxMessageTemplateSendFro;
import rebue.wxx.fto.WxxMessageTemplateSendFto;

@BaseRequest(baseURL = "https://api.weixin.qq.com")
public interface WxxFapi {
    /**
     * 获取Access Token
     */
    @Get("/cgi-bin/token?grant_type=client_credential")
    WxxGetAccessTokenFro getAccessToken(@Query("appid") String appId, @Query("secret") String appSecret);

    /**
     * 发送模板类的消息
     */
    @Post("https://api.weixin.qq.com/cgi-bin/message/template/send")
    WxxMessageTemplateSendFro sendTemplateMessage(@Query("access_token") String accessToken, @JSONBody WxxMessageTemplateSendFto to);
}
