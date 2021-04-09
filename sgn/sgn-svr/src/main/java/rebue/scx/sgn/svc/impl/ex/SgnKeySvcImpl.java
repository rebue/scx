package rebue.scx.sgn.svc.impl.ex;

import java.security.PublicKey;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rebue.sbs.cache.CacheManagerName;
import rebue.scx.sgn.svc.ex.SgnKeySvc;
import rebue.wheel.turing.Sm2Utils;

/**
 * 密钥服务的实现类
 *
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "rebue.scx.sgn.key.sign-id")
public class SgnKeySvcImpl implements SgnKeySvc {

    /**
     * 根据密钥字符串获取公钥
     *
     * @param key 密钥字符串
     *
     * @return 返回公钥
     */
    @Override
    @Cacheable(cacheManager = CacheManagerName.CAFFEINE_CACHE_MANAGER)
    public PublicKey getPublicKey(final String key) {
        try {
            return Sm2Utils.getPublicKeyFromString(key);
        } catch (final Exception e) {
            final String msg = "获取公钥出错";
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

}
