package rebue.scx.msg.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.ro.Ro;
import rebue.scx.msg.svc.impl.EmailMessageSendingSvcImpl;
import rebue.scx.msg.util.MailUtil;

// @RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootMailTest {

    @Autowired
    private JavaMailSender             javaMailSender;
    @Resource
    private MailUtil                   mailUtil;
    @Resource
    private EmailMessageSendingSvcImpl emailMessageSendingSvcImpl;

    @Test
    @Disabled
    public void testCrud() throws MessagingException, IOException {
        final MimeMessage       mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper message     = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setFrom("1343782084@qq.com");
        message.setTo("1782271387@qq.com");
        message.setSubject("my subject");
        // message.setText(contentString, content);
        // FileSystemResource res = new FileSystemResource(new File("/home/yuanman/git/scx/cap/cap-svr/src/main/resources/images/jigsaw/original/bg1.png"));
        // File file = new File("/home/yuanman/git/scx/cap/cap-svr/src/main/resources/images/jigsaw/original/bg1.png");
        message.setText("my text <img src=\'cid:myL\'>", true);
        message.addInline("myL", new ClassPathResource("bg1.png"));
        message.addAttachment("myDocument.xml", new ClassPathResource("log4j2.xml"));

        javaMailSender.send(mimeMessage);
    }

    @Test
    public void testCrud2() throws MessagingException, IOException {
        // final String from = "1343782084@qq.com";
        final String        title     = "验证码校验";
        final String        mailAddrs = "1782271387@qq.com";
        final String        code      = getSixRandom();
        final StringBuilder sb        = new StringBuilder();
        sb.append("<html><body>");
        sb.append("您好：");
        sb.append("<br/>");
        sb.append("感谢您对xxx的支持，您的验证码是：");
        sb.append(code);
        sb.append("  有效期5分钟。如非本人操作请忽略此邮箱。谢谢！");
        sb.append("</body></html>");
        final Map<String, String> mapImg  = new HashMap<>();
        final String              cid     = "imgCid";
        final String              imgPath = "bg1.png";
        mapImg.put(cid, imgPath);
        final Map<String, String> mapAttachment = new HashMap<>();
        final String              fileName      = "附件名称.xml";
        final String              filePath      = "log4j2.xml";
        mapAttachment.put(fileName, filePath);
        // mailUtil.sendMail(from, mailAddrs, title, sb.toString());
        final Ro<?> ro = emailMessageSendingSvcImpl.sendSimpleMail(mailAddrs, title, sb.toString());
        log.info(ro.toString());
        // mailUtil.sendMail(from, mailAddrs, title, sb.toString(), mapImg, mapAttachment);
    }

    /**
     * 产生6位随机数(000000-999999)校验码
     *
     * @return 6位随机数
     */
    public static String getSixRandom() {
        return StringUtils.leftPad(new Random().nextInt(1000000) + "", 6, "0");
    }
}
