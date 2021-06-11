package rebue.scx.rac.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rac.mo.RacDicMo;
import rebue.scx.rac.svc.RacDicSvc;
import rebue.scx.rac.to.RacDicAddTo;
import rebue.scx.rac.to.RacDicModifyTo;
import rebue.scx.rac.to.RacDicPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 字典 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacDicSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacDicSvc _svc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper    dozerMapper;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        RacDicAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacDicAddTo) RandomEx.randomPojo(RacDicAddTo.class);
            log.info("添加字典的参数为：" + addTo);
            final RacDicMo addRo = _svc.add(addTo);
            log.info("添加字典的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<RacDicMo> pageResult = _svc.page(new RacDicPageTo());
        log.info("查询字典的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个字典的参数为：" + id);
        RacDicMo getByIdResult = _svc.getById(id);
        log.info("获取单个字典的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RacDicModifyTo modifyTo = dozerMapper.map(addTo, RacDicModifyTo.class);
        modifyTo.setId(id);
        log.info("修改字典的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除字典的参数为：" + id);
        _svc.delById(id);
    }
}
