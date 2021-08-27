package com.github.rebue.scx.svc.impl;

import com.github.rebue.orp.core.OidcCore;
import com.github.rebue.scx.svc.OidcSvc;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.oauth2.sdk.TokenResponse;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponse;
import com.nimbusds.openid.connect.sdk.token.OIDCTokens;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Optional;

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

    @Override
    @SneakyThrows
    public Optional<String> callback(ServerHttpRequest request, String code)
    {
        TokenResponse tokenResponse = OidcCore.tokenRequest(
                tokenEndpoint,
                clientId,
                clientSecret,
                code,
                redirectUri
        );
        if (!tokenResponse.indicatesSuccess()) {
            // todo 提示错误信息
            return Optional.empty();
        }
        OIDCTokenResponse sr = (OIDCTokenResponse) tokenResponse.toSuccessResponse();
        OIDCTokens tokens = sr.getOIDCTokens();
        JWT idToken = tokens.getIDToken();
        if (!validateIdToken(idToken)) {
            // todo 提示错误信息
            return Optional.empty();
        }
        return Optional.of(tokens.getAccessToken().getValue());
    }

    private boolean validateIdToken(JWT idToken) throws Exception
    {
        SignedJWT token = (SignedJWT) idToken;
        return token.verify(new RSASSAVerifier(publicKey));
    }

}
