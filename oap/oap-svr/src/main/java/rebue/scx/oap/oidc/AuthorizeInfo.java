package rebue.scx.oap.oidc;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rebue.scx.oap.exception.OidcAuthenticationException;
import rebue.scx.oap.utils.Base64Util;


@Data
@NoArgsConstructor
@AllArgsConstructor
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
        redirectUri = OidcHelper.getRedirectUri(aRequest);
    }

    public static Optional<AuthorizeInfo> fromCookie(String cookie)
    {
        try {
            String jsStr = Base64Util.decode(cookie);
            AuthorizeInfo o = JSONObject.parseObject(jsStr, AuthorizeInfo.class);
            return Optional.ofNullable(o);
        } catch (Exception e) {
            return Optional.empty();
        }
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
