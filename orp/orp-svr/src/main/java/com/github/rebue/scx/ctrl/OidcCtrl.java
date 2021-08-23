package com.github.rebue.scx.ctrl;

import com.github.rebue.scx.svc.OidcSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oidc")
public class OidcCtrl {

    @Autowired
    private OidcSvc oidcSvc;

    @GetMapping("/callback")
    public void callback(String code)
    {
        oidcSvc.callback(code);
    }

}