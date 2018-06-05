package rebue.scx.zuul.server;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableZuulProxy
@RefreshScope
//@EnableAutoConfiguration(exclude = { rebue.sbs.smx.JacksonConfig.class })
@EnableFeignClients(basePackages = { "rebue.jwt.svr.feign" })
public class ZuulServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
}
