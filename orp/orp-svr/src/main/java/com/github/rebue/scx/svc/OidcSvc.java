package com.github.rebue.scx.svc;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

public interface OidcSvc {

    /**
     * @return [token jsStr, 错误信息]
     */
    Pair<String, String> callback(ServerHttpRequest request, ServerHttpResponse response, String code, String redirectUri);

}
