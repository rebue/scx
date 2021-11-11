package rebue.scx.oap.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import rebue.scx.oap.mo.OapAuthLogMo;
import rebue.scx.oap.svc.OapAuthLogSvc;
import rebue.scx.oap.to.OapAuthLogAddTo;
import rebue.scx.oap.to.OapAuthLogModifyTo;
import rebue.scx.oap.to.OapAuthLogPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 认证记录 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class OapAuthLogSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private OapAuthLogSvc _svc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper        dozerMapper;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        OapAuthLogAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (OapAuthLogAddTo) RandomEx.randomPojo(OapAuthLogAddTo.class);
            log.info("添加认证记录的参数为：" + addTo);
            final OapAuthLogMo addRo = _svc.add(addTo);
            log.info("添加认证记录的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<OapAuthLogMo> pageResult = _svc.page(new OapAuthLogPageTo());
        log.info("查询认证记录的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个认证记录的参数为：" + id);
        OapAuthLogMo getByIdResult = _svc.getById(id);
        log.info("获取单个认证记录的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final OapAuthLogModifyTo modifyTo = dozerMapper.map(addTo, OapAuthLogModifyTo.class);
        modifyTo.setId(id);
        log.info("修改认证记录的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除认证记录的参数为：" + id);
        _svc.delById(id);
    }
}
