package rebue.scx.cap;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringCloudApplication
@EnableCaching
public class CapApplication {

    public static void main(final String[] args) {
        SpringApplication.run(CapApplication.class, args);
    }

}