package rebue.scx.sgn;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@EnableCaching
public class SgnApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SgnApplication.class, args);
    }

}