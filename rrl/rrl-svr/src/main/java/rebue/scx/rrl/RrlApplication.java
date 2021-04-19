package rebue.scx.rrl;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@EnableCaching
public class RrlApplication {

    public static void main(final String[] args) {
        SpringApplication.run(RrlApplication.class, args);
    }

}