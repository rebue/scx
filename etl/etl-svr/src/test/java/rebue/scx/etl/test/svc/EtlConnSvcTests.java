package rebue.scx.etl.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.etl.mo.EtlConnMo;
import rebue.scx.etl.svc.EtlConnSvc;
import rebue.scx.etl.to.EtlConnAddTo;
import rebue.scx.etl.to.EtlConnModifyTo;
import rebue.scx.etl.to.EtlConnPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 数据库连接器 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class EtlConnSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private EtlConnSvc _svc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper     dozerMapper;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        EtlConnAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (EtlConnAddTo) RandomEx.randomPojo(EtlConnAddTo.class);
            log.info("添加数据库连接器的参数为：" + addTo);
            final EtlConnMo addRo = _svc.add(addTo);
            log.info("添加数据库连接器的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<EtlConnMo> pageResult = _svc.page(new EtlConnPageTo());
        log.info("查询数据库连接器的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个数据库连接器的参数为：" + id);
        EtlConnMo getByIdResult = _svc.getById(id);
        log.info("获取单个数据库连接器的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final EtlConnModifyTo modifyTo = dozerMapper.map(addTo, EtlConnModifyTo.class);
        modifyTo.setId(id);
        log.info("修改数据库连接器的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除数据库连接器的参数为：" + id);
        _svc.delById(id);
    }
}
