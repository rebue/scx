package rebue.scx.sgn.svc.ex;

import java.security.PublicKey;

/**
 * 签名验证服务
 */
public interface SgnKeySvc {

    /**
     * 根据密钥字符串获取公钥
     *
     * @param key 密钥字符串
     *
     * @return 返回公钥
     */
    PublicKey getPublicKey(String key);

}