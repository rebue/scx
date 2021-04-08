package rebue.scx.sgn.svc.ex;

import java.security.PublicKey;

import rebue.scx.sgn.mo.SgnSecretMo;

/**
 * 签名验证服务
 */
public interface SgnKeySvc {

    /**
     * 根据签名密钥实体获取公钥
     *
     * @param signId 签名ID
     *
     * @return 返回公钥
     */
    PublicKey getPublicKey(SgnSecretMo mo);

}