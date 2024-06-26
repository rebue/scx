package rebue.scx.oap.oidc;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;

import org.apache.commons.lang3.StringUtils;

import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.ResponseMode;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.oap.exception.OidcAuthenticationException;

@Slf4j
public class OidcHelper {


    /**
     * 从认证请求中获取 Url解码 之后的重定向URI。
     */
    public static String getRedirectUri(AuthenticationRequest request)
    {
        String redirectUri = request.getRedirectionURI().toString();
        if (StringUtils.isBlank(redirectUri)) {
            throw new OidcAuthenticationException("Redirect URI is blank");
        }
        try {
            return URLDecoder.decode(redirectUri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new OidcAuthenticationException("Invalid Redirect URI", e);
        }
    }

    public static HTTPResponse authenticationSuccessUri(URI redirectionUri, State state, AuthorizationCode code)
    {
        AuthenticationSuccessResponse response = new AuthenticationSuccessResponse(
                redirectionUri, code, null, null,
                state, null, ResponseMode.QUERY);
        return response.toHTTPResponse();
    }

}
