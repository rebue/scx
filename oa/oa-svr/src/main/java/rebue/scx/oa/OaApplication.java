package rebue.scx.oa;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringCloudApplication
@EnableCaching
public class OaApplication {

    public static void main(final String[] args) {
        SpringApplication.run(OaApplication.class, args);
    }

}