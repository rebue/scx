package rebue.scx.rac.svc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.rac.dao.RacRoleDao;
import rebue.scx.rac.jo.RacRoleJo;
import rebue.scx.rac.mapper.RacRoleMapper;
import rebue.scx.rac.mo.RacRoleMo;
import rebue.scx.rac.svc.RacRoleSvc;
import rebue.scx.rac.to.RacRoleAddTo;
import rebue.scx.rac.to.RacRoleDelTo;
import rebue.scx.rac.to.RacRoleListTo;
import rebue.scx.rac.to.RacRoleModifyTo;
import rebue.scx.rac.to.RacRoleOneTo;
import rebue.scx.rac.to.RacRolePageTo;
import rebue.wheel.core.exception.RuntimeExceptionX;

/**
 * 角色服务实现
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
public class RacRoleSvcImpl extends
		BaseSvcImpl<java.lang.Long, RacRoleAddTo, RacRoleModifyTo, RacRoleDelTo, RacRoleOneTo, RacRoleListTo, RacRolePageTo, RacRoleMo, RacRoleJo, RacRoleMapper, RacRoleDao>
		implements RacRoleSvc {

	/**
	 * 本服务的单例 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Lazy
	@Resource
	private RacRoleSvc thisSvc;

	/**
	 * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	protected Class<RacRoleMo> getMoClass() {
		return RacRoleMo.class;
	}

	/**
	 * 从接口获取本服务的单例(提供给基类调用)
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	protected BaseSvc<java.lang.Long, RacRoleAddTo, RacRoleModifyTo, RacRoleDelTo, RacRoleOneTo, RacRoleListTo, RacRolePageTo, RacRoleMo, RacRoleJo> getThisSvc() {
		return thisSvc;
	}

	/**
	 * 添加角色
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RacRoleMo add(RacRoleAddTo to) {
		final RacRoleMo mo = _dozerMapper.map(to, getMoClass());
		RacRoleOneTo qo = new RacRoleOneTo();
		qo.setDomainId(to.getDomainId());
		Long count = getThisSvc().countSelective(qo);
		mo.setSeqNo((byte) (count + 0));// 最初添加的角色顺序从0开始,新添加的角色顺序为最大
		return getThisSvc().addMo(mo);
	}

	/**
	 * 上移动
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void moveUp(RacRoleModifyTo to) {
		// 获取当前这条数据的具体数据
		RacRoleMo qo = _mapper.selectByPrimaryKey(to.getId())
				.orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
		// 获取修改当前这条数据上面一条的数据的顺序号
		RacRoleMo roleQo = new RacRoleMo();
		roleQo.setSeqNo((byte) (qo.getSeqNo() - 1));
		roleQo.setDomainId(to.getDomainId());
		RacRoleMo roleup = _mapper.selectOne(roleQo).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
		// 修改当前这条数据上面一条的数据的顺序号
		roleup.setSeqNo((byte) (roleup.getSeqNo() + 1));
		final RacRoleMo mo = _dozerMapper.map(roleup, getMoClass());
		getThisSvc().modifyMoById(mo);
		// 修改当前这条数据的顺序号
		to.setSeqNo((byte) (qo.getSeqNo() - 1));
		getThisSvc().modifyById(to);
	}

	/**
	 * 下移动
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void moveDown(RacRoleModifyTo to) {
		// 获取当前这条数据的具体数据
		RacRoleMo qo = _mapper.selectByPrimaryKey(to.getId())
				.orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
		// 获取当前这条数据下面一条的具体数据
		RacRoleMo roleQo = new RacRoleMo();
		roleQo.setSeqNo((byte) (qo.getSeqNo() + 1));
		roleQo.setDomainId(to.getDomainId());
		RacRoleMo roleDown = _mapper.selectOne(roleQo).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
		// 修改当前这条数据下面一条的数据的顺序号
		roleDown.setSeqNo((byte) (roleDown.getSeqNo() - 1));
		final RacRoleMo mo = _dozerMapper.map(roleDown, getMoClass());
		getThisSvc().modifyMoById(mo);
		// 修改当前这条数据的顺序号
		to.setSeqNo((byte) (qo.getSeqNo() + 1));
		getThisSvc().modifyById(to);
	}

	/**
	 * 启用角色
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void enable(RacRoleModifyTo to) {
		final RacRoleMo mo = _dozerMapper.map(to, getMoClass());
		_mapper.updateByPrimaryKeySelective(mo);
	}

	/**
	 * 禁用角色
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void disable(RacRoleModifyTo to) {
		final RacRoleMo mo = _dozerMapper.map(to, getMoClass());
		_mapper.updateByPrimaryKeySelective(mo);
	}

	/**
	 * 删除角色
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delById(Long id) {
		final RacRoleMo qo = this.getById(id);
		final int rowCount = _mapper.deleteByPrimaryKey(id);
		if (rowCount == 0) {
			throw new RuntimeExceptionX("删除记录异常，记录已不存在或有变动");
		}
		if (rowCount != 1) {
			throw new RuntimeExceptionX("删除记录异常，影响行数为" + rowCount);
		}
		// 删除后对其余角色进行顺序号更新
		_mapper.updateSeqNoByDeleteAfter(qo);
	}

	/**
	 * 查询角色
	 */
	@Override
	public List<RacRoleMo> list(RacRoleListTo qo) {
		final RacRoleMo mo = _dozerMapper.map(qo, getMoClass());
		return _mapper.selectListOrderByRole(mo);
	}

}
