package rebue.wxx.test.api;

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
import rebue.wheel.core.RandomEx;
import rebue.wxx.api.WxxMchApi;
import rebue.wxx.mo.WxxMchMo;
import rebue.wxx.to.WxxMchAddTo;
import rebue.wxx.to.WxxMchModifyTo;
import rebue.wxx.to.WxxMchPageTo;

/**
 * 商户(微信支付账户) API层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class WxxMchApiTests {

    /**
     * 要测试的API
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DubboReference
    private WxxMchApi _api;

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
        WxxMchAddTo addTo = null;
        String id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (WxxMchAddTo) RandomEx.randomPojo(WxxMchAddTo.class);
            log.info("添加商户(微信支付账户)的参数为：" + addTo);
            final Ro<IdRa<String>> idRo = _api.add(addTo);
            log.info("添加商户(微信支付账户)的返回值为：" + idRo);
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            id = idRo.getExtra().getId();
        }
        final Ro<PageRa<WxxMchMo>> pageResult = _api.page(new WxxMchPageTo());
        log.info("查询商户(微信支付账户)的返回值为：" + pageResult);
        Assertions.assertEquals(ResultDic.SUCCESS, pageResult.getResult());
        log.info("获取单个商户(微信支付账户)的参数为：" + id);
        final Ro<PojoRa<WxxMchMo>> getByIdResult = _api.getById(id);
        log.info("获取单个商户(微信支付账户)的返回值为：" + getByIdResult);
        final WxxMchModifyTo modifyTo = dozerMapper.map(addTo, WxxMchModifyTo.class);
        modifyTo.setId(id);
        log.info("修改商户(微信支付账户)的参数为：" + modifyTo);
        final Ro<?> modifyResult = _api.modify(modifyTo);
        log.info("修改商户(微信支付账户)的返回值为：" + modifyResult);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyResult.getResult());
        log.info("删除商户(微信支付账户)的参数为：" + id);
        final Ro<?> deleteResult = _api.del(id);
        log.info("删除商户(微信支付账户)的返回值为：" + deleteResult);
        Assertions.assertEquals(ResultDic.SUCCESS, deleteResult.getResult());
    }
}
