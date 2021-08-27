package com.github.rebue.scx.svc;

import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.Optional;

public interface OidcSvc {

    Optional<String> callback(ServerHttpRequest request, String code);

}
