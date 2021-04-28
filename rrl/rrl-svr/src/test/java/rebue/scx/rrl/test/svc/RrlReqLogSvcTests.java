package rebue.scx.rrl.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rrl.mo.RrlReqLogMo;
import rebue.scx.rrl.svc.RrlReqLogSvc;
import rebue.scx.rrl.to.RrlReqLogAddTo;
import rebue.scx.rrl.to.RrlReqLogModifyTo;
import rebue.scx.rrl.to.RrlReqLogPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 请求日志 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RrlReqLogSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RrlReqLogSvc _svc;

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
        RrlReqLogAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RrlReqLogAddTo) RandomEx.randomPojo(RrlReqLogAddTo.class);
            log.info("添加请求日志的参数为：" + addTo);
            final RrlReqLogMo addRo = _svc.add(addTo);
            log.info("添加请求日志的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<RrlReqLogMo> pageResult = _svc.page(new RrlReqLogPageTo());
        log.info("查询请求日志的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个请求日志的参数为：" + id);
        RrlReqLogMo getByIdResult = _svc.getById(id);
        log.info("获取单个请求日志的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RrlReqLogModifyTo modifyTo = dozerMapper.map(addTo, RrlReqLogModifyTo.class);
        modifyTo.setId(id);
        log.info("修改请求日志的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除请求日志的参数为：" + id);
        _svc.delById(id);
    }
}
