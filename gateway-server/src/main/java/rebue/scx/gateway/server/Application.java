package rebue.scx.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
