package rebue.scx.rac.svc.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageInfo;

import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.rac.dao.RacOrgDao;
import rebue.scx.rac.jo.RacOrgJo;
import rebue.scx.rac.mapper.RacAccountMapper;
import rebue.scx.rac.mapper.RacOrgAccountMapper;
import rebue.scx.rac.mapper.RacOrgMapper;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.RacOrgAccountMo;
import rebue.scx.rac.mo.RacOrgLeafMo;
import rebue.scx.rac.mo.RacOrgMo;
import rebue.scx.rac.mo.ex.RacOrgExMo;
import rebue.scx.rac.svc.RacOrgSvc;
import rebue.scx.rac.to.RacOrgAccountAddTo;
import rebue.scx.rac.to.RacOrgAccountDelTo;
import rebue.scx.rac.to.RacOrgAddTo;
import rebue.scx.rac.to.RacOrgDelTo;
import rebue.scx.rac.to.RacOrgListTo;
import rebue.scx.rac.to.RacOrgModifyTo;
import rebue.scx.rac.to.RacOrgOneTo;
import rebue.scx.rac.to.RacOrgPageTo;
import rebue.scx.rac.to.ex.RacModifyOrgAccountTo;
import rebue.scx.rac.to.ex.RacOrgListByAccountIdTo;
import rebue.scx.rac.to.ex.RacOrgModifyDefaultOrgTo;
import rebue.wheel.api.OrikaUtils;
import rebue.wheel.api.exception.RuntimeExceptionX;

/**
 * 组织服务实现
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
public class RacOrgSvcImpl
		extends BaseSvcImpl<java.lang.Long, RacOrgAddTo, RacOrgModifyTo, RacOrgDelTo, RacOrgOneTo, RacOrgListTo, RacOrgPageTo, RacOrgMo, RacOrgJo, RacOrgMapper, RacOrgDao>
		implements RacOrgSvc {

	/**
	 * 本服务的单例
	 * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Lazy
	@Resource
	private RacOrgSvc           thisSvc;

	@Resource
	private RacOrgAccountMapper orgAccountMapper;

	@Resource
	private RacAccountMapper    racAccountMapper;

	/**
	 * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	protected Class<RacOrgMo> getMoClass() {
		return RacOrgMo.class;
	}

	/**
	 * 从接口获取本服务的单例(提供给基类调用)
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	protected BaseSvc<java.lang.Long, RacOrgAddTo, RacOrgModifyTo, RacOrgDelTo, RacOrgOneTo, RacOrgListTo, RacOrgPageTo, RacOrgMo, RacOrgJo> getThisSvc() {
		return thisSvc;
	}

	/**
	 * 添加记录
	 *
	 * @param to 添加的参数
	 *
	 * @return 如果成功，且仅添加一条记录，返回添加时自动生成的ID，否则会抛出运行时异常
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RacOrgMo add(final RacOrgAddTo to) {
		final RacOrgMo mo = OrikaUtils.map(to, getMoClass());
		// mo中需要添加一个树编码
		if (to.getParentId() == null) {
			final RacOrgMo qo = new RacOrgMo();
			qo.setParentId(null);
			final Long   count    = _mapper.getCount(qo);
			final String treeCode = StringUtils.leftPad(count.toString(), 3, '0');
			mo.setTreeCode(treeCode);
		}
		if (to.getParentId() != null) {
			final RacOrgMo    orgMo     = thisSvc.getById(to.getParentId());
			final String      treeCode1 = orgMo.getTreeCode();
			final RacOrgOneTo qo        = new RacOrgOneTo();
			qo.setParentId(to.getParentId());
			final Long   count     = thisSvc.countSelective(qo);
			final String treeCode2 = StringUtils.leftPad(count.toString(), 3, '0');
			mo.setTreeCode(treeCode1 + treeCode2);
		}
		return thisSvc.addMo(mo);
	}

	/**
	 * 添加组织账户关系
	 *
	 * @param to 添加的具体信息
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addOrgAccount(final RacOrgAccountAddTo to) {
		final List<Long> lists = to.getAccountIds();
		for (final Long accountId : lists) {
			final RacOrgAccountMo mo = new RacOrgAccountMo();
			// 如果id为空那么自动生成分布式id
			mo.setId(_idWorker.getId());
			mo.setOrgId(to.getOrgId());
			mo.setAccountId(accountId);
			final int rowCount = orgAccountMapper.insertSelective(mo);
			if (rowCount != 1) {
				throw new RuntimeExceptionX("添加记录异常，影响行数为" + rowCount);
			}
			// 查询判断是否存在默认组织，没有则添加
			final RacAccountMo accountMo = racAccountMapper.selectByPrimaryKey(accountId).get();
			if (accountMo.getOrgId() == null) {
				accountMo.setOrgId(to.getOrgId());
				final int count = racAccountMapper.updateByPrimaryKey(accountMo);
				if (count != 1) {
					throw new RuntimeExceptionX("添加默认组织关系记录异常，影响行数为" + rowCount);
				}
			}
		}
	}

	/**
	 * 修改账户默认组织的信息
	 *
	 * @param to 修改的具体数据
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void modifyDefaultOrg(final RacOrgModifyDefaultOrgTo to) {
		// 获得当前账户的默认orgId
		final RacAccountMo racAccountMo = racAccountMapper.selectByPrimaryKey(to.getAccountId()).get();
		final RacAccountMo accountMo    = OrikaUtils.map(racAccountMo, RacAccountMo.class);
		// 修改账户的默认orgId
		accountMo.setOrgId(to.getOrgId());
		racAccountMapper.updateByPrimaryKeySelective(accountMo);
	}

	/**
	 * 更改组织与账户的关系
	 *
	 * @param to 修改的具体数据
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void modifyOrgAccount(final RacModifyOrgAccountTo to) {
		// 获得当前账户的默认orgId
		final RacAccountMo racAccountMo = racAccountMapper.selectByPrimaryKey(to.getAccountId()).get();
		if (racAccountMo.getOrgId().equals(to.getModifyOrgId())) {
			// 更改默认组织
			final RacAccountMo accountMo = OrikaUtils.map(racAccountMo, RacAccountMo.class);
			// 修改账户的默认orgId
			accountMo.setOrgId(to.getOrgId());
			racAccountMapper.updateByPrimaryKeySelective(accountMo);
			// 删除原来的组织关系
			final RacOrgAccountDelTo delTo         = new RacOrgAccountDelTo();
			final List<Long>         delAccountIds = new ArrayList<Long>();
			delAccountIds.add(to.getAccountId());
			delTo.setOrgId(to.getModifyOrgId());
			delTo.setAccountIds(delAccountIds);
			thisSvc.delOrgAccount(delTo);
			// 添加新的组织账户关系
			final RacOrgAccountAddTo addTo         = new RacOrgAccountAddTo();
			final List<Long>         addAccountIds = new ArrayList<Long>();
			addAccountIds.add(to.getAccountId());
			addTo.setOrgId(to.getOrgId());
			addTo.setAccountIds(addAccountIds);
			thisSvc.addOrgAccount(addTo);
		}
		else {
			// 更改非默认组织
			// 删除原来的组织关系
			final RacOrgAccountDelTo delTo         = new RacOrgAccountDelTo();
			final List<Long>         delAccountIds = new ArrayList<Long>();
			delAccountIds.add(to.getAccountId());
			delTo.setOrgId(to.getModifyOrgId());
			delTo.setAccountIds(delAccountIds);
			thisSvc.delOrgAccount(delTo);
			// 添加新的组织账户关系
			final RacOrgAccountAddTo addTo         = new RacOrgAccountAddTo();
			final List<Long>         addAccountIds = new ArrayList<Long>();
			addAccountIds.add(to.getAccountId());
			addTo.setOrgId(to.getOrgId());
			addTo.setAccountIds(addAccountIds);
			thisSvc.addOrgAccount(addTo);
		}
	}

	/**
	 * 删除组织账户关系
	 *
	 * @param to 删除的具体信息
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delOrgAccount(final RacOrgAccountDelTo to) {
		final List<Long> accountIds = to.getAccountIds();
		for (final Long accountId : accountIds) {
			final RacOrgAccountMo mo = new RacOrgAccountMo();
			mo.setAccountId(accountId);
			mo.setOrgId(to.getOrgId());
			orgAccountMapper.deleteSelective(mo);
		}
	}

	/**
	 * 查询可以添加的组织信息
	 *
	 * @param qo 查询的具体条件
	 */
	@Override
	public PageInfo<RacOrgMo> listAddableOrg(final RacOrgListByAccountIdTo qo) {
		return null;
		// final ISelect select = () -> _mapper.listAddableOrg(qo);
		// return getThisSvc().page(select, qo.getPageNum(), qo.getPageSize(), null);
	}

	/**
	 * 分页查询列表
	 *
	 * @param qo 查询条件
	 *
	 * @return 查询到的分页信息
	 */
	@Override
	public PageInfo<RacOrgMo> page(final RacOrgPageTo qo) {
		final RacOrgExMo         mo     = OrikaUtils.map(qo, RacOrgExMo.class);
		final ISelect            select = () -> _mapper.selectByDomainId(mo);
		final PageInfo<RacOrgMo> orgMo  = getThisSvc().page(select, qo.getPageNum(), qo.getPageSize(), qo.getOrderBy());
		final List<RacOrgMo>     list   = getSetLeafList(orgMo.getList());
		orgMo.setList(list);
		return orgMo;
	}

	/**
	 * 查询组织的信息
	 *
	 * @param qo 查询的具体条件
	 */
	@Override
	public List<RacOrgMo> list(final RacOrgListTo qo) {
		final List<RacOrgMo> list = super.list(qo);
		return getSetLeafList(list);
	}

	/**
	 * 共用的遍历是否设置Leaf字段
	 *
	 * @param list
	 *
	 * @return
	 */
	List<RacOrgMo> getSetLeafList(final List<RacOrgMo> list) {
		return list.stream().map(item -> {
			final RacOrgLeafMo racOrgLeafMo = OrikaUtils.map(item, RacOrgLeafMo.class);
			final RacOrgMo     existQo      = new RacOrgMo();
			existQo.setParentId(racOrgLeafMo.getId());
			racOrgLeafMo.setIsLeaf(!_mapper.existSelective(existQo));
			return racOrgLeafMo;
		}).collect(Collectors.toList());
	}

	/**
	 * 获取根组织以及根组织下的在所有组织
	 */
	@Override
	public PageInfo<RacOrgMo> pageIsTable(final RacOrgPageTo qo) {
		final RacOrgExMo         mo      = OrikaUtils.map(qo, RacOrgExMo.class);
		final ISelect            select  = () -> _mapper.selectByDomainId(mo);
		final PageInfo<RacOrgMo> orgMo   = getThisSvc().page(select, qo.getPageNum(), qo.getPageSize(), qo.getOrderBy());
		final List<RacOrgMo>     listAll = new ArrayList<RacOrgMo>();
		orgMo.getList().stream().map(item -> {
			final RacOrgLeafMo racOrgLeafMo = OrikaUtils.map(item, RacOrgLeafMo.class);
			final RacOrgMo     listQo       = new RacOrgMo();
			listQo.setTreeCode(racOrgLeafMo.getTreeCode());
			final List<RacOrgMo> listPageOrgLikeTreeCode = _mapper.listPageOrgLikeTreeCode(listQo);
			listAll.addAll(listPageOrgLikeTreeCode);
			return racOrgLeafMo;
		}).collect(Collectors.toList());
		orgMo.setList(listAll);
		return orgMo;
	}

	/**
	 * 查询当前账户所在的组织的信息
	 *
	 * @param qo 查询的具体条件
	 */
	@Override
	public List<RacOrgMo> listByAccountId(final RacOrgListByAccountIdTo qo) {
		return _mapper.listByAccountId(qo);
	}

	/**
	 * 查询组织的信息
	 *
	 * @param qo 查询的具体条件
	 */
	@Override
	public List<RacOrgMo> selectInAndByKeywordsList(final List<Long> ids, final RacOrgMo qo) {
		return _mapper.selectInAndByKeywordsList(ids, qo);
	}
}
