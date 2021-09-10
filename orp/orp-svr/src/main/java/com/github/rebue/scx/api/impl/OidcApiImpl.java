package com.github.rebue.scx.api.impl;

import com.github.rebue.scx.api.OidcApi;
import com.github.rebue.scx.svc.OidcSvc;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.http.ResponseCookie;

import javax.annotation.Resource;

@DubboService
public class OidcApiImpl implements OidcApi {

    @Resource
    private OidcSvc oidcSvc;

    @Override
    public Triple<String, String, ResponseCookie> callback(String code)
    {
        return oidcSvc.callback(code);
    }

}
