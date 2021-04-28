package rebue.scx.rrl.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rrl.mo.RrlRespLogMo;
import rebue.scx.rrl.svc.RrlRespLogSvc;
import rebue.scx.rrl.to.RrlRespLogAddTo;
import rebue.scx.rrl.to.RrlRespLogModifyTo;
import rebue.scx.rrl.to.RrlRespLogPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 响应日志 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RrlRespLogSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RrlRespLogSvc _svc;

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
        RrlRespLogAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RrlRespLogAddTo) RandomEx.randomPojo(RrlRespLogAddTo.class);
            log.info("添加响应日志的参数为：" + addTo);
            final RrlRespLogMo addRo = _svc.add(addTo);
            log.info("添加响应日志的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<RrlRespLogMo> pageResult = _svc.page(new RrlRespLogPageTo());
        log.info("查询响应日志的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个响应日志的参数为：" + id);
        RrlRespLogMo getByIdResult = _svc.getById(id);
        log.info("获取单个响应日志的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RrlRespLogModifyTo modifyTo = dozerMapper.map(addTo, RrlRespLogModifyTo.class);
        modifyTo.setId(id);
        log.info("修改响应日志的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除响应日志的参数为：" + id);
        _svc.delById(id);
    }
}
