package rebue.wxx.fapi;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;

import rebue.wxx.fro.WxxGetAccessTokenFro;

@BaseRequest(baseURL = "https://api.weixin.qq.com")
public interface WxxFapi {
    @Get("/cgi-bin/token?grant_type=client_credential")
    WxxGetAccessTokenFro getAccessToken(@Query("appid") String appId, @Query("secret") String appSecret);
}
