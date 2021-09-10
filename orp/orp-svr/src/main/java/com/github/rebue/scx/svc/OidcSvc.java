package com.github.rebue.scx.svc;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.http.ResponseCookie;

public interface OidcSvc {

    Triple<String, String, ResponseCookie> callback(String code);

}
