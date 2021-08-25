package com.github.rebue.scx.oidc;

import com.alibaba.fastjson.JSONObject;
import com.github.rebue.scx.exception.OidcAuthenticationException;
import com.github.rebue.scx.utils.Base64Util;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class AuthorizeInfo {

    private String state;

    private String clientId;

    private String scope;

    private String redirectUri;

    public AuthorizeInfo(AuthenticationRequest aRequest)
    {
        state = getStateValue(aRequest);
        clientId = aRequest.getClientID().getValue();
        scope = aRequest.getScope().toString();
        redirectUri = AuthorisationCodeFlow.getRedirectUri(aRequest);
    }

    public static AuthorizeInfo fromCookie(String cookie)
    {
        String jsStr = Base64Util.decode(cookie);
        return JSONObject.parseObject(jsStr, AuthorizeInfo.class);
    }

    public String toStr()
    {
        String str = JSONObject.toJSONString(this);
        return Base64Util.encode(str);
    }

    private static String getStateValue(AuthenticationRequest request)
    {
        State state = request.getState();
        if (state == null) {
            throw new OidcAuthenticationException("Missing state parameter");
        }
        String stateValue = state.getValue();
        if (StringUtils.isBlank(stateValue)) {
            throw new OidcAuthenticationException("Missing state parameter");
        }
        return stateValue;
    }

}
