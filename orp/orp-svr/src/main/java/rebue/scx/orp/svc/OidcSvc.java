package rebue.scx.orp.svc;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.http.ResponseCookie;

import rebue.scx.orp.core.ro.UserInfoRo;

public interface OidcSvc {

    Triple<String, String, ResponseCookie> callback(String code);

    /**
     * 获取认证Url(获取认证Url后前端跳转此URL)
     */
    String getAuthUrl(String orpType, String clientId, String redirectUri);

    /**
     * 认证授权码(OP服务器收到认证请求后重定向redirectUrl，通过此方法向OP服务器发出获取access_token的请求)
     */
    UserInfoRo authCode(String orpType, String clientId, String code, String state);
}
