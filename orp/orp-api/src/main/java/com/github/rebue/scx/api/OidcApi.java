package com.github.rebue.scx.api;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.http.ResponseCookie;

public interface OidcApi {

    Triple<String, String, ResponseCookie> callback(String code);

}
