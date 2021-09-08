package rebue.scx.rac.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rac.mo.RacAccountLockMo;
import rebue.scx.rac.svc.RacAccountLockSvc;
import rebue.scx.rac.to.RacAccountLockAddTo;
import rebue.scx.rac.to.RacAccountLockModifyTo;
import rebue.scx.rac.to.RacAccountLockPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 账户锁定 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacAccountLockSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacAccountLockSvc _svc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper            dozerMapper;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        RacAccountLockAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacAccountLockAddTo) RandomEx.randomPojo(RacAccountLockAddTo.class);
            log.info("添加账户锁定的参数为：" + addTo);
            final RacAccountLockMo addRo = _svc.add(addTo);
            log.info("添加账户锁定的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<RacAccountLockMo> pageResult = _svc.page(new RacAccountLockPageTo());
        log.info("查询账户锁定的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个账户锁定的参数为：" + id);
        RacAccountLockMo getByIdResult = _svc.getById(id);
        log.info("获取单个账户锁定的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RacAccountLockModifyTo modifyTo = dozerMapper.map(addTo, RacAccountLockModifyTo.class);
        modifyTo.setId(id);
        log.info("修改账户锁定的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除账户锁定的参数为：" + id);
        _svc.delById(id);
    }
}
