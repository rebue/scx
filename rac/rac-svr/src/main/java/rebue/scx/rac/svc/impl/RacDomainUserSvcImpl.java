package rebue.scx.rac.svc.impl;

import javax.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.rac.dao.RacDomainUserDao;
import rebue.scx.rac.jo.RacDomainUserJo;
import rebue.scx.rac.mapper.RacDomainUserMapper;
import rebue.scx.rac.mo.RacDomainUserMo;
import rebue.scx.rac.svc.RacDomainUserSvc;
import rebue.scx.rac.to.RacDomainUserAddTo;
import rebue.scx.rac.to.RacDomainUserDelTo;
import rebue.scx.rac.to.RacDomainUserListTo;
import rebue.scx.rac.to.RacDomainUserModifyTo;
import rebue.scx.rac.to.RacDomainUserOneTo;

/**
 * 领域用户服务实现
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
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
public class RacDomainUserSvcImpl extends
    BaseSvcImpl<java.lang.Long, RacDomainUserAddTo, RacDomainUserModifyTo, RacDomainUserDelTo, RacDomainUserOneTo, RacDomainUserListTo, RacDomainUserMo, RacDomainUserJo, RacDomainUserMapper, RacDomainUserDao>
    implements RacDomainUserSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private RacDomainUserSvc thisSvc;

    /**
     * 泛型MO的class(应为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<RacDomainUserMo> getMoClass() {
        return RacDomainUserMo.class;
    }
}