package rebue.scx.orp.core.factory;

import rebue.scx.orp.core.cache.StateCache;
import rebue.scx.orp.core.config.StrategyConfig;
import rebue.scx.orp.core.dic.OrpTypeDic;
import rebue.scx.orp.core.strategy.DingTalkStrategy;
import rebue.scx.orp.core.strategy.Strategy;
import rebue.scx.orp.core.strategy.WechatOpenStrategy;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.net.httpclient.HttpClient;

/**
 * 策略工厂
 */
public class StrategyFactory {

    public static Strategy getStrategy(OrpTypeDic orpType, StrategyConfig orpConfig, StateCache stateCache, HttpClient httpClient) {
        switch (orpType) {
        case DingTalk:
            return new DingTalkStrategy(orpConfig, stateCache, httpClient);
        case WeChatOpen:
            return new WechatOpenStrategy(orpConfig, stateCache, httpClient);
        default:
            throw new RuntimeExceptionX("不能识别的RP类型: " + orpType.name() + "(不会运行到此处)");
        }
    }
}
