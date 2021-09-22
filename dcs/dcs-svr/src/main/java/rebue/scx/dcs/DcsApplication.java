package rebue.scx.dcs;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringCloudApplication
@EnableCaching
public class DcsApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DcsApplication.class, args);
    }

}