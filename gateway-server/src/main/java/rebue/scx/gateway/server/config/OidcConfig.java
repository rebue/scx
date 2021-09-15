package rebue.scx.gateway.server.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

@Component
public class OidcConfig {

    @Value("${oidc-redirect.call-uri}")
    private String callUri;

    @Value("${oidc-redirect.client-id}")
    private String clientId;

    @Value("${oidc-redirect.redirect-uri}")
    private String redirectUri;

    @SneakyThrows
    public String getRedirectUri()
    {
        return callUri + "/oap-svr/oap/authorize?scope=openid&response_type=code"
                + "&client_id=" + clientId
                + "&state=st"
                + "&redirect_uri="
                + URLEncoder.encode(redirectUri + "/orp-svr/orp/callback", "utf-8");
    }

}
