package rebue.scx.rac.test.api;

import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacSignUpApi;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.ex.SignUpByUserNameTo;
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
    @DubboReference
    private RacSignUpApi _api;

    /**
     * 测试通过用户名称注册
     */
    @Test
    public void testSignUpByUserName() {
        Ro<SignUpOrInRa> ro = _api.signUpByUserName(null);
        log.info("通过用户名称注册的返回值为: {}", ro);
        Assertions.assertEquals(ResultDic.PARAM_ERROR, ro.getResult());
        final SignUpByUserNameTo to = new SignUpByUserNameTo();
        to.setUserName("admin");
        to.setSysId("rebue-platform");
        log.info("测试通过用户名称注册，缺少登录密码: to-{}", to);
        ro = _api.signUpByUserName(to);
        log.info("通过用户名称注册的返回值为: {}", ro);
        Assertions.assertEquals(ResultDic.PARAM_ERROR, ro.getResult());
        to.setSignInPswd(DigestUtils.md5AsHexStrX32("9527".getBytes()));
        log.info("测试通过用户名称注册: to-{}", to);
        ro = _api.signUpByUserName(to);
        log.info("通过用户名称注册的返回值为: {}", ro);
        Assertions.assertEquals(ResultDic.SUCCESS, ro.getResult());
    }
}
