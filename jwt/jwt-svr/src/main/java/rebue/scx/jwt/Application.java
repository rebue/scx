package rebue.scx.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(//
        title = "JWT微服务", //
        version = "1.2.0", //
        description = "基于SpringCloud架构提供JWT服务"//
))
@SpringCloudApplication
//@ComponentScan(basePackages = { "rebue" })
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
