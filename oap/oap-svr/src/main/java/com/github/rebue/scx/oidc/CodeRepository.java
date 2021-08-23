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

@Repository
public class CodeRepository {

    private final Map<String, CodeValue> codes = new HashMap<>();

    synchronized
    public AuthorizationCode createCode(AuthenticationRequest aRequest)
    {
        return createCode(
                AuthorisationCodeFlow.getRedirectUri(aRequest),
                aRequest.getState().getValue(),
                aRequest.getClientID().getValue(),
                aRequest.getScope()
        );
    }

    synchronized
    public AuthorizationCode createCode(String redirectUri, String state, String clientId, Scope scope)
    {
        AuthorizationCode code = new AuthorizationCode(16);
        HTTPResponse success = new AuthenticationSuccessResponse(
                URI.create(redirectUri), code, null, null,
                new State(state), null, ResponseMode.QUERY).toHTTPResponse();
        String location = success.getLocation().toString();
        CodeValue cv = new CodeValue(clientId, location, scope);
        codes.put(code.getValue(), cv);
        return code;
    }

}
