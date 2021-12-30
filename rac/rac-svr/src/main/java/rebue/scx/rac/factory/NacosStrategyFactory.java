package rebue.scx.rac.factory;

import rebue.scx.rac.config.AllNacosConfig.NacosStrategyProperties;
import rebue.scx.rac.dic.NacosTypeDic;
import rebue.scx.rac.strategy.nacos.NacosStrategy;
import rebue.scx.rac.strategy.nacos.OrpNacosStrategy;
import rebue.scx.rac.strategy.nacos.RacNacosStrategy;
import rebue.wheel.api.exception.RuntimeExceptionX;

public class NacosStrategyFactory {

    public static NacosStrategy getStrategy(NacosTypeDic nameType, String serverAddr, String active, NacosStrategyProperties strategyProperies) {
        switch (nameType) {
        case RacSvr:
            return new RacNacosStrategy(nameType, serverAddr, active, strategyProperies);
        case OrpSvr:
            return new OrpNacosStrategy(nameType, serverAddr, active, strategyProperies);
        default:
            throw new RuntimeExceptionX("不能识别的RP类型: " + nameType.name() + "(不会运行到此处)");
        }
    }

}
