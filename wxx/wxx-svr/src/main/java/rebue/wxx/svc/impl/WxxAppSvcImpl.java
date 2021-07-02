package rebue.wxx.svc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.sbs.cache.CacheManagerName;
import rebue.sbs.cache.RebueRedisCacheWriter;
import rebue.wheel.api.OrikaUtils;
import rebue.wxx.cco.WxxAppCco;
import rebue.wxx.co.CacheCo;
import rebue.wxx.dao.WxxAppDao;
import rebue.wxx.jo.WxxAppJo;
import rebue.wxx.mapper.WxxAppMapper;
import rebue.wxx.mo.WxxAppMo;
import rebue.wxx.svc.WxxAppSvc;
import rebue.wxx.to.WxxAppAddTo;
import rebue.wxx.to.WxxAppDelTo;
import rebue.wxx.to.WxxAppListTo;
import rebue.wxx.to.WxxAppModifyTo;
import rebue.wxx.to.WxxAppOneTo;
import rebue.wxx.to.WxxAppPageTo;

/**
 * APP服务实现
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
 * @mbg.dontOverWriteAnnotation
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
public class WxxAppSvcImpl
    extends BaseSvcImpl<java.lang.String, WxxAppAddTo, WxxAppModifyTo, WxxAppDelTo, WxxAppOneTo, WxxAppListTo, WxxAppPageTo, WxxAppMo, WxxAppJo, WxxAppMapper, WxxAppDao>
    implements WxxAppSvc {

    @Autowired
    @Qualifier(CacheManagerName.REDIS_CACHE_MANAGER)
    private CacheManager cacheManager;

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private WxxAppSvc    thisSvc;

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.String, WxxAppAddTo, WxxAppModifyTo, WxxAppDelTo, WxxAppOneTo, WxxAppListTo, WxxAppPageTo, WxxAppMo, WxxAppJo> getThisSvc() {
        return thisSvc;
    }

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<WxxAppMo> getMoClass() {
        return WxxAppMo.class;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public WxxAppMo modifyMoById(final WxxAppMo mo) {
        final WxxAppMo result = super.modifyMoById(mo);
        // 放入缓存
        final WxxAppCco cco = OrikaUtils.map(mo, WxxAppCco.class);
        cacheManager.getCache(CacheCo.WXX_APP_CACHE_NAME).put(cco.getAppId(), cco);
        return result;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delById(final String id) {
        // 删除缓存
        cacheManager.getCache(CacheCo.WXX_APP_CACHE_NAME).evict(id);
        super.delById(id);
    }

    @Override
    public WxxAppMo getById(final String id) {
        final WxxAppMo result = super.getById(id);
        // 如果缓存中没有则放入缓存
        final WxxAppCco cco = OrikaUtils.map(result, WxxAppCco.class);
        cacheManager.getCache(CacheCo.WXX_APP_CACHE_NAME).putIfAbsent(cco.getAppId(), cco);
        return result;
    }

    @Override
    public void putCco(final WxxAppCco cco) {
        cacheManager.getCache(CacheCo.WXX_APP_CACHE_NAME).put(cco.getAppId(), cco);
    }

    @Override
    public List<WxxAppCco> listCcoAll() {
        final RebueRedisCacheWriter cache = (RebueRedisCacheWriter) cacheManager.getCache(CacheCo.WXX_APP_CACHE_NAME).getNativeCache();
        final List<WxxAppCco> listCacheAll = cache.listAll(CacheCo.WXX_APP_CACHE_NAME);
        if (listCacheAll != null && !listCacheAll.isEmpty()) {
            return listCacheAll;
        }
        final List<WxxAppCco> result = OrikaUtils.mapAsList(super.listAll(), WxxAppCco.class);
        return result;
    }
}
