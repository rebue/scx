package rebue.scx.rac.test.api;

import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.dozermapper.core.Mapper;
import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacPersonApi;
import rebue.scx.rac.mo.RacPersonMo;
import rebue.scx.rac.to.RacPersonAddTo;
import rebue.scx.rac.to.RacPersonModifyTo;
import rebue.scx.rac.to.RacPersonPageTo;
import rebue.wheel.RandomEx;

/**
 * 个人 API层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacPersonApiTests {

    /**
     * 要测试的API
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DubboReference
    private RacPersonApi _api;

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
        RacPersonAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacPersonAddTo) RandomEx.randomPojo(RacPersonAddTo.class);
            log.info("添加个人的参数为：" + addTo);
            final Ro<IdRa<Long>> idRo = _api.add(addTo);
            log.info("添加个人的返回值为：" + idRo);
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            id = idRo.getExtra().getId();
        }
        final Ro<PageRa<RacPersonMo>> pageResult = _api.page(new RacPersonPageTo());
        log.info("查询个人的返回值为：" + pageResult);
        Assertions.assertEquals(ResultDic.SUCCESS, pageResult.getResult());
        log.info("获取单个个人的参数为：" + id);
        final Ro<PojoRa<RacPersonMo>> getByIdResult = _api.getById(id);
        log.info("获取单个个人的返回值为：" + getByIdResult);
        final RacPersonModifyTo modifyTo = dozerMapper.map(addTo, RacPersonModifyTo.class);
        modifyTo.setId(id);
        log.info("修改个人的参数为：" + modifyTo);
        final Ro<?> modifyResult = _api.modify(modifyTo);
        log.info("修改个人的返回值为：" + modifyResult);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyResult.getResult());
        log.info("删除个人的参数为：" + id);
        final Ro<?> deleteResult = _api.del(id);
        log.info("删除个人的返回值为：" + deleteResult);
        Assertions.assertEquals(ResultDic.SUCCESS, deleteResult.getResult());
    }
}
