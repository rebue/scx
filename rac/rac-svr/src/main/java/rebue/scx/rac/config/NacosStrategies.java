package rebue.scx.rac.config;

import java.util.Map;

import lombok.Builder;
import lombok.Data;
import rebue.scx.rac.strategy.nacos.NacosStrategy;

@Data
@Builder
public class NacosStrategies {

    Map<String, NacosStrategy> items;

}