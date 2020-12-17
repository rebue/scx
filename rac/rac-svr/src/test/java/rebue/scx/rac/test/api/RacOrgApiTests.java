package rebue.scx.rac.test.api;

import java.io.IOException;

import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import rebue.scx.rac.mo.RacOrgMo;
import rebue.scx.rac.to.RacOrgAddTo;
import rebue.scx.rac.to.RacOrgModifyTo;
import rebue.scx.rac.to.RacOrgPageTo;
import rebue.scx.rac.api.RacOrgApi;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.wheel.RandomEx;

/**
 * 组织 API层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacOrgApiTests {

    /**
     * 要测试的API
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DubboReference
    private RacOrgApi _api;

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
        RacOrgAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacOrgAddTo) RandomEx.randomPojo(RacOrgAddTo.class);
            log.info("添加组织的参数为：" + addTo);
            final Ro<IdRa<Long>> idRo = _api.add(addTo);
            log.info("添加组织的返回值为：" + idRo);
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            id = idRo.getExtra().getId();
        }
        final Ro<PageRa<RacOrgMo>> pageResult = _api.page(new RacOrgPageTo());
        log.info("查询组织的返回值为：" + pageResult);
        Assertions.assertEquals(ResultDic.SUCCESS, pageResult.getResult());
        log.info("获取单个组织的参数为：" + id);
        final Ro<PojoRa<RacOrgMo>> getByIdResult = _api.getById(id);
        log.info("获取单个组织的返回值为：" + getByIdResult);

        final RacOrgModifyTo modifyTo = dozerMapper.map(addTo, RacOrgModifyTo.class);
        modifyTo.setId(id);
        log.info("修改组织的参数为：" + modifyTo);
        final Ro<?> modifyResult = _api.modify(modifyTo);
        log.info("修改组织的返回值为：" + modifyResult);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyResult.getResult());

        log.info("删除组织的参数为：" + id);
        final Ro<?> deleteResult = _api.del(id);
        log.info("删除组织的返回值为：" + deleteResult);
        Assertions.assertEquals(ResultDic.SUCCESS, deleteResult.getResult());
    }
}
