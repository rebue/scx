package rebue.scx.doc;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringCloudApplication
@EnableCaching
public class DocApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DocApplication.class, args);
    }

}