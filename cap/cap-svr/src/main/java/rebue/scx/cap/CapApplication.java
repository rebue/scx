package rebue.scx.cap;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@EnableCaching
public class CapApplication {

    public static void main(final String[] args) {
        SpringApplication.run(CapApplication.class, args);
    }

}