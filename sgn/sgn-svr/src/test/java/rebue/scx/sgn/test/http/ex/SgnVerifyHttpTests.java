package rebue.scx.sgn.test.http.ex;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.StringRa;
import rebue.robotech.ro.Ro;
import rebue.wheel.JacksonUtils;
import rebue.wheel.MapUtils;
import rebue.wheel.RandomEx;
import rebue.wheel.http.HttpClient;
import rebue.wheel.http.impl.OkHttpClientImpl;
import rebue.wheel.turing.SignUtils;

/**
 * 签名密钥 HTTP测试
 */
@Slf4j
public class SgnVerifyHttpTests {

    private final String        _hostUrl     = "http://127.0.0.1:10080";
    private final static String SIGN_ID      = "sign-id-123";
    private final static String SIGN_KEY     = "sign-key-456";

    private final HttpClient    _httpClient  = new OkHttpClientImpl();

    private final Mapper        _dozerMapper = DozerBeanMapperBuilder.buildDefault();

    /**
     * 测试签名验证(通过网关)
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testCrud() throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException, IllegalArgumentException, IntrospectionException {
        SgnSecretAddExTo addTo = null;
        for (int i = 0; i < 20; i++) {
            addTo = (SgnSecretAddExTo) RandomEx.randomPojo(SgnSecretAddExTo.class);
            addTo.setSignId(SIGN_ID);
            final Map<?, ?> paramMap = MapUtils.bean2Map(addTo);
            SignUtils.sign1((Map<String, Object>) paramMap, SIGN_KEY);
            addTo = (SgnSecretAddExTo) MapUtils.map2Bean((Map<String, Object>) paramMap, SgnSecretAddExTo.class);
            log.info("添加签名密钥的参数为：" + addTo);
            final String addResult = _httpClient.postByJsonParams(_hostUrl + "/sgn/secret", addTo);
            log.info("添加签名密钥的返回值为：" + addResult);
            final Ro<?> idRo = JacksonUtils.deserialize(addResult, new TypeReference<Ro<?>>() {
            });
            log.info(idRo.toString());
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
        }

        final Map<String, Object> getByIdTo = new LinkedHashMap<>();
        getByIdTo.put("id", addTo.getId());
        getByIdTo.put("signId", SIGN_ID);
        SignUtils.sign1(getByIdTo, SIGN_KEY);
        final String getByIdParams = MapUtils.map2UrlParams(getByIdTo);
        log.info("获取单个签名密钥的参数为：{}", getByIdParams);
        String getSecretByIdResult = _httpClient.get(_hostUrl + "/sgn/secret/get-secret-by-id?" + getByIdParams);
        log.info("获取单个签名密钥的返回值为：" + getSecretByIdResult);
        Ro<StringRa> getSecretByIdRo = JacksonUtils.deserialize(getSecretByIdResult, new TypeReference<Ro<StringRa>>() {
        });
        Assertions.assertEquals(ResultDic.SUCCESS, getSecretByIdRo.getResult());

        SgnSecretModifyExTo modifyTo = _dozerMapper.map(addTo, SgnSecretModifyExTo.class);
        modifyTo.setSecret(RandomEx.random1(32));
        modifyTo.setSignId(SIGN_ID);
        final Map<?, ?> paramMap = MapUtils.bean2Map(modifyTo);
        SignUtils.sign1((Map<String, Object>) paramMap, SIGN_KEY);
        modifyTo = (SgnSecretModifyExTo) MapUtils.map2Bean((Map<String, Object>) paramMap, SgnSecretModifyExTo.class);
        log.info("修改签名密钥的参数为：" + modifyTo);
        final String modifyResult = _httpClient.putByJsonParams(_hostUrl + "/sgn/secret", modifyTo);
        log.info("修改签名密钥的返回值为：" + modifyResult);
        final Ro<?> modifyRo = JacksonUtils.deserialize(modifyResult, Ro.class);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyRo.getResult());

        log.info("获取单个签名密钥的参数为：{}", getByIdParams);
        getSecretByIdResult = _httpClient.get(_hostUrl + "/sgn/secret/get-secret-by-id?" + getByIdParams);
        log.info("获取单个签名密钥的返回值为：" + getSecretByIdResult);
        getSecretByIdRo = JacksonUtils.deserialize(getSecretByIdResult, new TypeReference<Ro<StringRa>>() {
        });
        Assertions.assertEquals(ResultDic.SUCCESS, getSecretByIdRo.getResult());
        Assertions.assertEquals(modifyTo.getSecret(), getSecretByIdRo.getExtra().getValue());

        log.info("删除签名密钥的参数为：{}", getByIdParams);
        final String deleteResult = _httpClient.delete(_hostUrl + "/sgn/secret?" + getByIdParams);
        log.info("删除签名密钥的返回值为：" + deleteResult);
        final Ro<?> deleteRo = JacksonUtils.deserialize(deleteResult, Ro.class);
        log.info(deleteRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, deleteRo.getResult());

        log.info("获取单个签名密钥的参数为：{}", getByIdParams);
        getSecretByIdResult = _httpClient.get(_hostUrl + "/sgn/secret/get-secret-by-id?" + getByIdParams);
        log.info("获取单个签名密钥的返回值为：" + getSecretByIdResult);
        getSecretByIdRo = JacksonUtils.deserialize(getSecretByIdResult, new TypeReference<Ro<StringRa>>() {
        });
        Assertions.assertEquals(ResultDic.FAIL, getSecretByIdRo.getResult());
        Assertions.assertEquals("找不到密钥", getSecretByIdRo.getMsg());
    }
}
