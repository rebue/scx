package rebue.scx.rac.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 账户 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacAccountSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacAccountSvc _svc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper        dozerMapper;

    /**
     * 测试基本的增删改查
     */
    @Test
    @Disabled
    public void testCrud() {
        RacAccountAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacAccountAddTo) RandomEx.randomPojo(RacAccountAddTo.class);
            addTo.setSignInPswdSalt("aaa");
            addTo.setPayPswdSalt("bbb");
            addTo.setUserId(null);
            addTo.setOrgId(null);
            addTo.setRealmId("ops");
            log.info("添加账户的参数为：" + addTo);
            final RacAccountMo addRo = _svc.add(addTo);
            log.info("添加账户的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<RacAccountMo> pageResult = _svc.page(new RacAccountPageTo());
        log.info("查询账户的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个账户的参数为：" + id);
        final RacAccountMo getByIdResult = _svc.getById(id);
        log.info("获取单个账户的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RacAccountModifyTo modifyTo = dozerMapper.map(addTo, RacAccountModifyTo.class);
        modifyTo.setId(id);
        log.info("修改账户的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除账户的参数为：" + id);
        _svc.delById(id);
    }
}
