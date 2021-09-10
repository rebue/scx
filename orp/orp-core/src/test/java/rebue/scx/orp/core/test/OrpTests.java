package rebue.scx.orp.core.test;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import rebue.scx.orp.core.cache.StateCache;
import rebue.scx.orp.core.config.StrategyConfig;
import rebue.scx.orp.core.dic.OrpTypeDic;
import rebue.scx.orp.core.factory.StrategyFactory;
import rebue.scx.orp.core.ro.UserInfoRo;
import rebue.scx.orp.core.strategy.Strategy;
import rebue.scx.orp.core.to.AuthCodeTo;
import rebue.scx.orp.core.to.AuthTo;
import rebue.wheel.net.httpclient.HttpClient;
import rebue.wheel.net.httpclient.impl.ApacheHttpClientImpl;

import java.time.Duration;

@RunWith(SpringRunner.class)
@Slf4j
public class OrpTests {
    private static StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();

    private HttpClient                 _httpClient         = new ApacheHttpClientImpl();

    /**
     * 初始化redis连接
     */
    @BeforeAll
    public static void init() {
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory("127.0.0.1", 6379);
        connectionFactory.afterPropertiesSet();
        stringRedisTemplate.setConnectionFactory(connectionFactory);
        stringRedisTemplate.afterPropertiesSet();
    }

    /**
     * 获取钉钉认证的URL，并认证授权码
     */
    @Test
    @Disabled
    @SneakyThrows
    public void test01() {
        String         clientId     = "dingsahvlqybvb44jd50";
        String         clientSecret = "hWt9A-ebYcR30DoXqJHLcka62jpR3ZKMc6GFizNW-5xczbahLr0yf7plEO7ewm80\n";
        String         redirectUri  = "http://www.abc.com";

        StrategyConfig orpConfig    = StrategyConfig.builder().isCheckState(true).build();
        StateCache     stateCache   = new StateCache(stringRedisTemplate, Duration.ofMinutes(5));
        Strategy       strategy     = StrategyFactory.getStrategy(OrpTypeDic.DingTalk, orpConfig, stateCache);
        String         authUrl      = strategy.getAuthUrl(AuthTo.builder().clientId(clientId).redirectUri(redirectUri).build());
        log.info("获取钉钉认证的URL为: {}", authUrl);

        String authRoStr = _httpClient.get(authUrl);
        log.info("认证返回: {}", authRoStr);

        UserInfoRo ro = strategy.authCode(AuthCodeTo.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .state("STATE")
                .code("CODE")
                .build());
        log.info("认证授权码的结果为: {}", ro);
    }

    /**
     * 获取微信认证的URL, 并认证授权码
     */
    @Test
    // @Disabled
    @SneakyThrows
    public void test02() {
        String         clientId     = "wxcbe623e1635842d9";
        String         clientSecret = "0d23fc6c83325585ac232d08d4635158";
        String         redirectUri  = "http://www.abc.com";

        StrategyConfig orpConfig    = StrategyConfig.builder().isCheckState(true).build();
        StateCache     stateCache   = new StateCache(stringRedisTemplate, Duration.ofMinutes(5));
        Strategy       strategy     = StrategyFactory.getStrategy(OrpTypeDic.WeChatOpen, orpConfig, stateCache);
        String         authUrl      = strategy.getAuthUrl(AuthTo.builder().clientId(clientId).redirectUri(redirectUri).build());
        log.info("获取微信认证的URL为: {}", authUrl);

        String authRoStr = _httpClient.get(authUrl);
        log.info("认证返回: {}", authRoStr);

        UserInfoRo ro = strategy.authCode(AuthCodeTo.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .state("STATE")
                .code("CODE")
                .build());
        log.info("认证授权码的结果为: {}", ro);
    }

}
