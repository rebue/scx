package rebue.scx.rrl.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rrl.mo.RrlFilterMo;
import rebue.scx.rrl.svc.RrlFilterSvc;
import rebue.scx.rrl.to.RrlFilterAddTo;
import rebue.scx.rrl.to.RrlFilterModifyTo;
import rebue.scx.rrl.to.RrlFilterPageTo;
import rebue.wheel.RandomEx;

/**
 * 过滤器 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RrlFilterSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RrlFilterSvc _svc;

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
        RrlFilterAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RrlFilterAddTo) RandomEx.randomPojo(RrlFilterAddTo.class);
            log.info("添加过滤器的参数为：" + addTo);
            final RrlFilterMo addRo = _svc.add(addTo);
            log.info("添加过滤器的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<RrlFilterMo> pageResult = _svc.page(new RrlFilterPageTo());
        log.info("查询过滤器的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个过滤器的参数为：" + id);
        RrlFilterMo getByIdResult = _svc.getById(id);
        log.info("获取单个过滤器的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RrlFilterModifyTo modifyTo = dozerMapper.map(addTo, RrlFilterModifyTo.class);
        modifyTo.setId(id);
        log.info("修改过滤器的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除过滤器的参数为：" + id);
        _svc.delById(id);
    }
}
