package rebue.wxx.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.wheel.core.RandomEx;
import rebue.wxx.mo.WxxAppMo;
import rebue.wxx.svc.WxxAppSvc;
import rebue.wxx.to.WxxAppAddTo;
import rebue.wxx.to.WxxAppModifyTo;
import rebue.wxx.to.WxxAppPageTo;

/**
 * APP Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class WxxAppSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private WxxAppSvc _svc;

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
        WxxAppAddTo addTo = null;
        String id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (WxxAppAddTo) RandomEx.randomPojo(WxxAppAddTo.class);
            log.info("添加APP的参数为：" + addTo);
            final WxxAppMo addRo = _svc.add(addTo);
            log.info("添加APP的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<WxxAppMo> pageResult = _svc.page(new WxxAppPageTo());
        log.info("查询APP的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个APP的参数为：" + id);
        WxxAppMo getByIdResult = _svc.getById(id);
        log.info("获取单个APP的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final WxxAppModifyTo modifyTo = dozerMapper.map(addTo, WxxAppModifyTo.class);
        modifyTo.setId(id);
        log.info("修改APP的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除APP的参数为：" + id);
        _svc.delById(id);
    }
}
