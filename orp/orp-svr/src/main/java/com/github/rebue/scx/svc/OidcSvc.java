package com.github.rebue.scx.svc;

import java.util.Optional;

public interface OidcSvc {

    Optional<String> callback(String code);

}
