package rebue.scx.orp;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringCloudApplication
@EnableCaching
@ComponentScans(value = { @ComponentScan(value = "rebue.scx.rac"), @ComponentScan(value = "com.github.rebue.scx")
})
public class OapApplication {

    public static void main(final String[] args) {
        SpringApplication.run(OapApplication.class, args);
    }

}