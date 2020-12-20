package rebue.scx.sgn.test.api.ex;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.sgn.api.ex.SgnVerifyApi;
import rebue.wheel.turing.SignUtils;

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

    /**
     * 测试签名验证
     */
    @Test
    public void testCrud() {
        final Map<String, Object> paramMap = new LinkedHashMap<>();
        Ro<?>                     ro       = _api.verify(paramMap);
        log.info("返回结果: {}", ro);
        Assertions.assertEquals(ResultDic.PARAM_ERROR, ro.getResult());
        Assertions.assertEquals("验证签名错误: 请求参数中没有signId", ro.getMsg());
        paramMap.put("A", "aaa");
        paramMap.put("B", "bbb");
        paramMap.put("C", "ccc");
        SignUtils.sign1(paramMap, "signKey");
        ro = _api.verify(paramMap);
        log.info("返回结果: {}", ro);
        Assertions.assertEquals(ResultDic.PARAM_ERROR, ro.getResult());
        Assertions.assertEquals("验证签名错误: 请求参数中没有signId", ro.getMsg());
        paramMap.put("signId", "sign-id-xxx");
        SignUtils.sign1(paramMap, "sign-key-xxx");
        ro = _api.verify(paramMap);
        log.info("返回结果: {}", ro);
        Assertions.assertEquals(ResultDic.PARAM_ERROR, ro.getResult());
        Assertions.assertEquals("验证签名错误: signId不正确", ro.getMsg());
        paramMap.put("signId", "sign-id-123");
        SignUtils.sign1(paramMap, "sign-key-123");
        ro = _api.verify(paramMap);
        log.info("返回结果: {}", ro);
        Assertions.assertEquals(ResultDic.WARN, ro.getResult());
        Assertions.assertEquals("验证签名错误: 签名不正确", ro.getMsg());
        SignUtils.sign1(paramMap, "sign-key-456");
        ro = _api.verify(paramMap);
        log.info("返回结果: {}", ro);
        Assertions.assertEquals(ResultDic.SUCCESS, ro.getResult());
    }
}
