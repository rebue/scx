package rebue.scx.oap.test.api;

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
import rebue.scx.oap.api.OapIpWhiteListApi;
import rebue.scx.oap.mo.OapIpWhiteListMo;
import rebue.scx.oap.to.OapIpWhiteListAddTo;
import rebue.scx.oap.to.OapIpWhiteListModifyTo;
import rebue.scx.oap.to.OapIpWhiteListPageTo;
import rebue.wheel.core.RandomEx;

/**
 * 第三方应用IP白名单 API层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class OapIpWhiteListApiTests {

    /**
     * 要测试的API
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DubboReference
    private OapIpWhiteListApi _api;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper            dozerMapper;

    /**
     * 测试基本的增删改查
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() {
        OapIpWhiteListAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (OapIpWhiteListAddTo) RandomEx.randomPojo(OapIpWhiteListAddTo.class);
            log.info("添加第三方应用IP白名单的参数为：" + addTo);
            final Ro<IdRa<Long>> idRo = _api.add(addTo);
            log.info("添加第三方应用IP白名单的返回值为：" + idRo);
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            id = idRo.getExtra().getId();
        }
        final Ro<PageRa<OapIpWhiteListMo>> pageResult = _api.page(new OapIpWhiteListPageTo());
        log.info("查询第三方应用IP白名单的返回值为：" + pageResult);
        Assertions.assertEquals(ResultDic.SUCCESS, pageResult.getResult());
        log.info("获取单个第三方应用IP白名单的参数为：" + id);
        final Ro<PojoRa<OapIpWhiteListMo>> getByIdResult = _api.getById(id);
        log.info("获取单个第三方应用IP白名单的返回值为：" + getByIdResult);
        final OapIpWhiteListModifyTo modifyTo = dozerMapper.map(addTo, OapIpWhiteListModifyTo.class);
        modifyTo.setId(id);
        log.info("修改第三方应用IP白名单的参数为：" + modifyTo);
        final Ro<?> modifyResult = _api.modify(modifyTo);
        log.info("修改第三方应用IP白名单的返回值为：" + modifyResult);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyResult.getResult());
        log.info("删除第三方应用IP白名单的参数为：" + id);
        final Ro<?> deleteResult = _api.del(id);
        log.info("删除第三方应用IP白名单的返回值为：" + deleteResult);
        Assertions.assertEquals(ResultDic.SUCCESS, deleteResult.getResult());
    }
}
