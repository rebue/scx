package rebue.scx.rac.test.http;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.ra.SignUpRa;
import rebue.scx.rac.to.SignUpByUserNameTo;
import rebue.wheel.http.HttpClient;
import rebue.wheel.http.impl.OkHttpClientImpl;
import rebue.wheel.turing.DigestUtils;

/**
 * 学生信息 HTTP测试
 */
@Slf4j
public class RacSignUpHttpTests {

    private final String       _hostUrl      = "http://127.0.0.1:9605";

    private final ObjectMapper _objectMapper = new ObjectMapper();

    private final HttpClient   _httpClient   = new OkHttpClientImpl();

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
        final String result = _httpClient.postByJsonParams(_hostUrl + "/api/sign-up/sign-up-by-user-name", to);
        log.info("通过用户名称注册的返回值为：" + result);
        final Ro<SignUpRa> ro = _objectMapper.readValue(result, new TypeReference<Ro<SignUpRa>>() {
        });
        log.info("通过用户名称注册的返回值为: {}", ro);
        Assertions.assertEquals(ResultDic.SUCCESS, ro.getResult());
    }
}
