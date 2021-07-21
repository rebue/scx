package rebue.scx.oss.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import rebue.scx.oss.mo.OssObjMo;
import rebue.scx.oss.svc.OssObjSvc;
import rebue.scx.oss.to.OssObjAddTo;
import rebue.scx.oss.to.OssObjModifyTo;
import rebue.scx.oss.to.OssObjPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 对象 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class OssObjSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private OssObjSvc _svc;

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
        OssObjAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (OssObjAddTo) RandomEx.randomPojo(OssObjAddTo.class);
            log.info("添加对象的参数为：" + addTo);
            final OssObjMo addRo = _svc.add(addTo);
            log.info("添加对象的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<OssObjMo> pageResult = _svc.page(new OssObjPageTo());
        log.info("查询对象的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个对象的参数为：" + id);
        OssObjMo getByIdResult = _svc.getById(id);
        log.info("获取单个对象的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final OssObjModifyTo modifyTo = dozerMapper.map(addTo, OssObjModifyTo.class);
        modifyTo.setId(id);
        log.info("修改对象的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除对象的参数为：" + id);
        _svc.delById(id);
    }
}
