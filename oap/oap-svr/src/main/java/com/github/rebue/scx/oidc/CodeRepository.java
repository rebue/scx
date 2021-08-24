package com.github.rebue.scx.oidc;

import com.github.rebue.scx.dto.CodeValue;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.ResponseMode;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * todo 换成redis 去掉sync
 */
@Repository
public class CodeRepository {

    private final Map<String, CodeValue> codes = new HashMap<>();

    synchronized
    public Optional<CodeValue> getCode(String code)
    {
        CodeValue value = codes.remove(code);
        return value == null ? Optional.empty() : Optional.of(value);
    }

    synchronized
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

    synchronized
    public AuthorizationCode createCode(String redirectUri, String state, String clientId, Scope scope, String userCode)
    {
        AuthorizationCode code = new AuthorizationCode(16);
        HTTPResponse success = new AuthenticationSuccessResponse(
                URI.create(redirectUri), code, null, null,
                new State(state), null, ResponseMode.QUERY).toHTTPResponse();
        String location = success.getLocation().toString();
        CodeValue cv = new CodeValue(clientId, location, scope, userCode);
        codes.put(code.getValue(), cv);
        return code;
    }

}
