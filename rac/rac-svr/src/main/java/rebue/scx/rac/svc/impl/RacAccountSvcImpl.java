package rebue.scx.rac.svc.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.rac.dao.RacAccountDao;
import rebue.scx.rac.jo.RacAccountJo;
import rebue.scx.rac.mapper.RacAccountMapper;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.GetCurAccountInfoRa;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.svc.RacPermMenuSvc;
import rebue.scx.rac.to.*;
import rebue.scx.rac.util.PswdUtils;
import rebue.wheel.NumberUtils;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.racAccount;

/**
 * 账户服务实现
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
public class RacAccountSvcImpl extends
        BaseSvcImpl<java.lang.Long, RacAccountAddTo, RacAccountModifyTo, RacAccountDelTo, RacAccountOneTo, RacAccountListTo, RacAccountPageTo, RacAccountMo, RacAccountJo, RacAccountMapper, RacAccountDao>
        implements RacAccountSvc {

    @Resource
    private RacPermMenuSvc permMenuSvc;

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private RacAccountSvc  thisSvc;

    /**
     * 泛型MO的class(应为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<RacAccountMo> getMoClass() {
        return RacAccountMo.class;
    }

    @Override
    public Long addMo(@Valid final RacAccountMo mo) {
        if (StringUtils.isNotBlank(mo.getSignInPswd())) {
            mo.setSignInPswdSalt(PswdUtils.randomSalt());
            mo.setSignInPswd(PswdUtils.saltPswd(mo.getSignInPswd(), mo.getSignInPswdSalt()));
        }

        final long now = System.currentTimeMillis();
        mo.setCreateTimestamp(now);
        mo.setUpdateTimestamp(now);
        return super.addMo(mo);
    }

    @Override
    public void modifyMoById(@Valid final RacAccountMo mo) {
        mo.setUpdateTimestamp(System.currentTimeMillis());
        super.modifyMoById(mo);
    }

    /**
     * 通过email获取账户信息
     *
     * @param domainId 领域ID
     * @param email    电子邮箱
     *
     * @return 账户信息
     */
    @Override
    public RacAccountMo getOneByEmail(final String domainId, final String email) {
        return _mapper.selectOne(c -> c.where(racAccount.domainId, isEqualTo(domainId), and(racAccount.signInEmail, isEqualTo(email)))).orElse(null);
    }

    /**
     * 通过手机号获取账户信息
     *
     * @param domainId 领域ID
     * @param mobile   手机号
     *
     * @return 账户信息
     */
    @Override
    public RacAccountMo getOneByMobile(final String domainId, final String mobile) {
        return _mapper.selectOne(c -> c.where(racAccount.domainId, isEqualTo(domainId), and(racAccount.signInMobile, isEqualTo(mobile)))).orElse(null);
    }

    /**
     * 通过登录名称获取账户信息
     *
     * @param domainId   领域ID
     * @param signInName 登录名称
     *
     * @return 账户信息
     */
    @Override
    public RacAccountMo getOneBySignInName(final String domainId, final String signInName) {
        return _mapper.selectOne(c -> c.where(racAccount.domainId, isEqualTo(domainId), and(racAccount.signInName, isEqualTo(signInName)))).orElse(null);
    }

    /**
     * 获取当前账户信息
     *
     * @param curAccountId 当前账户ID
     * @param sysId        系统ID
     *
     * @return 当前账户信息
     */
    @Override
    public Ro<GetCurAccountInfoRa> getCurAccountInfo(final Long curAccountId, final String sysId) {
        final RacAccountMo        accountMo = thisSvc.getById(curAccountId);
        final GetCurAccountInfoRa ra        = new GetCurAccountInfoRa();
        _dozerMapper.map(accountMo, ra);
        ra.setNickname(accountMo.getSignInNickname());
        ra.setAvatar(accountMo.getSignInAvatar());
        ra.setMenus(permMenuSvc.getMenusOfAccount(curAccountId, sysId));
        return new Ro<>(ResultDic.SUCCESS, "获取当前账户信息成功", ra);
    }

    /**
     * 分页查询列表
     *
     * @param qo 查询条件
     *
     * @return 查询到的分页信息
     */
    @Override
    public PageInfo<RacAccountMo> page(RacAccountPageTo qo) {
        String        keywords = StringUtils.isBlank(qo.getKeywords()) ? null : "%" + qo.getKeywords() + "%";
        final ISelect select   = () -> _mapper.select(c -> c
                .where(
                        racAccount.domainId, isEqualTo(qo.getDomainId()),
                        and(racAccount.signInNickname, isLikeWhenPresent(keywords),
                                or(racAccount.signInName, isLikeWhenPresent(keywords)),
                                or(racAccount.id, isEqualToWhenPresent(NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null)),
                                or(racAccount.signInEmail, isLikeWhenPresent(keywords)),
                                or(racAccount.signInMobile, isLikeWhenPresent(keywords)),
                                or(racAccount.qqNickname, isLikeWhenPresent(keywords)),
                                or(racAccount.qqOpenId, isLikeWhenPresent(keywords)),
                                or(racAccount.qqUnionId, isLikeWhenPresent(keywords)),
                                or(racAccount.wxNickname, isLikeWhenPresent(keywords)),
                                or(racAccount.wxOpenId, isLikeWhenPresent(keywords)),
                                or(racAccount.wxUnionId, isLikeWhenPresent(keywords)),
                                or(racAccount.remark, isLikeWhenPresent(keywords)))));

        return super.page(select, qo.getPageNum(), qo.getPageSize(), qo.getOrderBy());
    }
}
