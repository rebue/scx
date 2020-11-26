package rebue.scx.rac.test.http;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.ex.SignInByUserNameTo;
import rebue.wheel.JacksonUtils;
import rebue.wheel.http.HttpClient;
import rebue.wheel.http.impl.OkHttpClientImpl;
import rebue.wheel.turing.DigestUtils;

/**
 * HTTP测试
 */
@Slf4j
public class RacSignInHttpTests {

    private final String     _hostUrl    = "http://127.0.0.1:9605";

    private final HttpClient _httpClient = new OkHttpClientImpl();

    /**
     * 测试通过用户名称登录
     */
    @Test
    public void testSignUpByUserName() throws IOException {
        log.info("测试错误的系统ID");
        SignInByUserNameTo to = new SignInByUserNameTo();
        to.setSysId("aaa");
        to.setUserName("admin");
        to.setSignInPswd(DigestUtils.md5AsHexStrX32("9527".getBytes()));
        Ro<SignUpOrInRa> ro = signInByUserName(to);
        Assertions.assertEquals(ResultDic.FAIL, ro.getResult());
        Assertions.assertEquals("未发现此系统信息: aaa", ro.getMsg());

        log.info("测试错误的Email");
        to = new SignInByUserNameTo();
        to.setSysId("rebue-platform");
        to.setUserName("admin@qq.com");
        to.setSignInPswd(DigestUtils.md5AsHexStrX32("9527".getBytes()));
        ro = signInByUserName(to);
        Assertions.assertEquals(ResultDic.FAIL, ro.getResult());
        Assertions.assertEquals("", ro.getMsg());

    }

    private Ro<SignUpOrInRa> signInByUserName(SignInByUserNameTo to) throws IOException {
        String url = _hostUrl + "/sign-in/sign-in-by-user-name";
        log.info("测试通过用户名称登录: to-{}", to);
        final String result = _httpClient.postByJsonParams(url, to);
        log.info("通过用户名称注册的返回值为：" + result);
        final Ro<SignUpOrInRa> ro = JacksonUtils.deserialize(result, new TypeReference<Ro<SignUpOrInRa>>() {
        });
        log.info("通过用户名称注册的返回值为: {}", ro);
        return ro;
    }
}
