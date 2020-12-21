package rebue.scx.sgn.test.http;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.StringRa;
import rebue.robotech.ro.Ro;
import rebue.scx.sgn.to.SgnSecretAddTo;
import rebue.scx.sgn.to.SgnSecretModifyTo;
import rebue.wheel.JacksonUtils;
import rebue.wheel.RandomEx;
import rebue.wheel.http.HttpClient;
import rebue.wheel.http.impl.OkHttpClientImpl;

/**
 * 签名密钥 HTTP测试
 */
@Slf4j
public class SgnSecretHttpTests {

    private final String     _hostUrl     = "http://127.0.0.1:9436";

    private final HttpClient _httpClient  = new OkHttpClientImpl();

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private final Mapper     _dozerMapper = DozerBeanMapperBuilder.buildDefault();

    /**
     * 测试基本的增删改查
     */
    @Test
    public void testCrud() throws IOException {
        SgnSecretAddTo addTo = null;
        for (int i = 0; i < 20; i++) {
            addTo = (SgnSecretAddTo) RandomEx.randomPojo(SgnSecretAddTo.class);
            log.info("添加签名密钥的参数为：" + addTo);
            final String addResult = _httpClient.postByJsonParams(_hostUrl + "/sgn/secret", addTo);
            log.info("添加签名密钥的返回值为：" + addResult);
            final Ro<?> idRo = JacksonUtils.deserialize(addResult, new TypeReference<Ro<?>>() {
            });
            log.info(idRo.toString());
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
        }
        log.info("获取单个签名密钥的参数为：" + addTo.getId());
        final String getSecretByIdResult = _httpClient.get(_hostUrl + "/sgn/secret/get-secret-by-id?id=" + addTo.getId());
        log.info("获取单个签名密钥的返回值为：" + getSecretByIdResult);
        final Ro<StringRa> getSecretByIdRo = JacksonUtils.deserialize(getSecretByIdResult, new TypeReference<Ro<StringRa>>() {
        });
        Assertions.assertEquals(ResultDic.SUCCESS, getSecretByIdRo.getResult());
        final SgnSecretModifyTo modifyTo = _dozerMapper.map(addTo, SgnSecretModifyTo.class);
        modifyTo.setId(addTo.getId());
        log.info("修改签名密钥的参数为：" + modifyTo);
        final String modifyResult = _httpClient.putByJsonParams(_hostUrl + "/sgn/secret", modifyTo);
        log.info("修改签名密钥的返回值为：" + modifyResult);
        final Ro<?> modifyRo = JacksonUtils.deserialize(modifyResult, Ro.class);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyRo.getResult());
        log.info("删除签名密钥的参数为：" + addTo.getId());
        final String deleteResult = _httpClient.delete(_hostUrl + "/sgn/secret?id=" + addTo.getId());
        log.info("删除签名密钥的返回值为：" + deleteResult);
        final Ro<?> deleteRo = JacksonUtils.deserialize(deleteResult, Ro.class);
        log.info(deleteRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, deleteRo.getResult());
    }
}
