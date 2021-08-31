package com.github.rebue.scx.config;

public class OidcConfig {

    public static final String AUTH_INFO = "auth_info";

    public static final String CODE_FLOW_LOGIN_PAGE_COOKIE_DOMAIN = "127.0.0.1"; // todo

    public static final long CODE_FLOW_LOGIN_PAGE_COOKIE_AGE = 100000L; // todo

    public static final String LOGIN_URL = "http://127.0.0.1:13080/admin-web#/unifiedLogin";

    // 单位是秒
    public static final long ACCESS_TOKEN_LIFETIME = 60 * 60;

    // 单位是秒
    public static final long REFRESH_TOKEN_LIFETIME = 7 * 24 * 60 * 60;

}
