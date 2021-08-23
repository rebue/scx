package com.github.rebue.scx.svc.impl;

import com.github.rebue.scx.dto.LoginDto;
import com.github.rebue.scx.exception.OidcAuthenticationException;
import com.github.rebue.scx.oidc.AuthorisationCodeFlow;
import com.github.rebue.scx.oidc.CodeRepository;
import com.github.rebue.scx.oidc.OidcNS;
import com.github.rebue.scx.svc.OidcSvc;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.ResponseType;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OidcSvcImpl implements OidcSvc {

    private final Map<String, Map<String, String>> sessions = new HashMap<>();

    @Autowired
    private CodeRepository codeRepository;

    @Override
    @SneakyThrows
    public void authorize(Map<String, String> paramMap, ServerHttpRequest request, ServerHttpResponse response)
    {
        Map<String, List<String>> params = paramMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> Collections.singletonList(e.getValue())));
        AuthenticationRequest aRequest = AuthenticationRequest.parse(params);
        ResponseType responseType = aRequest.getResponseType();
        if (responseType.impliesCodeFlow()) {
            codeFlowLoginPage(aRequest, request, response);
        } else {
            throw new OidcAuthenticationException("Invalid authentication request");
        }
    }

    @SneakyThrows
    synchronized
    private void codeFlowLoginPage(AuthenticationRequest aRequest, ServerHttpRequest hRequest, ServerHttpResponse hResponse)
    {
        Map<String, String> sessionInfos = getOrCreateSession(hRequest, hResponse);
        if (isAuthenticated(sessionInfos)) {
            AuthorizationCode code = codeRepository.createCode(aRequest);
            // todo redirect 校验
            HTTPResponse redirect = AuthorisationCodeFlow.authenticationSuccessUri(aRequest.getRedirectionURI(), aRequest.getState(), code);
            hResponse.setStatusCode(HttpStatus.FOUND);
            hResponse.getHeaders().setLocation(URI.create(redirect.getLocation().toString()));
            return;
        }
        sessionInfos.put(OidcNS.OIDC_SKEY_STATE, OidcNS.getStateValue(aRequest));
        sessionInfos.put(OidcNS.OIDC_SKEY_CLIENT_ID, aRequest.getClientID().getValue());
        sessionInfos.put(OidcNS.OIDC_SKEY_REDIRECT_URI, AuthorisationCodeFlow.getRedirectUri(aRequest));
        sessionInfos.put(OidcNS.OIDC_SKEY_SCOPE, aRequest.getScope().toString());
        hResponse.setStatusCode(HttpStatus.FOUND);
        hResponse.getHeaders().setLocation(URI.create("http://localhost:13080/admin-web#/unifiedLogin"));
    }

    @Override
    @SneakyThrows
    public void login(LoginDto loginData, ServerHttpRequest request, ServerHttpResponse response)
    {
        // todo 用户名密码校验
        loginData.getLoginName();
        loginData.getPassword();
        Map<String, String> sessionInfo = getSession(request).orElse(null);
        if (sessionInfo == null) {
            // todo 错误信息
            return;
        }
        sessionInfo.put("isLogin", "isLogin");
        String uri = sessionInfo.get(OidcNS.OIDC_SKEY_REDIRECT_URI);
        String state = sessionInfo.get(OidcNS.OIDC_SKEY_STATE);
        String clientId = sessionInfo.get(OidcNS.OIDC_SKEY_CLIENT_ID);
        String scope = sessionInfo.get(OidcNS.OIDC_SKEY_SCOPE);
        AuthorizationCode code = codeRepository.createCode(uri, state, clientId, new Scope(scope));
        HTTPResponse redirect = AuthorisationCodeFlow.authenticationSuccessUri(new URI(uri), new State(state), code);
        response.setStatusCode(HttpStatus.FOUND);
        response.getHeaders().setLocation(URI.create(redirect.getLocation().toString()));
    }

    private Optional<Map<String, String>> getSession(ServerHttpRequest hRequest)
    {
        for (Map.Entry<String, List<HttpCookie>> kv : hRequest.getCookies().entrySet()) {
            if (kv.getKey().equals("sessionId")) {
                for (HttpCookie cookie : kv.getValue()) {
                    Map<String, String> m = sessions.get(cookie.getValue());
                    if (m != null) {
                        return Optional.of(m);
                    }
                }
            }
        }
        return Optional.empty();
    }

    private Map<String, String> getOrCreateSession(ServerHttpRequest hRequest, ServerHttpResponse hResponse)
    {
        Map<String, String> sessionInfo = getSession(hRequest).orElse(null);
        if (sessionInfo != null) {
            return sessionInfo;
        }
        String sessionId = UUID.randomUUID().toString();
        Map<String, String> m = new HashMap<>();
        hResponse.addCookie(createCookie("sessionId", sessionId));
        sessions.put(sessionId, m);
        return m;
    }

    private static ResponseCookie createCookie(String key, String value)
    {
        return ResponseCookie.from(key, value)
                .domain("localhost")
                .path("/")
                .maxAge(100000L)
                .build();
    }

    private boolean isAuthenticated(Map<String, String> sessionInfos)
    {
        return "isLogin".equals(sessionInfos.get("isLogin"));
    }

}
