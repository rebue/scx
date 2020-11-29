package rebue.scx.rac.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import rebue.scx.rac.mo.RacLockLogMo;
import rebue.scx.rac.svc.RacLockLogSvc;
import rebue.scx.rac.to.RacLockLogAddTo;
import rebue.scx.rac.to.RacLockLogListTo;
import rebue.scx.rac.to.RacLockLogModifyTo;
import rebue.wheel.RandomEx;

/**
 * 锁定日志 Service层测试
 * 
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacLockLogSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacLockLogSvc _svc;

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
        RacLockLogAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacLockLogAddTo) RandomEx.randomPojo(RacLockLogAddTo.class);
            log.info("添加锁定日志的参数为：" + addTo);
            final Long addRo = _svc.add(addTo);
            log.info("添加锁定日志的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo;
        }
        final PageInfo<RacLockLogMo> listResult = _svc.list(new RacLockLogListTo());
        log.info("查询锁定日志的返回值为：" + listResult);
        Assertions.assertNotNull(listResult);
        log.info("获取单个锁定日志的参数为：" + id);
        RacLockLogMo getByIdResult = _svc.getById(id);
        log.info("获取单个锁定日志的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final RacLockLogModifyTo modifyTo = dozerMapper.map(addTo, RacLockLogModifyTo.class);
        modifyTo.setId(id);
        log.info("修改锁定日志的参数为：" + modifyTo);
        final Boolean modifyResult = _svc.modifyById(modifyTo);
        log.info("修改锁定日志的返回值为：" + modifyResult);
        Assertions.assertTrue(modifyResult);
        log.info("删除锁定日志的参数为：" + id);
        final Boolean deleteResult = _svc.delById(id);
        log.info("删除锁定日志的返回值为：" + deleteResult);
        Assertions.assertTrue(deleteResult);
    }
}
