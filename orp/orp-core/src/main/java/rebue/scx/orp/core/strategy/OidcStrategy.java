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

    private StateCache stateCache;

    public OidcStrategy(StrategyConfig orpConfig, Map<String, ClientMo> clients, StateCache stateCache, HttpClient httpClient, Map<String, String> extras)
    {
        super(orpConfig, clients, stateCache, httpClient);
        this.stateCache = stateCache;
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
        _mapperFactory.classMap(AuthTo.class, Map.class)
                .field("scopes", "scope")
                .field("redirectUri", "redirect_uri")
                .byDefault().register();
    }

    @Override
    protected AuthCodeTo genGetUserInfoTo(AuthCodeTo authCodeTo)
    {
        return null;
    }

    @Override
    protected void checkGetUserInfoRo(Void unused)
    {
        System.out.println();
    }

    @Override
    public OrpTypeDic getOrpType()
    {
        return OrpTypeDic.Oidc;
    }

    String callUri = "http://127.0.0.1";     // todo 加到yml
    String redirectUri = "http://127.0.0.1"; // todo 加到yml
    String clientId = "unified-auth";        // todo 加到yml

    @SneakyThrows
    @Override
    public String getAuthUrl(final AuthTo authTo)
    {
        String state = UUID.randomUUID().toString();
        stateCache.set(OrpTypeDic.Oidc.name(), authTo.getClientId(), state);
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
