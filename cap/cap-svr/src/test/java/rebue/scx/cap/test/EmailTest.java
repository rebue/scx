package rebue.scx.cap.test;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.api.CapMessageSendingApi;
import rebue.scx.cap.to.CapEmailTo;
import rebue.scx.msg.api.EmailMessageSendingApi;

@SpringBootTest
@Slf4j
public class EmailTest {

    @DubboReference
    private EmailMessageSendingApi emailMessageSendingApi;

    @DubboReference
    private CapMessageSendingApi   capMessageSendingApi;

    @Test
    public void testCurd() {
        String        title     = "验证码校验";
        String        mailAddrs = "1782271387@qq.com";
        String        code      = getSixRandom();
        StringBuilder sb        = new StringBuilder();
        sb.append("<html><body>");
        sb.append("<p>");
        sb.append("您好：");
        sb.append("</p>");
        sb.append("感谢您的支持，您的验证码有效期5分钟");
        sb.append("</br>");
        sb.append("<b style=\"color: #0000FF;font-size: 32px\">");
        sb.append(code);
        sb.append("</b>");
        sb.append("</br>");
        sb.append("<small style=\"color:gray;\">");
        sb.append("如果这不是您的请求，请忽略此邮件。");
        sb.append("</small>");
        sb.append("</body></html>");
        CapEmailTo to = new CapEmailTo();
        to.setEmail(mailAddrs);
        Ro<?> sendTemplateEmail = capMessageSendingApi.sendTemplateEmail(to);
        // Ro<?> sendTemplateEmail = emailMessageSendingApi.sendSimpleMail(mailAddrs, title, sb.toString());
        log.debug(sendTemplateEmail.toString());
    }

    public static String getSixRandom() {
        return StringUtils.leftPad(new Random().nextInt(1000000) + "", 6, "0");
    }
}
