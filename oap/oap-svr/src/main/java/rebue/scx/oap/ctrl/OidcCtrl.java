package rebue.scx.oap.ctrl;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.oap.dto.LoginDto;
import rebue.scx.oap.dto.OidcGetUserInfoTo;
import rebue.scx.oap.dto.UserInfoMo;
import rebue.scx.oap.svc.OidcSvc;
import rebue.scx.oap.svc.impl.OidcSvcImpl;

/**
 * OIDC登录
 * 
 * @author yuanman
 *
 */
@RestController
@RequestMapping("/oap")
public class OidcCtrl {

    @Autowired
    private OidcSvc oidcSvc;

    /**
     * 重定向到前端登录页面(未登录)
     * https://openid.net/specs/openid-connect-core-1_0.html#AuthorizationEndpoint
     * scope openid
     * response_type code
     * client_id test1
     * redirect_uri https://www.baidu.com
     * state RECOMMENDED
     * 
     * @ignoreParams paramMap,request
     * 
     * @param paramMap
     * @param request
     * @param response
     */
    @RequestMapping(value = "/authorize", method = { RequestMethod.GET, RequestMethod.POST
    })
    @PostMapping("/authorize")
    public String authorize(@RequestParam Map<String, String> paramMap, ServerHttpRequest request, ServerHttpResponse response) {
        return oidcSvc.authorize(paramMap, request, response);
    }

    /**
     * 获取用户信息
     * 
     * @param userInfoTo
     * 
     * @return 用户基础信息
     */
    @GetMapping("/get-user-info")
    public Mono<Ro<UserInfoMo>> getUserInfo(OidcGetUserInfoTo userInfoTo) {
        return Mono.create(cb -> cb.success(oidcSvc.getUserInfo(userInfoTo)));
    }

    /**
     * 登录
     * 
     * @ignoreParams request
     * 
     * @param loginData
     * @param request
     * @param response
     * 
     */
    @PostMapping("/login")
    public Mono<Ro<String>> login(
            @RequestBody LoginDto loginData,
            ServerHttpRequest request,
            ServerHttpResponse response) {
        return Mono.create(cb -> cb.success(oidcSvc.login(loginData, request, response)));
    }

    /**
     * token校验获取
     * 
     * https://openid.net/specs/openid-connect-core-1_0.html#TokenEndpoint
     * https://openid.net/specs/openid-connect-core-1_0.html#RefreshTokens
     * 
     * @ignoreParams request
     */
    @PostMapping(value = "/token", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public Mono<Object> token(ServerHttpRequest request, ServerHttpResponse response) {
        List<String> authorizations = request.getHeaders().get("Authorization");
        if (CollectionUtils.isEmpty(authorizations)) {
            return Mono.just(OidcSvcImpl.tokenError(response, "auth", "missing Authorization header"));
        }
        URL url;
        try {
            url = new URL(request.getURI().toString());
        } catch (Exception e) {
            return Mono.just(OidcSvcImpl.tokenError(response, "url", e.getMessage()));
        }
        return DataBufferUtils.join(request.getBody())
                .map(buf -> {
                    String requestBody = StandardCharsets.UTF_8.decode(buf.asByteBuffer()).toString();
                    return oidcSvc.token(authorizations.get(0), url, requestBody, response);
                });
    }

}
