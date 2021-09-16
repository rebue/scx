package rebue.scx.orp.svc;

import org.apache.commons.lang3.tuple.Pair;

import org.springframework.http.server.reactive.ServerHttpResponse;
import rebue.robotech.ro.Ro;
import rebue.scx.orp.ra.OrpUserInfoRa;
import rebue.scx.orp.to.OrpCodeTo;
import rebue.scx.rac.ra.SignUpOrInRa;

public interface OrpSvc {

    Pair<String, String> callback(String code, ServerHttpResponse response);

    /**
     * 获取认证Url(获取认证Url后前端跳转此URL)
     */
    String getAuthUrl(String orpType, String clientId, String redirectUri);

    /**
     * 认证授权码(OP服务器收到认证请求后重定向redirectUrl，通过此方法向OP服务器发出获取access_token的请求)
     */
    OrpUserInfoRa authCode(String orpType, String clientId, OrpCodeTo to);

    /**
     * 通过授权码登录
     */
    Ro<SignUpOrInRa> signInByCode(String appId, String orpType, String clientId, OrpCodeTo to);
}
