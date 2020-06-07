package rebue.scx.rac.test;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.IdRo;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacRoleMo;
import rebue.scx.rac.svc.RacRoleSvc;
import rebue.wheel.RandomEx;

/**
 * 角色信息
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacRoleTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Reference
    private RacRoleSvc _svc;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        RacRoleMo mo = null;
        for (int i = 0; i < 20; i++) {
            mo = (RacRoleMo) RandomEx.randomPojo(RacRoleMo.class);
            mo.setId(null);
            log.info("添加角色信息的参数为：" + mo);
            @SuppressWarnings("unchecked")
            final IdRo<String> idRo = (IdRo<String>) _svc.add(mo);
            log.info("添加角色信息的返回值为：" + idRo);
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            mo.setId(idRo.getId());
        }
        final PageInfo<RacRoleMo> listResult = _svc.list(null, 1, 5);
        log.info("查询角色信息的返回值为：" + listResult);
        log.info("获取单个角色信息的参数为：" + mo.getId());
        final RacRoleMo getByIdResult = _svc.getById(mo.getId());
        log.info("获取单个角色信息的返回值为：" + getByIdResult);
        log.info("修改角色信息的参数为：" + mo);
        final Ro modifyResult = _svc.modify(mo);
        log.info("修改角色信息的返回值为：" + modifyResult);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyResult.getResult());
        log.info("删除角色信息的参数为：" + mo.getId());
        final Ro deleteResult = _svc.del(mo.getId());
        log.info("删除角色信息的返回值为：" + deleteResult);
        Assertions.assertEquals(ResultDic.SUCCESS, deleteResult.getResult());
    }
}
