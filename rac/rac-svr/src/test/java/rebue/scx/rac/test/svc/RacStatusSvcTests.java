package rebue.scx.rac.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rac.mo.RacStatusMo;
import rebue.scx.rac.svc.RacStatusSvc;
import rebue.scx.rac.to.RacStatusAddTo;
import rebue.scx.rac.to.RacStatusModifyTo;
import rebue.scx.rac.to.RacStatusPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 身份 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacStatusSvcTests {

    /**
     * 要测试的微服务
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacStatusSvc _svc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper       dozerMapper;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        RacStatusAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacStatusAddTo) RandomEx.randomPojo(RacStatusAddTo.class);
            log.info("添加身份的参数为：" + addTo);
            final RacStatusMo addRo = _svc.add(addTo);
            log.info("添加身份的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<RacStatusMo> pageResult = _svc.page(new RacStatusPageTo());
        log.info("查询身份的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个身份的参数为：" + id);
        RacStatusMo getByIdResult = _svc.getById(id);
        log.info("获取单个身份的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);

        final RacStatusModifyTo modifyTo = dozerMapper.map(addTo, RacStatusModifyTo.class);
        modifyTo.setId(id);
        log.info("修改身份的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);

        log.info("删除身份的参数为：" + id);
        _svc.delById(id);
    }
}
