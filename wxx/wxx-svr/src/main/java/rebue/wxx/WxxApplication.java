package rebue.wxx;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@EnableCaching
public class WxxApplication {

    public static void main(final String[] args) {
        SpringApplication.run(WxxApplication.class, args);
    }

}