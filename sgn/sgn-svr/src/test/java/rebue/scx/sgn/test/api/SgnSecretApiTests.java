package rebue.scx.sgn.test.api;

import java.security.KeyPair;

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
import rebue.scx.sgn.api.SgnSecretApi;
import rebue.scx.sgn.mo.SgnSecretMo;
import rebue.scx.sgn.to.SgnSecretAddTo;
import rebue.scx.sgn.to.SgnSecretModifyTo;
import rebue.scx.sgn.to.SgnSecretPageTo;
import rebue.wheel.RandomEx;
import rebue.wheel.turing.Sm2Utils;

/**
 * 签名密钥 API层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class SgnSecretApiTests {

    /**
     * 要测试的API
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DubboReference
    private SgnSecretApi _api;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private Mapper       dozerMapper;

    /**
     * 测试基本的增删改查
     */
    @Test
    public void testCrud() {
        SgnSecretAddTo addTo = null;
        Long id = null;
        for (int i = 0; i < 20; i++) {
            addTo = (SgnSecretAddTo) RandomEx.randomPojo(SgnSecretAddTo.class);
            // XXX 生成公钥并保存
            final KeyPair keyPair = Sm2Utils.generateKeyPair();
            addTo.setSecret(Sm2Utils.getPublicKeyString(keyPair));
            log.info("添加签名密钥的参数为：" + addTo);
            final Ro<IdRa<Long>> idRo = _api.add(addTo);
            log.info("添加签名密钥的返回值为：" + idRo);
            Assertions.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            id = idRo.getExtra().getId();
        }
        final Ro<PageRa<SgnSecretMo>> pageResult = _api.page(new SgnSecretPageTo());
        log.info("查询签名密钥的返回值为：" + pageResult);
        Assertions.assertEquals(ResultDic.SUCCESS, pageResult.getResult());
        log.info("获取单个签名密钥的参数为：" + id);
        final Ro<PojoRa<SgnSecretMo>> getByIdResult = _api.getById(id);
        log.info("获取单个签名密钥的返回值为：" + getByIdResult);
        final SgnSecretModifyTo modifyTo = dozerMapper.map(addTo, SgnSecretModifyTo.class);
        modifyTo.setId(id);
        log.info("修改签名密钥的参数为：" + modifyTo);
        final Ro<?> modifyResult = _api.modify(modifyTo);
        log.info("修改签名密钥的返回值为：" + modifyResult);
        Assertions.assertEquals(ResultDic.SUCCESS, modifyResult.getResult());
        log.info("删除签名密钥的参数为：" + id);
        final Ro<?> deleteResult = _api.del(id);
        log.info("删除签名密钥的返回值为：" + deleteResult);
        Assertions.assertEquals(ResultDic.SUCCESS, deleteResult.getResult());
    }
}
