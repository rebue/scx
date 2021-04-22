package rebue.scx.rac.test.http.ex;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.ex.SignInByAccountNameTo;
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
    private final String     _hostUrl    = "http://127.0.0.1:10080/rac-svr";

    private final HttpClient _httpClient = new OkHttpClientImpl();

    /**
     * 测试通过账户名称登录
     */
    @Test
    public void testSignInByAccountName() throws IOException {
        log.info("测试错误的系统ID");
        SignInByAccountNameTo to = new SignInByAccountNameTo();
        to.setSysId("aaa");
        to.setAccountName("admin");
        to.setSignInPswd(DigestUtils.md5AsHexStrX32("9527".getBytes()));
        Ro<SignUpOrInRa> ro = signInByAccountName(to);
        Assertions.assertEquals(ResultDic.FAIL, ro.getResult());
        Assertions.assertEquals("未发现此系统信息: aaa", ro.getMsg());

        log.info("测试找不到此账户");
        to = new SignInByAccountNameTo();
        to.setSysId("platform-admin-web");
        to.setAccountName("admin@qq.com");
        to.setSignInPswd(DigestUtils.md5AsHexStrX32("9527".getBytes()));
        ro = signInByAccountName(to);
        Assertions.assertEquals(ResultDic.WARN, ro.getResult());
        Assertions.assertEquals("找不到此账户: admin@qq.com", ro.getMsg());

        log.info("测试admin账户登录:错误的密码");
        to = new SignInByAccountNameTo();
        to.setSysId("platform-admin-web");
        to.setAccountName("admin");
        to.setSignInPswd(DigestUtils.md5AsHexStrX32("95271".getBytes()));
        ro = signInByAccountName(to);
        Assertions.assertEquals(ResultDic.WARN, ro.getResult());
        Assertions.assertTrue(ro.getMsg().startsWith("密码错误"));

        log.info("测试admin账户登录:正确的密码");
        to.setSignInPswd(DigestUtils.md5AsHexStrX32("9527".getBytes()));
        ro = signInByAccountName(to);
        Assertions.assertEquals(ResultDic.SUCCESS, ro.getResult());
    }

    private Ro<SignUpOrInRa> signInByAccountName(final SignInByAccountNameTo to) throws IOException {
        final String url = _hostUrl + "/rac/sign-in/sign-in-by-account-name";
        log.info("测试通过账户名称登录: to-{}", to);
        final String result = _httpClient.postByJsonParams(url, to);
        log.info("通过账户名称注册的返回值为：" + result);
        final Ro<SignUpOrInRa> ro = JacksonUtils.deserialize(result, new TypeReference<Ro<SignUpOrInRa>>() {
        });
        log.info("通过账户名称注册的返回值为: {}", ro);
        return ro;
    }
}
