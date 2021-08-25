package com.github.rebue.scx.oidc;

import com.alibaba.fastjson.JSONObject;
import com.github.rebue.scx.dto.CodeValue;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.ResponseMode;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.time.Duration;
import java.util.Optional;

@Repository
public class CodeRepository {

    private static final String CODE_PREFIX = "cp:";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public Optional<CodeValue> getCode(String code)
    {
        String key = CODE_PREFIX + code;
        String jsStr = stringRedisTemplate.opsForValue().get(key);
        if (jsStr == null) {
            return Optional.empty();
        }
        stringRedisTemplate.delete(key);
        CodeValue value = JSONObject.parseObject(jsStr, CodeValue.class);
        return Optional.ofNullable(value);
    }

    public AuthorizationCode createCode(AuthenticationRequest aRequest, String userCode)
    {
        String state = "";
        if (aRequest.getState() != null) {
            state = aRequest.getState().getValue();
        }
        return createCode(
                AuthorisationCodeFlow.getRedirectUri(aRequest),
                state,
                aRequest.getClientID().getValue(),
                aRequest.getScope(),
                userCode
        );
    }

    public AuthorizationCode createCode(String redirectUri, String state, String clientId, Scope scope, String userCode)
    {
        AuthorizationCode code = new AuthorizationCode(16);
        HTTPResponse success = new AuthenticationSuccessResponse(
                URI.create(redirectUri), code, null, null,
                new State(state), null, ResponseMode.QUERY).toHTTPResponse();
        String location = success.getLocation().toString();
        CodeValue cv = new CodeValue(clientId, location, scope, userCode);
        String jsStr = JSONObject.toJSONString(cv);
        stringRedisTemplate.opsForValue().set(CODE_PREFIX + code.getValue(), jsStr, Duration.ofMinutes(1));
        return code;
    }

}
