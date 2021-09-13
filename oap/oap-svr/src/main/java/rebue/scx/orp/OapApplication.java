package rebue.scx.orp;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@EnableCaching
public class OapApplication {

    public static void main(final String[] args)
    {
        SpringApplication.run(OapApplication.class, args);
    }

}