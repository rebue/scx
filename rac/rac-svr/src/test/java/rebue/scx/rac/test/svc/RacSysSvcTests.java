package rebue.scx.rac.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import rebue.scx.rac.mo.RacSysMo;
import rebue.scx.rac.svc.RacSysSvc;
import rebue.scx.rac.to.RacSysAddTo;
import rebue.scx.rac.to.RacSysModifyTo;
import rebue.wheel.RandomEx;
import rebue.scx.rac.to.RacSysPageTo;

/**
 * 系统 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacSysSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacSysSvc _svc;

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
        RacSysAddTo addTo = null;
        String id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacSysAddTo) RandomEx.randomPojo(RacSysAddTo.class);
            log.info("添加系统的参数为：" + addTo);
            final String addRo = _svc.add(addTo);
            log.info("添加系统的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo;
        }
        final PageInfo<RacSysMo> pageResult = _svc.page(new RacSysPageTo());
        log.info("查询系统的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个系统的参数为：" + id);
        RacSysMo getByIdResult = _svc.getById(id);
        log.info("获取单个系统的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RacSysModifyTo modifyTo = dozerMapper.map(addTo, RacSysModifyTo.class);
        modifyTo.setId(id);
        log.info("修改系统的参数为：" + modifyTo);
        final Boolean modifyResult = _svc.modifyById(modifyTo);
        log.info("修改系统的返回值为：" + modifyResult);
        Assertions.assertTrue(modifyResult);
        log.info("删除系统的参数为：" + id);
        final Boolean deleteResult = _svc.delById(id);
        log.info("删除系统的返回值为：" + deleteResult);
        Assertions.assertTrue(deleteResult);
    }
}
