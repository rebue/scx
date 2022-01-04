package rebue.scx.msg.test;

import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.ro.Ro;
import rebue.scx.msg.api.EmailMessageSendingApi;

/**
 *
 */
@Slf4j
// @RunWith(SpringRunner.class)
@SpringBootTest
public class MessageSendingtest {

    /**
     * 要测试的API
     *
     */
    @DubboReference
    private EmailMessageSendingApi emailMessageSendingApi;

    @Test
    public void testCrud() {
        Ro<?> sendTemplateSMS = emailMessageSendingApi.sendEmailTemple(new String[] { "1782271387@qq.com"
        }, "123456");
        log.info("*************" + sendTemplateSMS + "****************************");
    }

}
