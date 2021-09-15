package com.github.rebue.orp.core;

import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponseParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OidcCore {

    /**
     * @param tokenEndpointURI 统一身份认证token接口地址
     * @param clientId         第三方应用id, 需要在统一身份认证系统中配置
     * @param clientSecret     第三方应用密钥, 需要在统一身份认证系统中配置
     * @param code             todo
     * @param redirectUri      todo
     */
    public static TokenResponse tokenRequest(
            String tokenEndpointURI,
            String clientId,
            String clientSecret,
            String code,
            String redirectUri
    )
            throws URISyntaxException, IOException, ParseException
    {
        AuthorizationCode aCode = new AuthorizationCode(code);
        URI callback = new URI(redirectUri);
        AuthorizationGrant codeGrant = new AuthorizationCodeGrant(aCode, callback);
        HTTPResponse response = sendTokenRequest(tokenEndpointURI, clientId, clientSecret, codeGrant);
        return OIDCTokenResponseParser.parse(response);
    }

    private static HTTPResponse sendTokenRequest(
            String tokenEndpointURI, String clientId,
            String clientSecret, AuthorizationGrant grant)
            throws URISyntaxException, IOException
    {
        ClientID clientID = new ClientID(clientId);
        Secret secret = new Secret(clientSecret);
        ClientAuthentication clientAuth = new ClientSecretBasic(clientID, secret);

        URI tokenEndpoint = new URI(tokenEndpointURI);
        TokenRequest request = new TokenRequest(tokenEndpoint, clientAuth, grant);
        return request.toHTTPRequest().send();
    }

}
