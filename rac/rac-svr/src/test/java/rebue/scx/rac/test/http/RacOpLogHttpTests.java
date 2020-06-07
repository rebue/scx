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
import rebue.scx.rac.mo.RacOpLogMo;
import rebue.wheel.RandomEx;
import rebue.wheel.http.HttpClient;
import rebue.wheel.http.impl.OkHttpClientImpl;

/**
 * 用户操作日志
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
public class RacOpLogHttpTests {

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
        RacOpLogMo mo = null;
        for (int i = 0; i < 20; i++) {
            mo = (RacOpLogMo) RandomEx.randomPojo(RacOpLogMo.class);
            mo.setId(null);
            log.info("添加用户操作日志的参数为：" + mo);
            final String addResult = _httpClient.postByJsonParams(_hostUrl + "/rac/op-log", mo);
            log.info("添加用户操作日志的返回值为：" + addResult);
            final IdRo<Long> idRo = _objectMapper.readValue(addResult, new TypeReference<IdRo<Long>>() {
            });
            log.info(idRo.toString());
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            mo.setId(Long.valueOf(idRo.getId()));
        }
        final String listResult = _httpClient.get(_hostUrl + "/rac/op-log/list?pageNum=1&pageSize=5");
        log.info("查询用户操作日志的返回值为：" + listResult);
        log.info("获取单个用户操作日志的参数为：" + mo.getId());
        final String getByIdResult = _httpClient.get(_hostUrl + "/rac/op-log/get-by-id?id=" + mo.getId());
        log.info("获取单个用户操作日志的返回值为：" + getByIdResult);
        log.info("修改用户操作日志的参数为：" + mo);
        final String modifyResult = _httpClient.putByJsonParams(_hostUrl + "/rac/op-log", mo);
        log.info("修改用户操作日志的返回值为：" + modifyResult);
        final Ro modifyRo = _objectMapper.readValue(modifyResult, Ro.class);
        log.info(modifyRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, modifyRo.getResult());
        log.info("删除用户操作日志的参数为：" + mo.getId());
        final String deleteResult = _httpClient.delete(_hostUrl + "/rac/op-log?id=" + mo.getId());
        log.info("删除用户操作日志的返回值为：" + deleteResult);
        final Ro deleteRo = _objectMapper.readValue(deleteResult, Ro.class);
        log.info(deleteRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, deleteRo.getResult());
    }
}
