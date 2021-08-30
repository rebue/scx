package com.github.rebue.scx.svc.impl;

import com.github.rebue.orp.core.OidcCore;
import com.github.rebue.scx.config.OidcConfig;
import com.github.rebue.scx.svc.OidcSvc;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.oauth2.sdk.TokenResponse;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponse;
import com.nimbusds.openid.connect.sdk.token.OIDCTokens;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class OidcSvcImpl implements OidcSvc {

    @Value("${oidc.client-id}")
    private String clientId;

    @Value("${oidc.client-secret}")
    private String clientSecret;

    @Value("${oidc.token-endpoint}")
    private String tokenEndpoint;

    @Value("${oidc.redirect-uri}")
    private String redirectUri;

    @Value("${oidc.public-key}")
    private String publicKeyStr;

    private RSAPublicKey publicKey;

    @PostConstruct
    private void init() throws Exception
    {
        byte[] decoded = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        publicKey = (RSAPublicKey) kf.generatePublic(spec);
    }

    /**
     * @return [token jsStr, 错误信息]
     */
    @Override
    @SneakyThrows
    public Pair<String, String> callback(ServerHttpRequest request, ServerHttpResponse response, String code)
    {
        TokenResponse tokenResponse = OidcCore.tokenRequest(
                tokenEndpoint,
                clientId,
                clientSecret,
                code,
                redirectUri
        );
        if (!tokenResponse.indicatesSuccess()) {
            return Pair.of(null, tokenResponse.toErrorResponse().getErrorObject().getDescription());
        }
        OIDCTokenResponse sr = (OIDCTokenResponse) tokenResponse.toSuccessResponse();
        OIDCTokens tokens = sr.getOIDCTokens();
        JWT idToken = tokens.getIDToken();
        if (!validateIdToken(idToken)) {
            return Pair.of(null, "服务器内部错误");
        }

        response.addCookie(createCookie(idToken.serialize()));
        return Pair.of(tokens.toJSONObject().toJSONString(), null);
    }

    private static ResponseCookie createCookie(String value)
    {
        return ResponseCookie.from(OidcConfig.UNIFIED_LOGIN_COOKIE, value)
                .domain(OidcConfig.CODE_FLOW_LOGIN_PAGE_COOKIE_DOMAIN)
                .path("/")
                .maxAge(OidcConfig.CODE_FLOW_LOGIN_PAGE_COOKIE_AGE)
                .build();
    }


    private boolean validateIdToken(JWT idToken) throws Exception
    {
        SignedJWT token = (SignedJWT) idToken;
        return token.verify(new RSASSAVerifier(publicKey));
    }

}
