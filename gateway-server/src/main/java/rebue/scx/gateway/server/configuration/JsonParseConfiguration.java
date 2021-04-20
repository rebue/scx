package rebue.scx.gateway.server.configuration;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class JsonParseConfiguration {

    @Bean
    public JsonParser getJsonParser() {
        return JsonParserFactory.getJsonParser();
    }

}
