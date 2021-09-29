package rebue.scx.orp.core.strategy;

import rebue.scx.orp.core.cache.StateCache;
import rebue.scx.orp.core.config.StrategyConfig;
import rebue.scx.orp.core.dic.OrpTypeDic;
import rebue.scx.orp.core.mo.ClientMo;
import rebue.scx.orp.core.to.AuthCodeTo;
import rebue.scx.orp.core.to.AuthTo;
import rebue.wheel.net.httpclient.HttpClient;

import java.util.Map;

public class OidcStrategy extends AbstractStrategy<AuthCodeTo, Void, Void, AuthCodeTo, Void> {

    public OidcStrategy(StrategyConfig orpConfig, Map<String, ClientMo> clients, StateCache stateCache, HttpClient httpClient, Map<String, String> extras)
    {
        super(orpConfig, clients, stateCache, httpClient);
    }

    @Override
    protected void mapperRegister()
    {
        System.out.println();
    }

    @Override
    protected void fillAuthToDefaultValue(AuthTo authTo)
    {
        System.out.println();
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
        return null;
    }

    @Override
    protected String authUrl()
    {
        return super.authUrl();
    }

}
