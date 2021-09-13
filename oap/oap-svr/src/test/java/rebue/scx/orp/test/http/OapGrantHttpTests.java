package rebue.scx.orp.test.http;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import rebue.scx.orp.mo.OapGrantMo;
import rebue.scx.orp.to.OapGrantAddTo;
import rebue.scx.orp.to.OapGrantModifyTo;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.wheel.core.RandomEx;
import rebue.wheel.net.httpclient.HttpClient;
import rebue.wheel.net.httpclient.impl.OkHttpClientImpl;
import rebue.wheel.serialization.jackson.JacksonUtils;

/**
 * 三方应用账户信息 HTTP测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
public class OapGrantHttpTests {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private final String     _hostUrl     = "http://127.0.0.1:9970";

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private final HttpClient _httpClient  = new OkHttpClientImpl();

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private final Mapper     _dozerMapper = DozerBeanMapperBuilder.buildDefault();

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() throws IOException {
        OapGrantAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (OapGrantAddTo) RandomEx.randomPojo(OapGrantAddTo.class);
            log.info("添加三方应用账户信息的参数为：" + addTo);
            final String addResult = _httpClient.postByJsonParams(_hostUrl + "/oap/grant", addTo);
            log.info("添加三方应用账户信息的返回值为：" + addResult);
            final Ro<IdRa<Long>> idRo = JacksonUtils.deserialize(addResult, new TypeReference<Ro<IdRa<Long>>>() {
            });
            log.info(idRo.toString());
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            id = idRo.getExtra().getId();
        }
        final String pageResult = _httpClient.get(_hostUrl + "/oap/grant/page");
        log.info("查询三方应用账户信息的返回值为：" + pageResult);
        final Ro<PageRa<OapGrantMo>> pageRo = JacksonUtils.deserialize(pageResult, new TypeReference<Ro<PageRa<OapGrantMo>>>() {
        });
        Assertions.assertEquals(ResultDic.SUCCESS, pageRo.getResult());
        log.info("获取单个三方应用账户信息的参数为：" + id);
        final String getByIdResult = _httpClient.get(_hostUrl + "/oap/grant/get-by-id?id=" + id);
        log.info("获取单个三方应用账户信息的返回值为：" + getByIdResult);
        final OapGrantModifyTo modifyTo = _dozerMapper.map(addTo, OapGrantModifyTo.class);
        modifyTo.setId(id);
        log.info("修改三方应用账户信息的参数为：" + modifyTo);
        final String modifyResult = _httpClient.putByJsonParams(_hostUrl + "/oap/grant", modifyTo);
        final Ro<PojoRa<OapGrantMo>> getByIdRo = JacksonUtils.deserialize(getByIdResult, new TypeReference<Ro<PojoRa<OapGrantMo>>>() {
        });
        Assertions.assertEquals(ResultDic.SUCCESS, getByIdRo.getResult());
        OapGrantMo mo = getByIdRo.getExtra().getOne();
        mo = getByIdRo.getExtra().getOne();
        log.info("修改三方应用账户信息的返回值为：" + modifyResult);
        final Ro<?> modifyRo = JacksonUtils.deserialize(modifyResult, Ro.class);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyRo.getResult());
        log.info("删除三方应用账户信息的参数为：" + mo.getId());
        final String deleteResult = _httpClient.delete(_hostUrl + "/oap/grant?id=" + mo.getId());
        log.info("删除三方应用账户信息的返回值为：" + deleteResult);
        final Ro<?> deleteRo = JacksonUtils.deserialize(deleteResult, Ro.class);
        log.info(deleteRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, deleteRo.getResult());
    }
}
