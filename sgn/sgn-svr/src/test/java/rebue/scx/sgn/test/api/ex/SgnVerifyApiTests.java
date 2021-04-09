package rebue.scx.sgn.test.api.ex;

import java.security.KeyPair;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.sgn.api.SgnSecretApi;
import rebue.scx.sgn.api.ex.SgnVerifyApi;
import rebue.scx.sgn.dic.SignAlgorithmDic;
import rebue.scx.sgn.to.SgnSecretAddTo;
import rebue.wheel.RandomEx;
import rebue.wheel.turing.SignUtils;
import rebue.wheel.turing.Sm2Utils;

/**
 * 签名验证 API层测试
 */
@SpringBootTest
@Slf4j
public class SgnVerifyApiTests {

    /**
     * 要测试的API
     */
    @DubboReference
    private SgnVerifyApi _api;

    @DubboReference
    private SgnSecretApi _secretApi;

    /**
     * 测试签名验证
     *
     * @throws Exception
     */
    @Test
    public void testCrud() throws Exception {
        SgnSecretAddTo addTo = (SgnSecretAddTo) RandomEx.randomPojo(SgnSecretAddTo.class);
        addTo.setAlgorithm(SignAlgorithmDic.MD5.getCode());
        Long                      addId    = _secretApi.add(addTo).getExtra().getId();

        final Map<String, Object> paramMap = new LinkedHashMap<>();
        Ro<?>                     ro       = _api.verify(paramMap);
        log.info("返回结果: {}", ro);
        Assertions.assertEquals(ResultDic.PARAM_ERROR, ro.getResult());
        Assertions.assertEquals("验证签名错误: 没有请求参数", ro.getMsg());

        paramMap.put("A", "aaa");
        paramMap.put("B", "bbb");
        paramMap.put("C", "ccc");
        SignUtils.sign1(paramMap, "signKey");
        ro = _api.verify(paramMap);
        log.info("返回结果: {}", ro);
        Assertions.assertEquals(ResultDic.PARAM_ERROR, ro.getResult());
        Assertions.assertEquals("验证签名错误: 请求参数中没有signId", ro.getMsg());

        paramMap.put("signId", "12345678");
        SignUtils.sign1(paramMap, "sign-key-123");
        ro = _api.verify(paramMap);
        log.info("返回结果: {}", ro);
        Assertions.assertEquals(ResultDic.WARN, ro.getResult());
        Assertions.assertEquals("找不到记录", ro.getMsg());

        paramMap.put("signId", addId);
        SignUtils.sign1(paramMap, "sign-key-123");
        ro = _api.verify(paramMap);
        log.info("返回结果: {}", ro);
        Assertions.assertEquals(ResultDic.WARN, ro.getResult());
        Assertions.assertEquals("验证签名错误: 签名不正确", ro.getMsg());

        SignUtils.sign1(paramMap, addTo.getSecret());
        ro = _api.verify(paramMap);
        log.info("返回结果: {}", ro);
        Assertions.assertEquals(ResultDic.SUCCESS, ro.getResult());

        final KeyPair keyPair = Sm2Utils.generateKeyPair();
        addTo = new SgnSecretAddTo();
        addTo.setAlgorithm(SignAlgorithmDic.SM3_WITH_SM2.getCode());
        addTo.setSecret(Sm2Utils.getPublicKeyString(keyPair));
        addId = _secretApi.add(addTo).getExtra().getId();
        paramMap.put("signId", addId);
        log.info("private key: {}", Sm2Utils.getPrivateKeyString(keyPair));
        SignUtils.sign3(paramMap, Sm2Utils.getPrivateKeyFromString("MDBmMDcxMmI3NGMyODdlM2I2NDE2MzM2MWMwNmFjNTY0YWU0N2E3MDkzZjNiMzYyMDc0NjVhMGY5YWZhNGMyM2Ex"));
        ro = _api.verify(paramMap);
        log.info("返回结果: {}", ro);
        Assertions.assertEquals(ResultDic.WARN, ro.getResult());
        Assertions.assertEquals("验证签名错误: 签名不正确", ro.getMsg());

        SignUtils.sign3(paramMap, keyPair.getPrivate());
        ro = _api.verify(paramMap);
        log.info("返回结果: {}", ro);
        Assertions.assertEquals(ResultDic.SUCCESS, ro.getResult());

        // XXX 将过期设置为1秒，测试过期
        Thread.sleep(3000);
        SignUtils.sign3(paramMap, keyPair.getPrivate());
        ro = _api.verify(paramMap);
        log.info("返回结果: {}", ro);
        Assertions.assertEquals(ResultDic.SUCCESS, ro.getResult());
    }
}
