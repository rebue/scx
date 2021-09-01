package rebue.scx.rac.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.svc.RacAppSvc;
import rebue.scx.rac.to.RacAppAddTo;
import rebue.scx.rac.to.RacAppModifyTo;
import rebue.scx.rac.to.RacAppPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 应用 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacAppSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacAppSvc _svc;

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
        RacAppAddTo addTo = null;
        String id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacAppAddTo) RandomEx.randomPojo(RacAppAddTo.class);
            log.info("添加应用的参数为：" + addTo);
            final RacAppMo addRo = _svc.add(addTo);
            log.info("添加应用的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<RacAppMo> pageResult = _svc.page(new RacAppPageTo());
        log.info("查询应用的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个应用的参数为：" + id);
        RacAppMo getByIdResult = _svc.getById(id);
        log.info("获取单个应用的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RacAppModifyTo modifyTo = dozerMapper.map(addTo, RacAppModifyTo.class);
        modifyTo.setId(id);
        log.info("修改应用的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除应用的参数为：" + id);
        _svc.delById(id);
    }
}
