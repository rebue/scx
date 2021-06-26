package rebue.scx.sgn.svc.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.qaware.tools.collectioncacheableforspring.CollectionCacheable;
import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.sbs.cache.CacheManagerName;
import rebue.sbs.cache.RebueRedisCacheWriter;
import rebue.scx.sgn.dao.SgnSecretDao;
import rebue.scx.sgn.jo.SgnSecretJo;
import rebue.scx.sgn.mapper.SgnSecretMapper;
import rebue.scx.sgn.mo.SgnSecretMo;
import rebue.scx.sgn.svc.SgnSecretSvc;
import rebue.scx.sgn.to.SgnSecretAddTo;
import rebue.scx.sgn.to.SgnSecretDelTo;
import rebue.scx.sgn.to.SgnSecretListTo;
import rebue.scx.sgn.to.SgnSecretModifyTo;
import rebue.scx.sgn.to.SgnSecretOneTo;
import rebue.scx.sgn.to.SgnSecretPageTo;
import rebue.wheel.serialization.fst.FstUtils;

/**
 * 签名密钥服务实现
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
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
@CacheConfig(cacheNames = "rebue.scx.sgn.secret.sign-id")
public class SgnSecretSvcImpl extends
    BaseSvcImpl<java.lang.Long, SgnSecretAddTo, SgnSecretModifyTo, SgnSecretDelTo, SgnSecretOneTo, SgnSecretListTo, SgnSecretPageTo, SgnSecretMo, SgnSecretJo, SgnSecretMapper, SgnSecretDao>
    implements SgnSecretSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private SgnSecretSvc thisSvc;

    @Autowired
    @Qualifier(CacheManagerName.REDIS_CACHE_MANAGER)
    private CacheManager cacheManager;

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.Long, SgnSecretAddTo, SgnSecretModifyTo, SgnSecretDelTo, SgnSecretOneTo, SgnSecretListTo, SgnSecretPageTo, SgnSecretMo, SgnSecretJo> getThisSvc() {
        return thisSvc;
    }

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<SgnSecretMo> getMoClass() {
        return SgnSecretMo.class;
    }

    @Override
    @CachePut(key = "#mo.id")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public SgnSecretMo modifyMoById(final SgnSecretMo mo) {
        return super.modifyMoById(mo);
    }

    @Override
    @CacheEvict
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delById(final Long id) {
        super.delById(id);
    }

    @Override
    @Cacheable
    public SgnSecretMo getById(final Long id) {
        return super.getById(id);
    }

    @Override
    @CollectionCacheable
    public Map<Long, SgnSecretMo> mapAll() {
        return super.listAll().stream().collect(Collectors.toMap(SgnSecretMo::getId, item -> item));
    }

    @Override
    public List<SgnSecretMo> listCacheAll() {
        // parse org.springframework.cache.Cache to javax.cache.Cache by using getNativeCache() method and use java iterator as javax.cache.Cache already extends Iterable>.
        // @SuppressWarnings("rawtypes")
        final RebueRedisCacheWriter cache = (RebueRedisCacheWriter) cacheManager.getCache("rebue.scx.sgn.secret.sign-id").getNativeCache();
        // cache.get("").;
        // @SuppressWarnings("unchecked")
        // final Iterator<Cache.Entry<String, Object>> iterator = nativeCache.iterator();
        //
        // while (iterator.hasNext()) {
        // final Object value = iterator.next().getValue();
        // System.out.println(value);
        // }
        return cache.keys("rebue.scx.sgn.secret.sign-id").stream().map(item -> (SgnSecretMo) FstUtils.readObject(item)).collect(Collectors.toList());
        // return null;
    }

}
