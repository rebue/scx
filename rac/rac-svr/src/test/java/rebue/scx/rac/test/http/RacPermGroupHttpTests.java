package rebue.scx.rac.test.http;

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
import rebue.scx.rac.mo.RacPermGroupMo;
import rebue.scx.rac.to.RacPermGroupAddTo;
import rebue.scx.rac.to.RacPermGroupModifyTo;
import rebue.wheel.core.RandomEx;
import rebue.wheel.net.httpclient.HttpClient;
import rebue.wheel.net.httpclient.impl.OkHttpClientImpl;
import rebue.wheel.serialization.jackson.JacksonUtils;

/**
 * 权限分组 HTTP测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
public class RacPermGroupHttpTests {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private final String     _hostUrl     = "http://127.0.0.1:9605";

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
        RacPermGroupAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacPermGroupAddTo) RandomEx.randomPojo(RacPermGroupAddTo.class);
            log.info("添加权限分组的参数为：" + addTo);
            final String addResult = _httpClient.postByJsonParams(_hostUrl + "/rac/perm-group", addTo);
            log.info("添加权限分组的返回值为：" + addResult);
            final Ro<IdRa<Long>> idRo = JacksonUtils.deserialize(addResult, new TypeReference<Ro<IdRa<Long>>>() {
            });
            log.info(idRo.toString());
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            id = idRo.getExtra().getId();
        }
        final String pageResult = _httpClient.get(_hostUrl + "/rac/perm-group/page");
        log.info("查询权限分组的返回值为：" + pageResult);
        final Ro<PageRa<RacPermGroupMo>> pageRo = JacksonUtils.deserialize(pageResult, new TypeReference<Ro<PageRa<RacPermGroupMo>>>() {
        });
        Assertions.assertEquals(ResultDic.SUCCESS, pageRo.getResult());
        log.info("获取单个权限分组的参数为：" + id);
        final String getByIdResult = _httpClient.get(_hostUrl + "/rac/perm-group/get-by-id?id=" + id);
        log.info("获取单个权限分组的返回值为：" + getByIdResult);
        final RacPermGroupModifyTo modifyTo = _dozerMapper.map(addTo, RacPermGroupModifyTo.class);
        modifyTo.setId(id);
        log.info("修改权限分组的参数为：" + modifyTo);
        final String modifyResult = _httpClient.putByJsonParams(_hostUrl + "/rac/perm-group", modifyTo);
        final Ro<PojoRa<RacPermGroupMo>> getByIdRo = JacksonUtils.deserialize(getByIdResult, new TypeReference<Ro<PojoRa<RacPermGroupMo>>>() {
        });
        Assertions.assertEquals(ResultDic.SUCCESS, getByIdRo.getResult());
        RacPermGroupMo mo = getByIdRo.getExtra().getOne();
        mo = getByIdRo.getExtra().getOne();
        log.info("修改权限分组的返回值为：" + modifyResult);
        final Ro<?> modifyRo = JacksonUtils.deserialize(modifyResult, Ro.class);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyRo.getResult());
        log.info("删除权限分组的参数为：" + mo.getId());
        final String deleteResult = _httpClient.delete(_hostUrl + "/rac/perm-group?id=" + mo.getId());
        log.info("删除权限分组的返回值为：" + deleteResult);
        final Ro<?> deleteRo = JacksonUtils.deserialize(deleteResult, Ro.class);
        log.info(deleteRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, deleteRo.getResult());
    }
}
