package rebue.scx.orp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OidcConfig {

    @Value("${oidc.login-url}")
    private String loginUrl;

    private static OidcConfig self;

    @PostConstruct
    private void init()
    {
        OidcConfig.self = this;
    }

    public static String getLoginUrl()
    {
        return self.loginUrl;
    }

    public static final String AUTH_INFO = "auth_info";

    public static final long CODE_FLOW_LOGIN_PAGE_COOKIE_AGE = 100000L; // todo

    // 单位是秒
    public static final long ACCESS_TOKEN_LIFETIME = 60 * 60;

    // 单位是秒
    public static final long REFRESH_TOKEN_LIFETIME = 7 * 24 * 60 * 60;

}
