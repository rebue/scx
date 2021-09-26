package rebue.scx.etl.test.api;

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
import rebue.scx.etl.api.EtlSyncStrategyApi;
import rebue.scx.etl.mo.EtlSyncStrategyMo;
import rebue.scx.etl.to.EtlSyncStrategyAddTo;
import rebue.scx.etl.to.EtlSyncStrategyModifyTo;
import rebue.scx.etl.to.EtlSyncStrategyPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 同步策略 API层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class EtlSyncStrategyApiTests {

    /**
     * 要测试的API
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DubboReference
    private EtlSyncStrategyApi _api;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper             dozerMapper;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        EtlSyncStrategyAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (EtlSyncStrategyAddTo) RandomEx.randomPojo(EtlSyncStrategyAddTo.class);
            log.info("添加同步策略的参数为：" + addTo);
            final Ro<IdRa<Long>> idRo = _api.add(addTo);
            log.info("添加同步策略的返回值为：" + idRo);
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            id = idRo.getExtra().getId();
        }
        final Ro<PageRa<EtlSyncStrategyMo>> pageResult = _api.page(new EtlSyncStrategyPageTo());
        log.info("查询同步策略的返回值为：" + pageResult);
        Assertions.assertEquals(ResultDic.SUCCESS, pageResult.getResult());
        log.info("获取单个同步策略的参数为：" + id);
        final Ro<PojoRa<EtlSyncStrategyMo>> getByIdResult = _api.getById(id);
        log.info("获取单个同步策略的返回值为：" + getByIdResult);
        final EtlSyncStrategyModifyTo modifyTo = dozerMapper.map(addTo, EtlSyncStrategyModifyTo.class);
        modifyTo.setId(id);
        log.info("修改同步策略的参数为：" + modifyTo);
        final Ro<?> modifyResult = _api.modify(modifyTo);
        log.info("修改同步策略的返回值为：" + modifyResult);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyResult.getResult());
        log.info("删除同步策略的参数为：" + id);
        final Ro<?> deleteResult = _api.del(id);
        log.info("删除同步策略的返回值为：" + deleteResult);
        Assertions.assertEquals(ResultDic.SUCCESS, deleteResult.getResult());
    }
}
