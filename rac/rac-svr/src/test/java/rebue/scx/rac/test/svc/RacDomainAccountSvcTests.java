package rebue.scx.rac.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rac.mo.RacDomainAccountMo;
import rebue.scx.rac.svc.RacDomainAccountSvc;
import rebue.scx.rac.to.RacDomainAccountAddTo;
import rebue.scx.rac.to.RacDomainAccountModifyTo;
import rebue.scx.rac.to.RacDomainAccountPageTo;
import rebue.wheel.RandomEx;

/**
 * 领域账户 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacDomainAccountSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacDomainAccountSvc _svc;

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
        RacDomainAccountAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacDomainAccountAddTo) RandomEx.randomPojo(RacDomainAccountAddTo.class);
            log.info("添加领域账户的参数为：" + addTo);
            final Long addRo = _svc.add(addTo);
            log.info("添加领域账户的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo;
        }
        final PageInfo<RacDomainAccountMo> pageResult = _svc.page(new RacDomainAccountPageTo());
        log.info("查询领域账户的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个领域账户的参数为：" + id);
        RacDomainAccountMo getByIdResult = _svc.getById(id);
        log.info("获取单个领域账户的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RacDomainAccountModifyTo modifyTo = dozerMapper.map(addTo, RacDomainAccountModifyTo.class);
        modifyTo.setId(id);
        log.info("修改领域账户的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除领域账户的参数为：" + id);
        _svc.delById(id);
    }
}
