package com.github.rebue.third.party.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
public class Configurations {

    public static final String LOGIN_COOKIE = "login_cookie";

    @Getter
    @Value("${demo-configurations.client-id}")
    private String clientId;

    @Getter
    @Value("${demo-configurations.client-secret}")
    private String clientSecret;

    @Getter
    @Value("${demo-configurations.issuer}")
    private String issuer;

    @Getter
    @Value("${demo-configurations.redirect}")
    private String redirect;

    @Getter
    @Value("${demo-configurations.auth-uri}")
    private String authUri;

    @Getter
    @Value("${demo-configurations.token-endpoint}")
    private String tokenEndpoint;

}
