package rebue.scx.orp.api;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.http.ResponseCookie;

import rebue.robotech.ro.Ro;

public interface OidcApi {

    Triple<String, String, ResponseCookie> callback(String code);

    /**
     * 获取认证Url(获取认证Url后前端跳转此URL)
     */
    Ro<?> getAuthUrl(String orpType, String clientId, String redirectUri);

    /**
     * 认证授权码(OP服务器收到认证请求后重定向redirectUrl，通过此方法向OP服务器发出获取access_token的请求)
     */
    Ro<?> getUserInfo(String orpType, String clientId, String code, String state);

}
