package rebue.scx.rac.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rac.mo.RacRealmMo;
import rebue.scx.rac.svc.RacRealmSvc;
import rebue.scx.rac.to.RacRealmAddTo;
import rebue.scx.rac.to.RacRealmModifyTo;
import rebue.scx.rac.to.RacRealmPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 领域 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacRealmSvcTests {

    /**
     * 要测试的微服务
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacRealmSvc _svc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper      dozerMapper;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        RacRealmAddTo addTo = null;
        String id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacRealmAddTo) RandomEx.randomPojo(RacRealmAddTo.class);
            log.info("添加领域的参数为：" + addTo);
            final RacRealmMo addRo = _svc.add(addTo);
            log.info("添加领域的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<RacRealmMo> pageResult = _svc.page(new RacRealmPageTo());
        log.info("查询领域的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个领域的参数为：" + id);
        RacRealmMo getByIdResult = _svc.getById(id);
        log.info("获取单个领域的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);

        final RacRealmModifyTo modifyTo = dozerMapper.map(addTo, RacRealmModifyTo.class);
        modifyTo.setId(id);
        log.info("修改领域的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);

        log.info("删除领域的参数为：" + id);
        _svc.delById(id);
    }
}
