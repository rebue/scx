package rebue.scx.orp.core.test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import rebue.scx.orp.core.cache.StateCache;
import rebue.scx.orp.core.config.StrategyConfig;
import rebue.scx.orp.core.dic.OrpTypeDic;
import rebue.scx.orp.core.factory.StrategyFactory;
import rebue.scx.orp.core.mo.ClientMo;
import rebue.scx.orp.core.strategy.Strategy;
import rebue.scx.orp.core.to.AuthCodeTo;
import rebue.scx.orp.core.to.AuthTo;
import rebue.scx.orp.ra.OrpUserInfoRa;
import rebue.wheel.net.httpclient.HttpClient;
import rebue.wheel.net.httpclient.impl.ApacheHttpClientImpl;

@Slf4j
public class OrpTests {
    private static final StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();

    private static final HttpClient          httpClient          = new ApacheHttpClientImpl();

    public static void main(final String[] args) {
        init();
        test01();
        // test02();
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

    /**
     * 获取钉钉认证的URL，并认证授权码
     */
    @SneakyThrows
    public static void test01() {
        String                clientId     = "dingsahvlqybvb44jd50";
        String                clientSecret = "hWt9A-ebYcR30DoXqJHLcka62jpR3ZKMc6GFizNW-5xczbahLr0yf7plEO7ewm80";
        String                redirectUri  = "http://www.abc.com";

        Map<String, ClientMo> clients      = new HashMap<>();
        clients.put(clientId, ClientMo.builder().id(clientId).secret(clientSecret).build());

        StrategyConfig orpConfig  = StrategyConfig.builder().isCheckState(true).build();
        StateCache     stateCache = new StateCache(stringRedisTemplate, Duration.ofMinutes(5));
        Strategy       strategy   = StrategyFactory.getStrategy(OrpTypeDic.DingTalk, clients, orpConfig, stateCache, httpClient, null);
        String         authUrl    = strategy.getAuthUrl(AuthTo.builder().clientId(clientId).redirectUri(redirectUri).build());
        log.info("获取钉钉认证的URL为: {}", authUrl);

        String code;
        String state;
        try (Scanner s = new Scanner(System.in)) {
            System.out.println("************************************************************************");
            System.out.println("* 请点击上面的链接，用钉钉扫码后，输入链接中的code和state继续往下执行");
            System.out.println("************************************************************************");
            System.out.println("请输入code，并按回车: ");
            code = s.nextLine().trim();
            System.out.println("请输入state，并按回车: ");
            state = s.nextLine().trim();
        }

        OrpUserInfoRa ro = strategy.authCode(AuthCodeTo.builder()
                .clientId(clientId)
                .code(code)
                .state(state)
                .build());
        log.info("钉钉认证授权码的结果为: {}", ro);
    }

    /**
     * 获取微信认证的URL, 并认证授权码
     */
    @SneakyThrows
    public static void test02() {
        String                clientId     = "wxc9bcdf70da391cdd";
        String                clientSecret = "4a215a29ccf5d91c2c8ea2af12a57e93";
        String                redirectUri  = "http://maiyuesoft.com";

        Map<String, ClientMo> clients      = new HashMap<>();
        clients.put(clientId, ClientMo.builder().id(clientId).secret(clientSecret).build());

        StrategyConfig orpConfig  = StrategyConfig.builder().isCheckState(true).build();
        StateCache     stateCache = new StateCache(stringRedisTemplate, Duration.ofMinutes(5));
        Strategy       strategy   = StrategyFactory.getStrategy(OrpTypeDic.WechatOpen, clients, orpConfig, stateCache, httpClient, null);
        String         authUrl    = strategy.getAuthUrl(AuthTo.builder().clientId(clientId).redirectUri(redirectUri).build());
        log.info("获取微信认证的URL为: {}", authUrl);

        String code;
        String state;
        try (Scanner s = new Scanner(System.in)) {
            System.out.println("************************************************************************");
            System.out.println("* 请点击上面的链接，用微信扫码后，输入链接中的code和state继续往下执行");
            System.out.println("************************************************************************");
            System.out.println("请输入code，并按回车: ");
            code = s.nextLine().trim();
            System.out.println("请输入state，并按回车: ");
            state = s.nextLine().trim();
        }

        OrpUserInfoRa ro = strategy.authCode(AuthCodeTo.builder()
                .clientId(clientId)
                .code(code)
                .state(state)
                .build());
        log.info("微信认证授权码的结果为: {}", ro);
    }

}
