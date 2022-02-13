package rebue.scx.sgn.test.svc;

import java.security.KeyPair;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.dozermapper.core.Mapper;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.sgn.mo.SgnSecretMo;
import rebue.scx.sgn.svc.SgnSecretSvc;
import rebue.scx.sgn.to.SgnSecretAddTo;
import rebue.scx.sgn.to.SgnSecretModifyTo;
import rebue.wheel.core.RandomEx;
import rebue.wheel.turing.Sm2Utils;

/**
 * 签名密钥 Service层测试
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Slf4j
@SpringBootTest
public class SgnSecretSvcTests {

    /**
     * 要测试的微服务
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Autowired
    private SgnSecretSvc _svc;

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
            final SgnSecretMo addRo = _svc.add(addTo);
            log.info("添加签名密钥的返回值为：" + addRo);
            Assertions.assertNotNull(addRo);
            id = addRo.getId();
        }
        final List<SgnSecretMo> listResult = _svc.listCacheAll();
        log.info("查询签名密钥的返回值为: {}", listResult);
        Assertions.assertNotNull(listResult);
        log.info("获取单个签名密钥的参数为: {}", id);
        final SgnSecretMo getByIdResult = _svc.getById(id);
        log.info("获取单个签名密钥的返回值为：" + getByIdResult);
        Assertions.assertNotNull(getByIdResult);
        final SgnSecretModifyTo modifyTo = dozerMapper.map(addTo, SgnSecretModifyTo.class);
        modifyTo.setId(id);
        log.info("修改签名密钥的参数为：" + modifyTo);
        _svc.modifyById(modifyTo);
        log.info("删除签名密钥的参数为：" + id);
        _svc.delById(id);
    }
}
