package rebue.scx.oap.test.http;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.oap.mo.OapIpWhiteListMo;
import rebue.scx.oap.to.OapIpWhiteListAddTo;
import rebue.scx.oap.to.OapIpWhiteListModifyTo;
import rebue.wheel.core.RandomEx;
import rebue.wheel.net.httpclient.HttpClient;
import rebue.wheel.net.httpclient.impl.OkHttpClientImpl;
import rebue.wheel.serialization.jackson.JacksonUtils;

/**
 * 第三方应用IP白名单 HTTP测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
public class OapIpWhiteListHttpTests {

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
        OapIpWhiteListAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (OapIpWhiteListAddTo) RandomEx.randomPojo(OapIpWhiteListAddTo.class);
            log.info("添加第三方应用IP白名单的参数为：" + addTo);
            final String addResult = _httpClient.postByJsonParams(_hostUrl + "/oap/ip-white-list", addTo);
            log.info("添加第三方应用IP白名单的返回值为：" + addResult);
            final Ro<IdRa<Long>> idRo = JacksonUtils.deserialize(addResult, new TypeReference<Ro<IdRa<Long>>>() {
            });
            log.info(idRo.toString());
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            id = idRo.getExtra().getId();
        }
        final String pageResult = _httpClient.get(_hostUrl + "/oap/ip-white-list/page");
        log.info("查询第三方应用IP白名单的返回值为：" + pageResult);
        final Ro<PageRa<OapIpWhiteListMo>> pageRo = JacksonUtils.deserialize(pageResult, new TypeReference<Ro<PageRa<OapIpWhiteListMo>>>() {
        });
        Assertions.assertEquals(ResultDic.SUCCESS, pageRo.getResult());
        log.info("获取单个第三方应用IP白名单的参数为：" + id);
        final String getByIdResult = _httpClient.get(_hostUrl + "/oap/ip-white-list/get-by-id?id=" + id);
        log.info("获取单个第三方应用IP白名单的返回值为：" + getByIdResult);
        final OapIpWhiteListModifyTo modifyTo = _dozerMapper.map(addTo, OapIpWhiteListModifyTo.class);
        modifyTo.setId(id);
        log.info("修改第三方应用IP白名单的参数为：" + modifyTo);
        final String modifyResult = _httpClient.putByJsonParams(_hostUrl + "/oap/ip-white-list", modifyTo);
        final Ro<PojoRa<OapIpWhiteListMo>> getByIdRo = JacksonUtils.deserialize(getByIdResult, new TypeReference<Ro<PojoRa<OapIpWhiteListMo>>>() {
        });
        Assertions.assertEquals(ResultDic.SUCCESS, getByIdRo.getResult());
        OapIpWhiteListMo mo = getByIdRo.getExtra().getOne();
        mo = getByIdRo.getExtra().getOne();
        log.info("修改第三方应用IP白名单的返回值为：" + modifyResult);
        final Ro<?> modifyRo = JacksonUtils.deserialize(modifyResult, Ro.class);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyRo.getResult());
        log.info("删除第三方应用IP白名单的参数为：" + mo.getId());
        final String deleteResult = _httpClient.delete(_hostUrl + "/oap/ip-white-list?id=" + mo.getId());
        log.info("删除第三方应用IP白名单的返回值为：" + deleteResult);
        final Ro<?> deleteRo = JacksonUtils.deserialize(deleteResult, Ro.class);
        log.info(deleteRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, deleteRo.getResult());
    }
}
