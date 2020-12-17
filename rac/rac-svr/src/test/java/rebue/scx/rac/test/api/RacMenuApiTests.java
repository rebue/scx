package rebue.scx.rac.test.api;

import java.io.IOException;

import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import rebue.scx.rac.mo.RacMenuMo;
import rebue.scx.rac.to.RacMenuAddTo;
import rebue.scx.rac.to.RacMenuModifyTo;
import rebue.scx.rac.to.RacMenuPageTo;
import rebue.scx.rac.api.RacMenuApi;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.wheel.RandomEx;

/**
 * 菜单 API层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacMenuApiTests {

    /**
     * 要测试的API
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DubboReference
    private RacMenuApi _api;

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
        RacMenuAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacMenuAddTo) RandomEx.randomPojo(RacMenuAddTo.class);
            log.info("添加菜单的参数为：" + addTo);
            final Ro<IdRa<Long>> idRo = _api.add(addTo);
            log.info("添加菜单的返回值为：" + idRo);
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            id = idRo.getExtra().getId();
        }
        final Ro<PageRa<RacMenuMo>> pageResult = _api.page(new RacMenuPageTo());
        log.info("查询菜单的返回值为：" + pageResult);
        Assertions.assertEquals(ResultDic.SUCCESS, pageResult.getResult());
        log.info("获取单个菜单的参数为：" + id);
        final Ro<PojoRa<RacMenuMo>> getByIdResult = _api.getById(id);
        log.info("获取单个菜单的返回值为：" + getByIdResult);

        final RacMenuModifyTo modifyTo = dozerMapper.map(addTo, RacMenuModifyTo.class);
        modifyTo.setId(id);
        log.info("修改菜单的参数为：" + modifyTo);
        final Ro<?> modifyResult = _api.modify(modifyTo);
        log.info("修改菜单的返回值为：" + modifyResult);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyResult.getResult());

        log.info("删除菜单的参数为：" + id);
        final Ro<?> deleteResult = _api.del(id);
        log.info("删除菜单的返回值为：" + deleteResult);
        Assertions.assertEquals(ResultDic.SUCCESS, deleteResult.getResult());
    }
}
