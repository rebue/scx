package rebue.scx.etl;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringCloudApplication
@EnableCaching
public class EtlApplication {

    public static void main(final String[] args) {
        SpringApplication.run(EtlApplication.class, args);
    }

}