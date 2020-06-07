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
import rebue.scx.rac.mo.RacRoleMo;
import rebue.wheel.RandomEx;
import rebue.wheel.http.HttpClient;
import rebue.wheel.http.impl.OkHttpClientImpl;

/**
 * 角色信息
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
public class RacRoleHttpTests {

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
        RacRoleMo mo = null;
        for (int i = 0; i < 20; i++) {
            mo = (RacRoleMo) RandomEx.randomPojo(RacRoleMo.class);
            mo.setId(null);
            log.info("添加角色信息的参数为：" + mo);
            final String addResult = _httpClient.postByJsonParams(_hostUrl + "/rac/role", mo);
            log.info("添加角色信息的返回值为：" + addResult);
            final IdRo<String> idRo = _objectMapper.readValue(addResult, new TypeReference<IdRo<String>>() {
            });
            log.info(idRo.toString());
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            mo.setId(idRo.getId());
        }
        final String listResult = _httpClient.get(_hostUrl + "/rac/role/list?pageNum=1&pageSize=5");
        log.info("查询角色信息的返回值为：" + listResult);
        log.info("获取单个角色信息的参数为：" + mo.getId());
        final String getByIdResult = _httpClient.get(_hostUrl + "/rac/role/get-by-id?id=" + mo.getId());
        log.info("获取单个角色信息的返回值为：" + getByIdResult);
        log.info("修改角色信息的参数为：" + mo);
        final String modifyResult = _httpClient.putByJsonParams(_hostUrl + "/rac/role", mo);
        log.info("修改角色信息的返回值为：" + modifyResult);
        final Ro modifyRo = _objectMapper.readValue(modifyResult, Ro.class);
        log.info(modifyRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, modifyRo.getResult());
        log.info("删除角色信息的参数为：" + mo.getId());
        final String deleteResult = _httpClient.delete(_hostUrl + "/rac/role?id=" + mo.getId());
        log.info("删除角色信息的返回值为：" + deleteResult);
        final Ro deleteRo = _objectMapper.readValue(deleteResult, Ro.class);
        log.info(deleteRo.toString());
        Assertions.assertEquals(ResultDic.SUCCESS, deleteRo.getResult());
    }
}
