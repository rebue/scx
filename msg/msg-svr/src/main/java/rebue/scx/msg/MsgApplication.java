package rebue.scx.msg;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import com.dtflys.forest.springboot.annotation.ForestScan;

/**
 * 消息
 * 
 * @author yuanman
 *
 */
@SpringCloudApplication
// @EnableCaching
@ForestScan(basePackages = "rebue.msg.fapi")
public class MsgApplication {
    public static void main(final String[] args) {
        SpringApplication.run(MsgApplication.class, args);
    }

}