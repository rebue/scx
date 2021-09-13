package rebue.scx.oap.svc;

import java.net.URL;
import java.util.Map;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

import rebue.scx.oap.dto.LoginDto;

import rebue.robotech.ro.Ro;

public interface OidcSvc {

    String authorize(Map<String, String> paramMap, ServerHttpRequest request, ServerHttpResponse response);

    Ro<String> login(LoginDto loginData, ServerHttpRequest request, ServerHttpResponse response);

    Object token(String authorization, URL url, String requestBody, ServerHttpResponse response);

}
