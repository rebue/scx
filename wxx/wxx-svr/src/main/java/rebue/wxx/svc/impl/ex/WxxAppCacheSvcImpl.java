package rebue.wxx.svc.impl.ex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import rebue.sbs.cache.CacheManagerName;
import rebue.sbs.cache.RebueRedisCacheWriter;
import rebue.wxx.cco.WxxAppCco;
import rebue.wxx.co.CacheCo;
import rebue.wxx.svc.ex.WxxAppCacheSvc;

/**
 * APP缓存服务实现
 *
 * <pre>
 * 注意：
 * 1. 查询数据库操作的方法，不用设置默认 @Transactional
 *    在类上方已经设置默认为 readOnly=true, propagation=Propagation.SUPPORTS
 *    而涉及到 增删改 数据库操作的方法时，要设置 readOnly=false, propagation=Propagation.REQUIRED
 * 2. 事务不会针对受控异常（checked exception）回滚
 *    要想回滚事务，须抛出运行时异常(RuntimeException)
 * 3. 如果类上方不带任何参数的 @Transactional 注解时，如同下面的设置
 *    propagation(传播模式)=REQUIRED，readOnly=false，isolation(事务隔离级别)=READ_COMMITTED
 * </pre>
 *
 */
@Service
public class WxxAppCacheSvcImpl implements WxxAppCacheSvc {

    @Autowired
    @Qualifier(CacheManagerName.REDIS_CACHE_MANAGER)
    private CacheManager cacheManager;

    /**
     * 放入缓存
     */
    @Override
    public void putToCache(final WxxAppCco cco) {
        cacheManager.getCache(CacheCo.WXX_APP_CACHE_NAME).put(cco.getAppId(), cco);
    }

    /**
     * 删除缓存
     */
    @Override
    public void delById(final String id) {
        cacheManager.getCache(CacheCo.WXX_APP_CACHE_NAME).evict(id);
    }

    /**
     * 获取缓存
     */
    @Override
    public WxxAppCco getById(final String id) {
        return (WxxAppCco) cacheManager.getCache(CacheCo.WXX_APP_CACHE_NAME).get(id).get();
    }

    /**
     * 获取缓存列表
     */
    @Override
    public List<WxxAppCco> listAll() {
        final RebueRedisCacheWriter cache = (RebueRedisCacheWriter) cacheManager.getCache(CacheCo.WXX_APP_CACHE_NAME).getNativeCache();
        return cache.listAll(CacheCo.WXX_APP_CACHE_NAME);
    }
}
