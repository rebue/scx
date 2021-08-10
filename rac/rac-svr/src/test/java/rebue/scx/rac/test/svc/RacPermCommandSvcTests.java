package rebue.scx.rac.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rac.mo.RacPermCommandMo;
import rebue.scx.rac.svc.RacPermCommandSvc;
import rebue.scx.rac.to.RacPermCommandAddTo;
import rebue.scx.rac.to.RacPermCommandModifyTo;
import rebue.scx.rac.to.RacPermCommandPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 权限命令 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacPermCommandSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacPermCommandSvc _svc;

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
        RacPermCommandAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacPermCommandAddTo) RandomEx.randomPojo(RacPermCommandAddTo.class);
            log.info("添加权限命令的参数为：" + addTo);
            final RacPermCommandMo addRo = _svc.add(addTo);
            log.info("添加权限命令的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<RacPermCommandMo> pageResult = _svc.page(new RacPermCommandPageTo());
        log.info("查询权限命令的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个权限命令的参数为：" + id);
        RacPermCommandMo getByIdResult = _svc.getById(id);
        log.info("获取单个权限命令的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RacPermCommandModifyTo modifyTo = dozerMapper.map(addTo, RacPermCommandModifyTo.class);
        modifyTo.setId(id);
        log.info("修改权限命令的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除权限命令的参数为：" + id);
        _svc.delById(id);
    }
}
