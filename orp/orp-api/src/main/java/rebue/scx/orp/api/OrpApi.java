package rebue.scx.orp.api;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.server.reactive.ServerHttpResponse;

import rebue.robotech.ro.Ro;
import rebue.scx.orp.to.OrpCodeTo;
import rebue.scx.rac.ra.SignUpOrInRa;

public interface OrpApi {

    Pair<String, String> callback(String code, ServerHttpResponse response);

    /**
     * 获取认证Url(获取认证Url后前端跳转此URL)
     */
    Ro<?> getAuthUrl(String orpType, String clientId, String redirectUri);

    /**
     * 认证授权码(OP服务器收到认证请求后重定向redirectUrl，通过此方法向OP服务器发出获取access_token的请求)
     */
    Ro<?> getUserInfo(String orpType, String clientId, OrpCodeTo to);

    /**
     * 通过授权码登录
     */
    Ro<SignUpOrInRa> signInByCode(String appId, String orpType, String clientId, OrpCodeTo to);

    /**
     * 根据账户ID绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     * 
     */
    Ro<?> bindModify(String orpType, String clientId, Long accountId, OrpCodeTo to);

    /**
     * 解除绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     * 
     */
    Ro<?> unbindModify(String orpType, String clientId, Long accountId, OrpCodeTo to);

}
