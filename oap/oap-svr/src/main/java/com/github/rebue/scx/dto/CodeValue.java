package com.github.rebue.scx.dto;

import com.nimbusds.oauth2.sdk.Scope;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CodeValue {

    private final String clientId;

    private final String redirectionUri;

    private final Scope scope;

    private final long accountId;

}
