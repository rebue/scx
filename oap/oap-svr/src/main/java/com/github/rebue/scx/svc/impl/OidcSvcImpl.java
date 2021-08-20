package com.github.rebue.scx.svc.impl;

import com.github.rebue.scx.dto.CodeValue;
import com.github.rebue.scx.exception.OidcAuthenticationException;
import com.github.rebue.scx.oidc.AuthorisationCodeFlow;
import com.github.rebue.scx.svc.OidcSvc;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.ResponseMode;
import com.nimbusds.oauth2.sdk.ResponseType;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;
import lombok.SneakyThrows;
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

    private Map<String, CodeValue> codes = new HashMap<>();

    private Map<String, Object> sessions = new HashMap<>(); // todo

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
        createSession(hRequest, hResponse);
        if (isAuthenticated(hRequest)) {
            String decodeUri = AuthorisationCodeFlow.getRedirectUri(aRequest);
            AuthorizationCode code = new AuthorizationCode(16);

            HTTPResponse success = new AuthenticationSuccessResponse(
                    URI.create(decodeUri), code, null, null,
                    aRequest.getState(), null, ResponseMode.QUERY).toHTTPResponse();
            String location = success.getLocation().toString();
            String clientId = aRequest.getClientID().getValue();

            CodeValue cv = new CodeValue(clientId, location, aRequest.getScope());
            codes.put(code.getValue(), cv);

            hResponse.setStatusCode(HttpStatus.FOUND);
            hResponse.getHeaders().setLocation(URI.create("http://localhost:13080/admin-web#/unifiedLogin"));
            return;
        }
        hResponse.addCookie(createCookie("state", "todo"));
        hResponse.addCookie(createCookie("client_id", "todo"));
        hResponse.setStatusCode(HttpStatus.FOUND);
        hResponse.getHeaders().setLocation(URI.create("http://localhost:13080/admin-web#/unifiedLogin"));
    }

    private void createSession(ServerHttpRequest hRequest, ServerHttpResponse hResponse)
    {
        for (Map.Entry<String, List<HttpCookie>> kv : hRequest.getCookies().entrySet()) {
            if (kv.getKey().equals("sessionId")) {
                return;
            }
        }
        String sessionId = UUID.randomUUID().toString();
        sessions.put(sessionId, true);
        hResponse.addCookie(createCookie("sessionId", sessionId));
    }

    private static ResponseCookie createCookie(String key, String value)
    {
        return ResponseCookie.from(key, value)
                .domain("localhost")
                .path("/")
                .maxAge(100000L)
                .build();
    }

    private boolean isAuthenticated(ServerHttpRequest hRequest)
    {
//        for (Map.Entry<String, List<HttpCookie>> kv : hRequest.getCookies().entrySet()) {
//            if (kv.getKey().equals("logined")) {
//                for (HttpCookie httpCookie : kv.getValue()) {
//                    if (LoginedCookies.contains(httpCookie.getValue())) {
//                        return true;
//                    }
//                }
//                return false;
//            }
//        }
        return false;
    }

}
