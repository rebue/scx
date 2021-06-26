package rebue.scx.rac.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rac.mo.RacUserMo;
import rebue.scx.rac.svc.RacUserSvc;
import rebue.scx.rac.to.RacUserAddTo;
import rebue.scx.rac.to.RacUserModifyTo;
import rebue.scx.rac.to.RacUserPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 用户 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacUserSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacUserSvc _svc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper     dozerMapper;

    /**
     * 测试基本的增删改查
     */
    @Test
    public void testCrud() {
        RacUserAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacUserAddTo) RandomEx.randomPojo(RacUserAddTo.class);
            log.info("添加用户的参数为：" + addTo);
            final RacUserMo addRo = _svc.add(addTo);
            log.info("添加用户的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<RacUserMo> pageResult = _svc.page(new RacUserPageTo());
        log.info("查询用户的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个用户的参数为：" + id);
        final RacUserMo getByIdResult = _svc.getById(id);
        log.info("获取单个用户的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RacUserModifyTo modifyTo = dozerMapper.map(addTo, RacUserModifyTo.class);
        modifyTo.setId(id);
        log.info("修改用户的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除用户的参数为：" + id);
        _svc.delById(id);
    }
}
