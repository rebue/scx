package com.github.rebue.scx.svc;

import com.github.rebue.scx.dto.LoginDto;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.util.Map;

public interface OidcSvc {

    void authorize(Map<String, String> paramMap, ServerHttpRequest request, ServerHttpResponse response);

    void login(LoginDto loginData, ServerHttpRequest request, ServerHttpResponse response);

}
