package rebue.scx.rac.test.svc;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rebue.scx.rac.mo.RacOrgMo;
import rebue.scx.rac.svc.RacOrgSvc;
import rebue.scx.rac.to.RacOrgAddTo;
import rebue.scx.rac.to.RacOrgModifyTo;
import rebue.wheel.RandomEx;

/**
 * 组织信息 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacOrgSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacOrgSvc _svc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper dozerMapper;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        RacOrgAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacOrgAddTo) RandomEx.randomPojo(RacOrgAddTo.class);
            log.info("添加组织信息的参数为：" + addTo);
            final Long addRo = _svc.add(addTo);
            log.info("添加组织信息的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo;
        }
        final PageInfo<RacOrgMo> listResult = _svc.list(null, 1, 5, null, 10);
        log.info("查询组织信息的返回值为：" + listResult);
        Assertions.assertNotNull(listResult);
        log.info("获取单个组织信息的参数为：" + id);
        RacOrgMo getByIdResult = _svc.getById(id);
        log.info("获取单个组织信息的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RacOrgModifyTo modifyTo = dozerMapper.map(addTo, RacOrgModifyTo.class);
        modifyTo.setId(id);
        log.info("修改组织信息的参数为：" + modifyTo);
        final Boolean modifyResult = _svc.modify(modifyTo);
        log.info("修改组织信息的返回值为：" + modifyResult);
        Assertions.assertTrue(modifyResult);
        log.info("删除组织信息的参数为：" + id);
        final Boolean deleteResult = _svc.del(id);
        log.info("删除组织信息的返回值为：" + deleteResult);
        Assertions.assertTrue(deleteResult);
    }
}
