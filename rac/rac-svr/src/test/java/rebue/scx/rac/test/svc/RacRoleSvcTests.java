package rebue.scx.rac.test.svc;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rebue.scx.rac.mo.RacRoleMo;
import rebue.scx.rac.svc.RacRoleSvc;
import rebue.scx.rac.to.RacRoleAddTo;
import rebue.scx.rac.to.RacRoleModifyTo;
import rebue.wheel.RandomEx;

/**
 * 角色信息 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacRoleSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacRoleSvc _svc;

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
        RacRoleAddTo addTo = null;
        String id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacRoleAddTo) RandomEx.randomPojo(RacRoleAddTo.class);
            log.info("添加角色信息的参数为：" + addTo);
            final String addRo = _svc.add(addTo);
            log.info("添加角色信息的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo;
        }
        final PageInfo<RacRoleMo> listResult = _svc.list(null, 1, 5, null, 10);
        log.info("查询角色信息的返回值为：" + listResult);
        Assertions.assertNotNull(listResult);
        log.info("获取单个角色信息的参数为：" + id);
        RacRoleMo getByIdResult = _svc.getById(id);
        log.info("获取单个角色信息的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RacRoleModifyTo modifyTo = dozerMapper.map(addTo, RacRoleModifyTo.class);
        modifyTo.setId(id);
        log.info("修改角色信息的参数为：" + modifyTo);
        final Boolean modifyResult = _svc.modify(modifyTo);
        log.info("修改角色信息的返回值为：" + modifyResult);
        Assertions.assertTrue(modifyResult);
        log.info("删除角色信息的参数为：" + id);
        final Boolean deleteResult = _svc.del(id);
        log.info("删除角色信息的返回值为：" + deleteResult);
        Assertions.assertTrue(deleteResult);
    }
}
