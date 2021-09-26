package rebue.scx.etl;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@EnableCaching
public class EtlApplication {

    public static void main(final String[] args) {
        SpringApplication.run(EtlApplication.class, args);
    }

}