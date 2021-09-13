package rebue.scx.orp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OidcConfig {

    @Value("${oidc.login-url}")
    private String loginUrl;

    @Value("${oidc.cookie-domain}")
    private String cookieDomain;

    @PostConstruct
    private void init()
    {
        OidcConfig.LOGIN_URL = loginUrl;
        OidcConfig.CODE_FLOW_LOGIN_PAGE_COOKIE_DOMAIN = cookieDomain;
    }

    public static String LOGIN_URL;

    public static String CODE_FLOW_LOGIN_PAGE_COOKIE_DOMAIN;

    public static final String AUTH_INFO = "auth_info";

    public static final long CODE_FLOW_LOGIN_PAGE_COOKIE_AGE = 100000L; // todo

    // 单位是秒
    public static final long ACCESS_TOKEN_LIFETIME = 60 * 60;

    // 单位是秒
    public static final long REFRESH_TOKEN_LIFETIME = 7 * 24 * 60 * 60;

}
