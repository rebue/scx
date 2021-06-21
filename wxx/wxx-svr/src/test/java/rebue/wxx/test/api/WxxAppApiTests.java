package rebue.wxx.test.api;

import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.wheel.core.RandomEx;
import rebue.wxx.api.WxxAppApi;
import rebue.wxx.mo.WxxAppMo;
import rebue.wxx.to.WxxAppAddTo;
import rebue.wxx.to.WxxAppModifyTo;
import rebue.wxx.to.WxxAppPageTo;

/**
 *  API层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class WxxAppApiTests {

    /**
     * 要测试的API
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DubboReference
    private WxxAppApi _api;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper    dozerMapper;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        WxxAppAddTo addTo = null;
        String id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (WxxAppAddTo) RandomEx.randomPojo(WxxAppAddTo.class);
            log.info("添加的参数为：" + addTo);
            final Ro<IdRa<String>> idRo = _api.add(addTo);
            log.info("添加的返回值为：" + idRo);
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            id = idRo.getExtra().getId();
        }
        final Ro<PageRa<WxxAppMo>> pageResult = _api.page(new WxxAppPageTo());
        log.info("查询的返回值为：" + pageResult);
        Assertions.assertEquals(ResultDic.SUCCESS, pageResult.getResult());
        log.info("获取单个的参数为：" + id);
        final Ro<PojoRa<WxxAppMo>> getByIdResult = _api.getById(id);
        log.info("获取单个的返回值为：" + getByIdResult);

        final WxxAppModifyTo modifyTo = dozerMapper.map(addTo, WxxAppModifyTo.class);
        modifyTo.setId(id);
        log.info("修改的参数为：" + modifyTo);
        final Ro<?> modifyResult = _api.modify(modifyTo);
        log.info("修改的返回值为：" + modifyResult);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyResult.getResult());

        log.info("删除的参数为：" + id);
        final Ro<?> deleteResult = _api.del(id);
        log.info("删除的返回值为：" + deleteResult);
        Assertions.assertEquals(ResultDic.SUCCESS, deleteResult.getResult());
    }
}
