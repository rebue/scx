package rebue.scx.orp.config;

import java.util.Map;

import lombok.Builder;
import lombok.Data;
import rebue.scx.orp.core.strategy.Strategy;

@Data
@Builder
public class OrpBeans {

    Map<String, String>   apps;

    Map<String, Strategy> strategies;

}
