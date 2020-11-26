package rebue.scx.rac.test.http;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.ex.SignUpByUserNameTo;
import rebue.wheel.JacksonUtils;
import rebue.wheel.http.HttpClient;
import rebue.wheel.http.impl.OkHttpClientImpl;
import rebue.wheel.turing.DigestUtils;

/**
 * HTTP测试
 */
@Slf4j
public class RacSignUpHttpTests {

    private final String     _hostUrl    = "http://127.0.0.1:9605";

    private final HttpClient _httpClient = new OkHttpClientImpl();

    /**
     * 测试通过用户名称注册
     */
    @Test
    public void testSignUpByUserName() throws IOException, ReflectiveOperationException {
        final SignUpByUserNameTo to = new SignUpByUserNameTo();
        to.setUserName("admin");
        to.setSysId("rebue-platform");
        to.setSignInPswd(DigestUtils.md5AsHexStrX32("9527".getBytes()));
        log.info("测试通过用户名称注册: to-{}", to);
        final String result = _httpClient.postByJsonParams(_hostUrl + "/sign-up/sign-up-by-user-name", to);
        log.info("通过用户名称注册的返回值为：" + result);
        final Ro<SignUpOrInRa> ro = JacksonUtils.deserialize(result, new TypeReference<Ro<SignUpOrInRa>>() {
        });
        log.info("通过用户名称注册的返回值为: {}", ro);
        Assertions.assertEquals(ResultDic.SUCCESS, ro.getResult());
    }
}
