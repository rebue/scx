package com.github.rebue.scx.svc;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.util.Optional;

public interface OidcSvc {

    Optional<String> callback(ServerHttpRequest request, ServerHttpResponse response, String code);

}
