package rebue.scx.rac.svc.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.and;
import static org.mybatis.dynamic.sql.SqlBuilder.equalTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isTrue;
import static rebue.scx.rac.mapper.RacAccountRoleDynamicSqlSupport.racAccountRole;
import static rebue.scx.rac.mapper.RacPermDynamicSqlSupport.racPerm;
import static rebue.scx.rac.mapper.RacPermUrnDynamicSqlSupport.racPermUrn;
import static rebue.scx.rac.mapper.RacRoleDynamicSqlSupport.racRole;
import static rebue.scx.rac.mapper.RacRolePermDynamicSqlSupport.racRolePerm;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.rac.dao.RacPermUrnDao;
import rebue.scx.rac.jo.RacPermUrnJo;
import rebue.scx.rac.mapper.RacPermUrnMapper;
import rebue.scx.rac.mo.RacPermUrnMo;
import rebue.scx.rac.svc.RacPermUrnSvc;
import rebue.scx.rac.to.RacPermUrnAddTo;
import rebue.scx.rac.to.RacPermUrnDelTo;
import rebue.scx.rac.to.RacPermUrnListTo;
import rebue.scx.rac.to.RacPermUrnModifyTo;
import rebue.scx.rac.to.RacPermUrnOneTo;
import rebue.scx.rac.to.RacPermUrnPageTo;

/**
 * 权限URN服务实现
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
public class RacPermUrnSvcImpl extends
    BaseSvcImpl<java.lang.Long, RacPermUrnAddTo, RacPermUrnModifyTo, RacPermUrnDelTo, RacPermUrnOneTo, RacPermUrnListTo, RacPermUrnPageTo, RacPermUrnMo, RacPermUrnJo, RacPermUrnMapper, RacPermUrnDao>
    implements RacPermUrnSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private RacPermUrnSvc thisSvc;

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<RacPermUrnMo> getMoClass() {
        return RacPermUrnMo.class;
    }

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.Long, RacPermUrnAddTo, RacPermUrnModifyTo, RacPermUrnDelTo, RacPermUrnOneTo, RacPermUrnListTo, RacPermUrnPageTo, RacPermUrnMo, RacPermUrnJo> getThisSvc() {
        return thisSvc;
    }

    /**
     * 添加修改URN
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void modifyByPermId(RacPermUrnAddTo to) {
        // 先删除
        final RacPermUrnMo delMo = new RacPermUrnMo();
        delMo.setPermId(to.getPermId());
        _mapper.deleteSelective(delMo);
        List<String> urns = to.getUrn();
        // 后添加
        for (String urn : urns) {
            final RacPermUrnMo mo = new RacPermUrnMo();
            mo.setPermId(to.getPermId());
            mo.setUrn(urn);
            getThisSvc().addMo(mo);
        }
    }

    /**
     * 获取账户的菜单列表
     *
     * @param accountId 账户ID
     *
     * @return 指定账户的菜单列表
     */
    @Override
    public List<String> getUrnsOfAccount(final Long accountId) {
        final List<RacPermUrnMo> list = _mapper.select(c -> c.join(racPerm).on(racPerm.id, equalTo(racPermUrn.permId)).join(racRolePerm).on(racRolePerm.permId, equalTo(racPerm.id))
            .join(racRole).on(racRole.id, equalTo(racRolePerm.roleId)).join(racAccountRole).on(racAccountRole.roleId, equalTo(racRole.id))
            .where(racAccountRole.accountId, isEqualTo(accountId), and(racPerm.isEnabled, isTrue()), and(racRole.isEnabled, isTrue())));
        return list.stream().map(RacPermUrnMo::getUrn).distinct().collect(Collectors.toList());
    }
}
