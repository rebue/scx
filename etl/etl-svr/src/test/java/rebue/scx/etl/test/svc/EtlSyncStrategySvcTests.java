package rebue.scx.etl.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.etl.mo.EtlSyncStrategyMo;
import rebue.scx.etl.svc.EtlSyncStrategySvc;
import rebue.scx.etl.to.EtlSyncStrategyAddTo;
import rebue.scx.etl.to.EtlSyncStrategyModifyTo;
import rebue.scx.etl.to.EtlSyncStrategyPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 同步策略 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class EtlSyncStrategySvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private EtlSyncStrategySvc _svc;

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
            final EtlSyncStrategyMo addRo = _svc.add(addTo);
            log.info("添加同步策略的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<EtlSyncStrategyMo> pageResult = _svc.page(new EtlSyncStrategyPageTo());
        log.info("查询同步策略的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个同步策略的参数为：" + id);
        EtlSyncStrategyMo getByIdResult = _svc.getById(id);
        log.info("获取单个同步策略的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final EtlSyncStrategyModifyTo modifyTo = dozerMapper.map(addTo, EtlSyncStrategyModifyTo.class);
        modifyTo.setId(id);
        log.info("修改同步策略的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除同步策略的参数为：" + id);
        _svc.delById(id);
    }
}
