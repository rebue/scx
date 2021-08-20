package com.github.rebue.scx.oidc;

import com.github.rebue.scx.exception.OidcAuthenticationException;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Slf4j
public class AuthorisationCodeFlow {


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

}
