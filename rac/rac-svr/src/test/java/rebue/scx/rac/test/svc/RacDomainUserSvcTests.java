package rebue.scx.rac.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rac.mo.RacDomainUserMo;
import rebue.scx.rac.svc.RacDomainUserSvc;
import rebue.scx.rac.to.RacDomainUserAddTo;
import rebue.scx.rac.to.RacDomainUserModifyTo;
import rebue.scx.rac.to.RacDomainUserPageTo;
import rebue.wheel.RandomEx;

/**
 * 领域用户 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacDomainUserSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacDomainUserSvc _svc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper           dozerMapper;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        RacDomainUserAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacDomainUserAddTo) RandomEx.randomPojo(RacDomainUserAddTo.class);
            log.info("添加领域用户的参数为：" + addTo);
            final Long addRo = _svc.add(addTo);
            log.info("添加领域用户的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo;
        }
        final PageInfo<RacDomainUserMo> pageResult = _svc.page(new RacDomainUserPageTo());
        log.info("查询领域用户的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个领域用户的参数为：" + id);
        RacDomainUserMo getByIdResult = _svc.getById(id);
        log.info("获取单个领域用户的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RacDomainUserModifyTo modifyTo = dozerMapper.map(addTo, RacDomainUserModifyTo.class);
        modifyTo.setId(id);
        log.info("修改领域用户的参数为：" + modifyTo);
        final Boolean modifyResult = _svc.modifyById(modifyTo);
        log.info("修改领域用户的返回值为：" + modifyResult);
        Assertions.assertTrue(modifyResult);
        log.info("删除领域用户的参数为：" + id);
        final Boolean deleteResult = _svc.delById(id);
        log.info("删除领域用户的返回值为：" + deleteResult);
        Assertions.assertTrue(deleteResult);
    }
}
