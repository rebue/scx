package rebue.scx.orp.svc;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.http.ResponseCookie;

import rebue.scx.orp.core.dic.OrpTypeDic;
import rebue.scx.orp.core.ro.UserInfoRo;

public interface OidcSvc {

    Triple<String, String, ResponseCookie> callback(String code);

    /**
     * 认证授权码(OP服务器收到认证请求后重定向redirectUrl，通过此方法向OP服务器发出获取access_token的请求)
     */
    UserInfoRo authCode(OrpTypeDic orpType, String code, String state);
}
