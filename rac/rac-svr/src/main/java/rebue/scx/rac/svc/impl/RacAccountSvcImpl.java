package rebue.scx.rac.svc.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.and;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isLikeWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.or;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.racAccount;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageInfo;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.rac.dao.RacAccountDao;
import rebue.scx.rac.jo.RacAccountJo;
import rebue.scx.rac.mapper.RacAccountMapper;
import rebue.scx.rac.mapper.RacOrgAccountMapper;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.RacLockLogMo;
import rebue.scx.rac.ra.GetCurAccountInfoRa;
import rebue.scx.rac.ra.ListTransferOfOrgRa;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.svc.RacLockLogSvc;
import rebue.scx.rac.svc.RacOrgSvc;
import rebue.scx.rac.svc.RacPermMenuSvc;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.RacAccountDelTo;
import rebue.scx.rac.to.RacAccountDisableTo;
import rebue.scx.rac.to.RacAccountEnableTo;
import rebue.scx.rac.to.RacAccountListTo;
import rebue.scx.rac.to.RacAccountModifySignInPswdTo;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.RacAccountOneTo;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.scx.rac.to.RacLockLogAddTo;
import rebue.scx.rac.to.RacOrgAccountAddTo;
import rebue.scx.rac.to.ex.RacAccountExMo;
import rebue.scx.rac.util.PswdUtils;
import rebue.wheel.core.NumberUtils;

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

	/**
	 * 本服务的单例 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Lazy
	@Resource
	private RacAccountSvc thisSvc;

	@Resource
	private RacPermMenuSvc permMenuSvc;

	@Resource
	private RacLockLogSvc lockLogSvc;

	@Resource
	private RacOrgAccountMapper orgAccountMapper;

	@Resource
	private RacOrgSvc racOrgSvc;

	/**
	 * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	protected Class<RacAccountMo> getMoClass() {
		return RacAccountMo.class;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RacAccountMo addMo(final RacAccountMo mo) {
		if (StringUtils.isNotBlank(mo.getSignInPswd())) {
			// 随机生成盐值
			mo.setSignInPswdSalt(PswdUtils.randomSalt());
			// 根据生成的盐值进行摘要
			mo.setSignInPswd(PswdUtils.saltPswd(mo.getSignInPswd(), mo.getSignInPswdSalt()));
		}
		final long now = System.currentTimeMillis();
		mo.setCreateTimestamp(now);
		mo.setUpdateTimestamp(now);
		RacAccountMo result = super.addMo(mo);
		// 判断是否设置了默认组织，有则添加组织关系
		if (result.getOrgId() != null) {
			List<Long> accountId = new ArrayList<Long>();
			accountId.add(result.getId());
			RacOrgAccountAddTo to = new RacOrgAccountAddTo();
			to.setOrgId(result.getOrgId());
			to.setAccountId(accountId);
			racOrgSvc.addOrgAccount(to);
		}

		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RacAccountMo modifyMoById(final RacAccountMo mo) {
		if (StringUtils.isNotBlank(mo.getSignInPswd())) {
			// 随机生成盐值
			mo.setSignInPswdSalt(PswdUtils.randomSalt());
			// 根据生成的盐值进行摘要
			mo.setSignInPswd(PswdUtils.saltPswd(mo.getSignInPswd(), mo.getSignInPswdSalt()));
		}
		mo.setUpdateTimestamp(System.currentTimeMillis());
		return super.modifyMoById(mo);
	}

	/**
	 * 修改账户登录密码
	 *
	 * @param to 修改账户登录密码的具体数据
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void modifySignInPswd(final RacAccountModifySignInPswdTo to) {
		final RacAccountMo mo = new RacAccountMo();
		mo.setId(to.getId());
		mo.setSignInPswd(to.getSignInPswd());
		thisSvc.modifyMoById(mo);
	}

	/**
	 * 查询账户的信息
	 *
	 * @param to 查询的具体条件
	 *
	 */
	@Override
	public Ro<ListTransferOfOrgRa> listTransferOfOrg(RacAccountPageTo to) {
		// 查找存在当前组织下的账户
		final RacAccountMo existQo = new RacAccountMo();
		existQo.setOrgId(to.getOrgId());
		List<RacAccountMo> existAccountList = _mapper.getExistAccountList(existQo);
		// 查询可添加的所有用户
		final RacAccountExMo addableQo = new RacAccountExMo();
		addableQo.setDomainId(to.getDomainId());
		addableQo.setOrgId(to.getOrgId());
		addableQo.setKeywords(to.getKeywords());
		final ISelect select = () -> _mapper.getAddablAccountList(addableQo);
		PageInfo<RacAccountMo> addableList = thisSvc.page(select, to.getPageNum(), to.getPageSize(), null);
		// 将所有记录添加到返回ListTransferOfOrgRa的对象中
		ListTransferOfOrgRa ro = new ListTransferOfOrgRa();
		ro.setAddableList(addableList);
		ro.setExistList(existAccountList);
		return new Ro<>(ResultDic.SUCCESS, "查询账户列表成功", ro);
	}

	/**
	 * 启用账户
	 *
	 * @param to 启用的具体数据
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void enable(final RacAccountEnableTo to) {
		final RacAccountMo mo = new RacAccountMo();
		mo.setId(to.getLockAccountId());
		mo.setIsEnabled(to.getIsEnabled());
		RacLockLogMo qo = new RacLockLogMo();
		if (to.getIsEnabled()) {
			qo.setDomainId(to.getDomainId());
			qo.setUnlockOpId(to.getUnlockOpId());
			qo.setUnlockDatetime(LocalDateTime.now());
			qo.setUnlockReason(to.getUnlockReason());
			qo.setLockAccountId(to.getLockAccountId());
			lockLogSvc.updateLockLog(qo);
			_mapper.updateByPrimaryKeySelective(mo);
		}
	}

	/**
	 * 禁用账户
	 *
	 * @param to 禁用的具体数据
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void disable(RacAccountDisableTo to) {
		final RacAccountMo mo = new RacAccountMo();
		mo.setId(to.getLockAccountId());
		mo.setIsEnabled(to.getIsEnabled());
		RacLockLogAddTo ato = new RacLockLogAddTo();
		if (!to.getIsEnabled()) {
			ato.setDomainId(to.getDomainId());
			ato.setLockOpId(to.getLockOpId());
			ato.setLockDatetime(LocalDateTime.now());
			ato.setLockReason(to.getLockReason());
			ato.setLockAccountId(to.getLockAccountId());
			lockLogSvc.add(ato);// 禁用时添加锁定日志
			_mapper.updateByPrimaryKeySelective(mo);
		}
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
		return _mapper.selectOne(
				c -> c.where(racAccount.domainId, isEqualTo(domainId), and(racAccount.signInEmail, isEqualTo(email))))
				.orElse(null);
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
		return _mapper.selectOne(
				c -> c.where(racAccount.domainId, isEqualTo(domainId), and(racAccount.signInMobile, isEqualTo(mobile))))
				.orElse(null);
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
		return _mapper.selectOne(c -> c.where(racAccount.domainId, isEqualTo(domainId),
				and(racAccount.signInName, isEqualTo(signInName)))).orElse(null);
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
		final RacAccountMo accountMo = thisSvc.getById(curAccountId);
		if (accountMo == null) {
			return new Ro<>(ResultDic.WARN, "查找不到当前账户");
		}
		final GetCurAccountInfoRa ra = new GetCurAccountInfoRa();
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
	public PageInfo<RacAccountMo> page(final RacAccountPageTo qo) {
		final String keywords = StringUtils.isBlank(qo.getKeywords()) ? null : "%" + qo.getKeywords() + "%";
		final ISelect select = () -> _mapper.select(c -> c.where(racAccount.domainId, isEqualTo(qo.getDomainId()),
				and(racAccount.signInNickname, isLikeWhenPresent(keywords),
						or(racAccount.signInName, isLikeWhenPresent(keywords)),
						or(racAccount.id,
								isEqualToWhenPresent(
										NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null)),
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

	/**
	 * 从接口获取本服务的单例(提供给基类调用)
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	protected BaseSvc<java.lang.Long, RacAccountAddTo, RacAccountModifyTo, RacAccountDelTo, RacAccountOneTo, RacAccountListTo, RacAccountPageTo, RacAccountMo, RacAccountJo> getThisSvc() {
		return thisSvc;
	}

}
