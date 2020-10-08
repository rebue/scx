package rebue.scx.rac;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 资源访问控制微服务
 * 
 * @author zbz
 *
 */
@SpringCloudApplication
@EnableCaching
public class RacApplication {

    public static void main(final String[] args) {
        SpringApplication.run(RacApplication.class, args);
    }

}