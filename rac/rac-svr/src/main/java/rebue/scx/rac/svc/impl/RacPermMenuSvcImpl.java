package rebue.scx.rac.svc.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.and;
import static org.mybatis.dynamic.sql.SqlBuilder.equalTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isTrue;
import static rebue.scx.rac.mapper.RacAccountRoleDynamicSqlSupport.racAccountRole;
import static rebue.scx.rac.mapper.RacPermDynamicSqlSupport.racPerm;
import static rebue.scx.rac.mapper.RacPermMenuDynamicSqlSupport.racPermMenu;
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
import rebue.scx.rac.dao.RacPermMenuDao;
import rebue.scx.rac.jo.RacPermMenuJo;
import rebue.scx.rac.mapper.RacPermMenuMapper;
import rebue.scx.rac.mo.RacPermMenuMo;
import rebue.scx.rac.svc.RacPermMenuSvc;
import rebue.scx.rac.to.RacPermMenuAddTo;
import rebue.scx.rac.to.RacPermMenuDelTo;
import rebue.scx.rac.to.RacPermMenuListTo;
import rebue.scx.rac.to.RacPermMenuModifyTo;
import rebue.scx.rac.to.RacPermMenuOneTo;
import rebue.scx.rac.to.RacPermMenuPageTo;
import rebue.scx.rac.to.ex.RacPermMenusAddTo;
import rebue.wheel.core.util.OrikaUtils;

/**
 * 权限菜单服务实现
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
public class RacPermMenuSvcImpl extends
    BaseSvcImpl<java.lang.Long, RacPermMenuAddTo, RacPermMenuModifyTo, RacPermMenuDelTo, RacPermMenuOneTo, RacPermMenuListTo, RacPermMenuPageTo, RacPermMenuMo, RacPermMenuJo, RacPermMenuMapper, RacPermMenuDao>
    implements RacPermMenuSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private RacPermMenuSvc thisSvc;

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<RacPermMenuMo> getMoClass() {
        return RacPermMenuMo.class;
    }

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.Long, RacPermMenuAddTo, RacPermMenuModifyTo, RacPermMenuDelTo, RacPermMenuOneTo, RacPermMenuListTo, RacPermMenuPageTo, RacPermMenuMo, RacPermMenuJo> getThisSvc() {
        return thisSvc;
    }

    /**
     * 添加/修改权限菜单
     *
     * @param to 添加的具体信息
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void addPermMenuUrn(final RacPermMenusAddTo to) {
        // 先删除
        final RacPermMenuMo del = OrikaUtils.map(to, getMoClass());
        _mapper.deleteSelective(del);
        // 后添加
        final List<String> menuUrns = to.getMenuUrns();
        for (final String menuUrn : menuUrns) {
            final RacPermMenuMo mo = new RacPermMenuMo();
            mo.setSysId(to.getSysId());
            mo.setPermId(to.getPermId());
            mo.setMenuUrn(menuUrn);
            addMo(mo);
        }
    }

    /**
     * 查询权限菜单的信息
     *
     * @param qo 查询的具体条件
     */
    @Override
    public List<RacPermMenuMo> listPermMenu(final RacPermMenuListTo to) {
        final RacPermMenuMo       qo   = OrikaUtils.map(to, getMoClass());
        final List<RacPermMenuMo> list = _mapper.selectSelective(qo);
        return list;
    }

    /**
     * 获取账户的菜单列表
     *
     * @param accountId 账户ID
     * @param sysId     系统ID
     *
     * @return 指定账户的菜单列表
     */
    @Override
    public List<String> getMenusOfAccount(final Long accountId, final String sysId) {
        final List<RacPermMenuMo> list = _mapper
            .select(c -> c.join(racPerm).on(racPerm.id, equalTo(racPermMenu.permId)).join(racRolePerm).on(racRolePerm.permId, equalTo(racPerm.id)).join(racRole)
                .on(racRole.id, equalTo(racRolePerm.roleId)).join(racAccountRole).on(racAccountRole.roleId, equalTo(racRole.id)).where(racPermMenu.sysId, isEqualTo(sysId),
                    and(racAccountRole.accountId, isEqualTo(accountId)), and(racPerm.isEnabled, isTrue()), and(racRole.isEnabled, isTrue())));
        return list.stream().map(RacPermMenuMo::getMenuUrn).distinct().collect(Collectors.toList());
    }
}
