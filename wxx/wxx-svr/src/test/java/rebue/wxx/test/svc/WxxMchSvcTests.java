package rebue.wxx.test.svc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import rebue.wheel.core.RandomEx;
import rebue.wxx.mo.WxxMchMo;
import rebue.wxx.svc.WxxMchSvc;
import rebue.wxx.to.WxxMchAddTo;
import rebue.wxx.to.WxxMchModifyTo;
import rebue.wxx.to.WxxMchPageTo;

/**
 * 商户(微信支付账户) Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class WxxMchSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private WxxMchSvc _svc;

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
            final WxxMchMo addRo = _svc.add(addTo);
            log.info("添加商户(微信支付账户)的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final PageInfo<WxxMchMo> pageResult = _svc.page(new WxxMchPageTo());
        log.info("查询商户(微信支付账户)的返回值为：" + pageResult);
        Assertions.assertNotNull(pageResult);
        log.info("获取单个商户(微信支付账户)的参数为：" + id);
        WxxMchMo getByIdResult = _svc.getById(id);
        log.info("获取单个商户(微信支付账户)的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final WxxMchModifyTo modifyTo = dozerMapper.map(addTo, WxxMchModifyTo.class);
        modifyTo.setId(id);
        log.info("修改商户(微信支付账户)的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除商户(微信支付账户)的参数为：" + id);
        _svc.delById(id);
    }
}
