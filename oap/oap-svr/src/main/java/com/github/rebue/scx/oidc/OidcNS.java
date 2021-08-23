package com.github.rebue.scx.oidc;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.TokenRequest;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.PrivateKey;
import java.util.Date;

/**
 * 该模块存放OIDC协议以及OAuth2.0协议的公共功能代码。
 */
public class OidcNS {

    // 单位是秒
    public static final long ACCESS_TOKEN_LIFETIME = 60 * 60;

    // 单位是秒
    public static final long REFRESH_TOKEN_LIFETIME = 7 * 24 * 60 * 60;

    public static final String ID_TOKEN_ISSUER = "http://www.maiyuesoft.com";

    // 单位是毫秒
    public static final long ID_TOKEN_EXP_TIME = 24 * 60 * 60 * 1000L;

    public static final String OIDC_SKEY_STATE = "state";

    public static final String OIDC_SKEY_CLIENT_ID = "client_id";

    public static final String OIDC_SKEY_SCOPE = "scope";

    public static final String OIDC_SKEY_REDIRECT_URI = "redirect_uri";

    /**
     * 构造TokenRequest对象，用于TokenEndpoint。
     */
    public static TokenRequest tokenRequest(
            String requestBody, HttpServletRequest request) throws ParseException
    {
//        URI uri = URI.create(request.getRequestURL().toString());
//        HTTPRequest httpRequest = new HTTPRequest(HTTPRequest.Method.POST, uri);
//        httpRequest.setEntityContentType(ContentType.APPLICATION_URLENCODED);
//        httpRequest.setQuery(requestBody);
//        httpRequest.setAuthorization(request.getHeader("Authorization"));
//        return TokenRequest.parse(httpRequest);
        throw new RuntimeException("todo"); // todo
    }

    public static String getStateValue(AuthenticationRequest request)
    {
        State state = request.getState();
        if (state == null) {
            // todo throw new OidcAuthenticationException("Missing state parameter");
        }
        String stateValue = state.getValue();
        if (StringUtils.isBlank(stateValue)) {
            // todo throw new OidcAuthenticationException("Missing state parameter");
        }
        return stateValue;
    }

    public static SignedJWT makeIdtoken(
            PrivateKey privateKey, String userCode, String clientCode)
            throws JOSEException
    {
        Date now = new Date();
        Date exp = new Date(now.getTime() + ID_TOKEN_EXP_TIME);
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .issuer(ID_TOKEN_ISSUER)
                .subject(userCode)
                .audience(clientCode)
                .expirationTime(exp)
                .issueTime(now)
                .build();
        JWSSigner signer = new RSASSASigner(privateKey);
        SignedJWT jwt = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claims);
        jwt.sign(signer);
        return jwt;
    }

}
