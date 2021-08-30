package com.github.rebue.scx.svc;

import com.github.rebue.scx.dto.LoginDto;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import rebue.robotech.ro.Ro;

import java.net.URL;
import java.util.Map;

public interface OidcSvc {

    String authorize(Map<String, String> paramMap, ServerHttpRequest request, ServerHttpResponse response);

    Ro<String> login(LoginDto loginData, ServerHttpRequest request, ServerHttpResponse response);

    Object token(String authorization, URL url, String requestBody, ServerHttpResponse response);

}
