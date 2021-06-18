package rebue.scx.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@EnableCaching
public class OssApplication {

    public static void main(final String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }

}