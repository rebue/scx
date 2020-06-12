package rebue.scx.rac.test.api;

import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.scx.rac.api.RacSignUpApi;
import rebue.scx.rac.ro.SignUpRo;
import rebue.scx.rac.to.SignUpByUserNameTo;
import rebue.wheel.turing.DigestUtils;

/**
 * 用户注册测试
 */
@Slf4j
@SpringBootTest
public class RacSignUpApiTests {

    /**
     * 要测试的微服务
     */
    @Reference
    private RacSignUpApi _svc;

    /**
     * 测试通过用户名称注册
     */
    @Test
    public void testSignUpByUserName() {
        final SignUpByUserNameTo to = new SignUpByUserNameTo();
        to.setUserName("admin");
        to.setSysId("rebue-platform");
        to.setSignInPswd(DigestUtils.md5AsHexStrX32("9527".getBytes()));
        log.info("测试通过用户名称注册: to-{}", to);
        final SignUpRo ro = _svc.signUpByUserName(to);
        log.info("通过用户名称注册的返回值为: {}", ro);
        Assertions.assertEquals(ResultDic.SUCCESS, ro.getResult());
    }
}
