package rebue.scx.rac.test.svc;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rebue.scx.rac.mo.RacSignInLogMo;
import rebue.scx.rac.svc.RacSignInLogSvc;
import rebue.wheel.RandomEx;

/**
 * 用户登录日志 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacSignInLogSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private RacSignInLogSvc _svc;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        RacSignInLogMo mo = null;
        for (int i = 0; i < 20; i++) {
            mo = (RacSignInLogMo) RandomEx.randomPojo(RacSignInLogMo.class);
            mo.setId(null);
            log.info("添加用户登录日志的参数为：" + mo);
            final Long addRo = _svc.add(mo);
            log.info("添加用户登录日志的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            mo.setId(addRo);
        }
        final PageInfo<RacSignInLogMo> listResult = _svc.list(null, 1, 5, null, 10);
        log.info("查询用户登录日志的返回值为：" + listResult);
        Assertions.assertNotNull(listResult);
        log.info("获取单个用户登录日志的参数为：" + mo.getId());
        RacSignInLogMo getByIdResult = _svc.getById(mo.getId());
        log.info("获取单个用户登录日志的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        log.info("修改用户登录日志的参数为：" + mo);
        final Boolean modifyResult = _svc.modify(mo);
        log.info("修改用户登录日志的返回值为：" + modifyResult);
        Assertions.assertTrue(modifyResult);
        log.info("删除用户登录日志的参数为：" + mo.getId());
        final Boolean deleteResult = _svc.del(mo.getId());
        log.info("删除用户登录日志的返回值为：" + deleteResult);
        Assertions.assertTrue(deleteResult);
    }
}
