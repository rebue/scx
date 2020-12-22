package rebue.scx.sgn.svc;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;

import rebue.scx.sgn.to.SgnSecretAddTo;
import rebue.scx.sgn.to.SgnSecretModifyTo;

/**
 * 签名密钥服务接口
 *
 * 在接口上方必须写上 @Validated 注解；
 * 有分组时，在方法上方必须写上 @Validated 注解及分组；
 * 参数是POJO类时用 @Valid 注解在参数类型的前面进行修饰；
 * 而如果是普通参数，则在方法的上方写上 @Validated 注解，具体约束的注解直接写在参数类型的前面
 */
@Validated
@CacheConfig(cacheNames = "rebue.scx.sgn.svc.secret.sign-id")
public interface SgnSecretSvc {
    /**
     * 添加密钥
     *
     * @param to 添加的参数
     *
     * @return 如果成功，且仅添加一条记录，正常返回，否则会抛出运行时异常
     */
    @CachePut(key = "#to.id")
    String add(@Valid SgnSecretAddTo to);

    /**
     * 修改密钥
     *
     * @param to 修改的参数
     *
     * @return 如果成功，且仅修改一条记录，正常返回，否则会抛出运行时异常
     */
    @CachePut(key = "#to.id")
    String modifyById(@Valid SgnSecretModifyTo to);

    /**
     * 通过ID删除密钥
     *
     * @param id 要删除密钥的ID
     *
     * @return 如果成功，且删除一条记录，正常返回，否则会抛出运行时异常
     */
    @CacheEvict
    void delById(@NotNull String id);

    /**
     * 根据ID获取密钥
     *
     * @param id 要获取密钥的ID
     *
     * @return 密钥
     */
    @Cacheable
    String getSecretById(@NotNull String id);

}
