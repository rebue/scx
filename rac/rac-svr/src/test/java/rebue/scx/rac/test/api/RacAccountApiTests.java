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
import rebue.scx.rac.api.RacAccountApi;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 账户 API层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class RacAccountApiTests {

    /**
     * 要测试的API
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DubboReference
    private RacAccountApi _api;

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
        RacAccountAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (RacAccountAddTo) RandomEx.randomPojo(RacAccountAddTo.class);
            log.info("添加账户的参数为：" + addTo);
            final Ro<IdRa<Long>> idRo = _api.add(addTo);
            log.info("添加账户的返回值为：" + idRo);
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            id = idRo.getExtra().getId();
        }
        final Ro<PageRa<RacAccountMo>> pageResult = _api.page(new RacAccountPageTo());
        log.info("查询账户的返回值为：" + pageResult);
        Assertions.assertEquals(ResultDic.SUCCESS, pageResult.getResult());
        log.info("获取单个账户的参数为：" + id);
        final Ro<PojoRa<RacAccountMo>> getByIdResult = _api.getById(id);
        log.info("获取单个账户的返回值为：" + getByIdResult);
        final RacAccountModifyTo modifyTo = dozerMapper.map(addTo, RacAccountModifyTo.class);
        modifyTo.setId(id);
        log.info("修改账户的参数为：" + modifyTo);
        final Ro<?> modifyResult = _api.modify(modifyTo);
        log.info("修改账户的返回值为：" + modifyResult);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyResult.getResult());
        log.info("删除账户的参数为：" + id);
        final Ro<?> deleteResult = _api.del(id);
        log.info("删除账户的返回值为：" + deleteResult);
        Assertions.assertEquals(ResultDic.SUCCESS, deleteResult.getResult());
    }
}
