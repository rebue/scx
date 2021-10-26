package rebue.scx.msg.test;

import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import rebue.robotech.ro.Ro;
import rebue.scx.msg.svc.impl.TemplateMessageSendingSvcImpl;
import rebue.scx.msg.to.MsgSMSVerificationTo;
import rebue.wheel.net.httpclient.HttpClient;
import rebue.wheel.net.httpclient.impl.ApacheHttpClientImpl;

@Slf4j
@SpringBootTest
public class TemplateMessageSendingSvcTest {
    private static final StringRedisTemplate           stringRedisTemplate = new StringRedisTemplate();

    private static final HttpClient                    httpClient          = new ApacheHttpClientImpl();
    private final static TemplateMessageSendingSvcImpl svc                 = new TemplateMessageSendingSvcImpl();

    @Test
    public static void main(final String[] args) {
        init();
        test01();
        test02();
    }

    /**
     * 初始化redis连接
     */
    public static void init() {
        final LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory("127.0.0.1", 6379);
        connectionFactory.afterPropertiesSet();
        stringRedisTemplate.setConnectionFactory(connectionFactory);
        stringRedisTemplate.afterPropertiesSet();
    }

    @SneakyThrows
    public static void test01() {
        // svc.setStringRedisTemplate(stringRedisTemplate);
        Ro<?> sendTemplateSMS = svc.sendTemplateSMS("18775885903");
        System.out.println("*************" + sendTemplateSMS + "****************************");
    }

    @SneakyThrows
    public static void test02() {
        String               code;
        String               phoneNumber;
        MsgSMSVerificationTo to = new MsgSMSVerificationTo();
        try (Scanner s = new Scanner(System.in)) {
            System.out.println("************************************************************************");
            System.out.println("************************************************************************");
            System.out.println("请输入code，并按回车: ");
            code = s.nextLine().trim();
            System.out.println("请输入phoneNumber，并按回车: ");
            phoneNumber = s.nextLine().trim();
        }
        to.setCode(code);
        to.setPhoneNumber(phoneNumber);
        Ro<?> msgSMSVerification = svc.msgSMSVerification(to);
        System.out.println("**************" + msgSMSVerification + "******************");

    }

}
