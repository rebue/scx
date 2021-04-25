package rebue.scx.sgn.test.http;

import java.io.IOException;
import java.security.KeyPair;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.sgn.mo.SgnSecretMo;
import rebue.scx.sgn.to.SgnSecretAddTo;
import rebue.scx.sgn.to.SgnSecretModifyTo;
import rebue.wheel.RandomEx;
import rebue.wheel.net.httpclient.HttpClient;
import rebue.wheel.net.httpclient.impl.OkHttpClientImpl;
import rebue.wheel.serialization.jackson.JacksonUtils;
import rebue.wheel.turing.Sm2Utils;

/**
 * 签名密钥 HTTP测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
public class SgnSecretHttpTests {

    private final String     _hostUrl     = "http://127.0.0.1:9436";

    private final HttpClient _httpClient  = new OkHttpClientImpl();

    private final Mapper     _dozerMapper = DozerBeanMapperBuilder.buildDefault();

    /**
     * 测试基本的增删改查
     */
    @Test
    public void testCrud() throws IOException {
        SgnSecretAddTo addTo = null;
        Long           id    = null;
        for (int i = 0; i < 20; i++) {
            addTo = (SgnSecretAddTo) RandomEx.randomPojo(SgnSecretAddTo.class);
            // XXX 生成公钥并保存
            final KeyPair keyPair = Sm2Utils.generateKeyPair();
            log.info("private key: {}", Sm2Utils.getPrivateKeyString(keyPair));
            addTo.setSecret(Sm2Utils.getPublicKeyString(keyPair));
            log.info("添加签名密钥的参数为：" + addTo);
            final String addResult = _httpClient.postByJsonParams(_hostUrl + "/sgn/secret", addTo);
            log.info("添加签名密钥的返回值为：" + addResult);
            final Ro<IdRa<Long>> idRo = JacksonUtils.deserialize(addResult, new TypeReference<Ro<IdRa<Long>>>() {
            });
            log.info(idRo.toString());
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            id = idRo.getExtra().getId();
        }
        final String pageResult = _httpClient.get(_hostUrl + "/sgn/secret/page");
        log.info("查询签名密钥的返回值为：" + pageResult);
        final Ro<PageRa<SgnSecretMo>> pageRo = JacksonUtils.deserialize(pageResult, new TypeReference<Ro<PageRa<SgnSecretMo>>>() {
        });
        Assertions.assertEquals(ResultDic.SUCCESS, pageRo.getResult());
        log.info("获取单个签名密钥的参数为：" + id);
        final String getByIdResult = _httpClient.get(_hostUrl + "/sgn/secret/get-by-id?id=" + id);
        log.info("获取单个签名密钥的返回值为：" + getByIdResult);
        final SgnSecretModifyTo modifyTo = _dozerMapper.map(addTo, SgnSecretModifyTo.class);
        modifyTo.setId(id);
        modifyTo.setAlgorithm((byte) 3);
        log.info("修改签名密钥的参数为：" + modifyTo);
        final String                  modifyResult = _httpClient.putByJsonParams(_hostUrl + "/sgn/secret", modifyTo);
        final Ro<PojoRa<SgnSecretMo>> getByIdRo    = JacksonUtils.deserialize(getByIdResult, new TypeReference<Ro<PojoRa<SgnSecretMo>>>() {
                                                   });
        Assertions.assertEquals(ResultDic.SUCCESS, getByIdRo.getResult());
        log.info("修改签名密钥的返回值为：" + modifyResult);
        final Ro<?> modifyRo = JacksonUtils.deserialize(modifyResult, Ro.class);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyRo.getResult());

        // XXX 这里注释起来不删除，是因为要保留这个添加的记录的实体做签名测试
        // final SgnSecretMo mo = getByIdRo.getExtra().getOne();
        // log.info("删除签名密钥的参数为：" + mo.getId());
        // final String deleteResult = _httpClient.delete(_hostUrl + "/sgn/secret?id=" + mo.getId());
        // log.info("删除签名密钥的返回值为：" + deleteResult);
        // final Ro<?> deleteRo = JacksonUtils.deserialize(deleteResult, Ro.class);
        // log.info(deleteRo.toString());
        // Assertions.assertEquals(ResultDic.SUCCESS, deleteRo.getResult());
    }
}
