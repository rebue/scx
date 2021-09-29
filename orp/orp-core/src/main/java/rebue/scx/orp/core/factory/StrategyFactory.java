package rebue.scx.orp.core.factory;

import java.util.Map;

import rebue.scx.orp.core.cache.StateCache;
import rebue.scx.orp.core.config.StrategyConfig;
import rebue.scx.orp.core.dic.OrpTypeDic;
import rebue.scx.orp.core.mo.ClientMo;
import rebue.scx.orp.core.strategy.DingTalkStrategy;
import rebue.scx.orp.core.strategy.OidcStrategy;
import rebue.scx.orp.core.strategy.Strategy;
import rebue.scx.orp.core.strategy.WechatOpenStrategy;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.net.httpclient.HttpClient;

/**
 * 策略工厂
 */
public class StrategyFactory {

    public static Strategy getStrategy(final OrpTypeDic orpType, final Map<String, ClientMo> clients, final StrategyConfig orpConfig, final StateCache stateCache,
            final HttpClient httpClient) {
        switch (orpType) {
        case DingTalk:
            return new DingTalkStrategy(orpConfig, clients, stateCache, httpClient);
        case WechatOpen:
            return new WechatOpenStrategy(orpConfig, clients, stateCache, httpClient);
        case Oidc:
            return new OidcStrategy(orpConfig, clients, stateCache, httpClient);
        default:
            throw new RuntimeExceptionX("不能识别的RP类型: " + orpType.name() + "(不会运行到此处)");
        }
    }
}
