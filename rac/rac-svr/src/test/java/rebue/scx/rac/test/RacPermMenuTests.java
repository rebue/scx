package rebue.scx.rac.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.IdRo;
import rebue.robotech.ro.PageRo;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacPermMenuMo;
import rebue.scx.rac.svc.RacPermMenuSvc;
import rebue.wheel.RandomEx;

/**
 * 权限菜单测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacPermMenuTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Reference
    private RacPermMenuSvc _svc;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        RacPermMenuMo mo = null;
        for (int i = 0; i < 20; i++) {
            mo = (RacPermMenuMo) RandomEx.randomPojo(RacPermMenuMo.class);
            mo.setId(null);
            log.info("添加权限菜单的参数为：" + mo);
            @SuppressWarnings("unchecked")
            final IdRo<Long> idRo = (IdRo<Long>) _svc.add(mo);
            log.info("添加权限菜单的返回值为：" + idRo);
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            mo.setId(idRo.getId());
        }
        final PageRo<RacPermMenuMo> listResult = _svc.list(null, 1, 5);
        log.info("查询权限菜单的返回值为：" + listResult);
        Assertions.assertEquals(ResultDic.SUCCESS, listResult.getResult());
        log.info("获取单个权限菜单的参数为：" + mo.getId());
        final RacPermMenuMo getByIdResult = _svc.getById(mo.getId());
        log.info("获取单个权限菜单的返回值为：" + getByIdResult);
        log.info("修改权限菜单的参数为：" + mo);
        final Ro modifyResult = _svc.modify(mo);
        log.info("修改权限菜单的返回值为：" + modifyResult);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyResult.getResult());
        log.info("删除权限菜单的参数为：" + mo.getId());
        final Ro deleteResult = _svc.del(mo.getId());
        log.info("删除权限菜单的返回值为：" + deleteResult);
        Assertions.assertEquals(ResultDic.SUCCESS, deleteResult.getResult());
    }
}
