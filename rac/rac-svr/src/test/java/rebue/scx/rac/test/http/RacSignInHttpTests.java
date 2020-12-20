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

    // private final String _hostUrl = "http://127.0.0.1:9605";
    private final String     _hostUrl    = "http://127.0.0.1:8080/rac-svr";

    private final HttpClient _httpClient = new OkHttpClientImpl();

    /**
     * 测试通过用户名称登录
     */
    @Test
    public void testSignInByUserName() throws IOException {
        log.info("测试错误的系统ID");
        SignInByUserNameTo to = new SignInByUserNameTo();
        to.setSysId("aaa");
        to.setUserName("admin");
        to.setSignInPswd(DigestUtils.md5AsHexStrX32("9527".getBytes()));
        Ro<SignUpOrInRa> ro = signInByUserName(to);
        Assertions.assertEquals(ResultDic.FAIL, ro.getResult());
        Assertions.assertEquals("未发现此系统信息: aaa", ro.getMsg());

        log.info("测试找不到此用户");
        to = new SignInByUserNameTo();
        to.setSysId("platform-admin-web");
        to.setUserName("admin@qq.com");
        to.setSignInPswd(DigestUtils.md5AsHexStrX32("9527".getBytes()));
        ro = signInByUserName(to);
        Assertions.assertEquals(ResultDic.WARN, ro.getResult());
        Assertions.assertEquals("找不到此用户: admin@qq.com", ro.getMsg());

        log.info("测试admin用户登录:错误的密码");
        to = new SignInByUserNameTo();
        to.setSysId("platform-admin-web");
        to.setUserName("admin");
        to.setSignInPswd(DigestUtils.md5AsHexStrX32("95271".getBytes()));
        ro = signInByUserName(to);
        Assertions.assertEquals(ResultDic.WARN, ro.getResult());
        Assertions.assertTrue(ro.getMsg().startsWith("密码错误"));

        log.info("测试admin用户登录:正确的密码");
        to.setSignInPswd(DigestUtils.md5AsHexStrX32("9527".getBytes()));
        ro = signInByUserName(to);
        Assertions.assertEquals(ResultDic.SUCCESS, ro.getResult());
    }

    private Ro<SignUpOrInRa> signInByUserName(final SignInByUserNameTo to) throws IOException {
        final String url = _hostUrl + "/sign-in/sign-in-by-user-name";
        log.info("测试通过用户名称登录: to-{}", to);
        final String result = _httpClient.postByJsonParams(url, to);
        log.info("通过用户名称注册的返回值为：" + result);
        final Ro<SignUpOrInRa> ro = JacksonUtils.deserialize(result, new TypeReference<Ro<SignUpOrInRa>>() {
        });
        log.info("通过用户名称注册的返回值为: {}", ro);
        return ro;
    }
}
