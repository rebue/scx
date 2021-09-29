package rebue.scx.orp.core.strategy;

import lombok.SneakyThrows;
import rebue.scx.orp.core.cache.StateCache;
import rebue.scx.orp.core.config.StrategyConfig;
import rebue.scx.orp.core.dic.OrpTypeDic;
import rebue.scx.orp.core.mo.ClientMo;
import rebue.scx.orp.core.to.AuthCodeTo;
import rebue.scx.orp.core.to.AuthTo;
import rebue.wheel.net.httpclient.HttpClient;

import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

public class OidcStrategy extends AbstractStrategy<AuthCodeTo, Void, Void, AuthCodeTo, Void> {

    private final StateCache stateCache;

    private final String callUri;

    private final String redirectUri;

    private final String clientId;

    public OidcStrategy(StrategyConfig orpConfig, Map<String, ClientMo> clients, StateCache stateCache, HttpClient httpClient, Map<String, String> extras)
    {
        super(orpConfig, clients, stateCache, httpClient);
        this.stateCache = stateCache;
        this.callUri = extras.get("call-uri");
        this.redirectUri = extras.get("redirect-uri");
        this.clientId = extras.get("client-id");
    }

    @Override
    protected void checkAuthTo(final AuthTo authTo)
    {
    }

    @Override
    protected void fillAuthToDefaultValue(AuthTo authTo)
    {
    }

    @Override
    protected void mapperRegister()
    {
    }

    @Override
    protected AuthCodeTo genGetUserInfoTo(AuthCodeTo authCodeTo)
    {
        return null;
    }

    @Override
    protected void checkGetUserInfoRo(Void unused)
    {
    }

    @Override
    public OrpTypeDic getOrpType()
    {
        return OrpTypeDic.Oidc;
    }

    @SneakyThrows
    @Override
    public String getAuthUrl(final AuthTo authTo)
    {
        String state = UUID.randomUUID().toString();
        // stateCache.set(OrpTypeDic.Oidc.name(), authTo.getClientId(), state);
        String authUrl = callUri + "/oap-svr/oap/authorize?scope=openid&response_type=code"
                + "&client_id=" + clientId
                + "&state=" + state
                + "&redirect_uri=";
        if (clientId.equals(authTo.getClientId())) {
            authUrl += URLEncoder.encode(redirectUri + "/orp-svr/orp/callback", "utf-8");
        } else {
            authUrl += authTo.getRedirectUri();
        }
        return authUrl;
    }

}
