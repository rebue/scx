package rebue.scx.msg;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 消息
 * 
 * @author yuanman
 *
 */
@SpringCloudApplication
@EnableCaching
public class MsgApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MsgApplication.class, args);
    }

}