package rebue.scx.etl.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.etl.mo.EtlSyncStrategyDetailMo;
import rebue.scx.etl.svc.EtlSyncStrategyDetailSvc;
import rebue.scx.etl.to.EtlSyncStrategyDetailAddTo;
import rebue.scx.etl.to.EtlSyncStrategyDetailModifyTo;
import rebue.scx.etl.to.EtlSyncStrategyDetailPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 同步策略详情 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class EtlSyncStrategyDetailSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private EtlSyncStrategyDetailSvc _svc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper                   dozerMapper;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        EtlSyncStrategyDetailAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (EtlSyncStrategyDetailAddTo) RandomEx.randomPojo(EtlSyncStrategyDetailAddTo.class);
            log.info("添加同步策略详情的参数为：" + addTo);
            final EtlSyncStrategyDetailMo addRo = _svc.add(addTo);
            log.info("添加同步策略详情的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<EtlSyncStrategyDetailMo> pageResult = _svc.page(new EtlSyncStrategyDetailPageTo());
        log.info("查询同步策略详情的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个同步策略详情的参数为：" + id);
        EtlSyncStrategyDetailMo getByIdResult = _svc.getById(id);
        log.info("获取单个同步策略详情的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final EtlSyncStrategyDetailModifyTo modifyTo = dozerMapper.map(addTo, EtlSyncStrategyDetailModifyTo.class);
        modifyTo.setId(id);
        log.info("修改同步策略详情的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除同步策略详情的参数为：" + id);
        _svc.delById(id);
    }
}
