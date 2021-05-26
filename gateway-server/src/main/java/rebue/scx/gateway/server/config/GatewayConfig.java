package rebue.scx.gateway.server.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import rebue.scx.gateway.server.properties.MaxFileSizeProperties;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(MaxFileSizeProperties.class)
public class GatewayConfig {

}
