package rebue.scx.rac.test.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.IdRo;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacSysUserMo;
import rebue.wheel.RandomEx;
import rebue.wheel.http.HttpClient;
import rebue.wheel.http.impl.OkHttpClientImpl;

/**
 * 系统用户
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
public class RacSysUserHttpTests {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private final String _hostUrl = "http://127.0.0.1:9105";

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private final ObjectMapper _objectMapper = new ObjectMapper();

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private final HttpClient _httpClient = new OkHttpClientImpl();

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() throws IOException, ReflectiveOperationException {
        RacSysUserMo mo = null;
        for (int i = 0; i < 20; i++) {
            mo = (RacSysUserMo) RandomEx.randomPojo(RacSysUserMo.class);
            mo.setId(null);
            log.info("添加系统用户的参数为：" + mo);
            final String addResult = _httpClient.postByJsonParams(_hostUrl + "/rac/sys-user", mo);
            log.info("添加系统用户的返回值为：" + addResult);
            final IdRo<Long> idRo = _objectMapper.readValue(addResult, new TypeReference<IdRo<Long>>() {
            });
            log.info(idRo.toString());
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            mo.setId(Long.valueOf(idRo.getId()));
        }
        final String listResult = _httpClient.get(_hostUrl + "/rac/sys-user/list?pageNum=1&pageSize=5");
        log.info("查询系统用户的返回值为：" + listResult);
        log.info("获取单个系统用户的参数为：" + mo.getId());
        final String getByIdResult = _httpClient.get(_hostUrl + "/rac/sys-user/get-by-id?id=" + mo.getId());
        log.info("获取单个系统用户的返回值为：" + getByIdResult);
        log.info("修改系统用户的参数为：" + mo);
        final String modifyResult = _httpClient.putByJsonParams(_hostUrl + "/rac/sys-user", mo);
        log.info("修改系统用户的返回值为：" + modifyResult);
        final Ro modifyRo = _objectMapper.readValue(modifyResult, Ro.class);
        log.info(modifyRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, modifyRo.getResult());
        log.info("删除系统用户的参数为：" + mo.getId());
        final String deleteResult = _httpClient.delete(_hostUrl + "/rac/sys-user?id=" + mo.getId());
        log.info("删除系统用户的返回值为：" + deleteResult);
        final Ro deleteRo = _objectMapper.readValue(deleteResult, Ro.class);
        log.info(deleteRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, deleteRo.getResult());
    }
}
