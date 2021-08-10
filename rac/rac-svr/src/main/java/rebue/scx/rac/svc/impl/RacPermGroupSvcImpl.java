package rebue.scx.rac.svc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.rac.dao.RacPermGroupDao;
import rebue.scx.rac.jo.RacPermGroupJo;
import rebue.scx.rac.mapper.RacPermGroupMapper;
import rebue.scx.rac.mo.RacPermGroupMo;
import rebue.scx.rac.mo.RacPermMo;
import rebue.scx.rac.svc.RacPermGroupSvc;
import rebue.scx.rac.svc.RacPermSvc;
import rebue.scx.rac.to.RacPermGroupAddTo;
import rebue.scx.rac.to.RacPermGroupDelTo;
import rebue.scx.rac.to.RacPermGroupListTo;
import rebue.scx.rac.to.RacPermGroupModifyTo;
import rebue.scx.rac.to.RacPermGroupOneTo;
import rebue.scx.rac.to.RacPermGroupPageTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.util.OrikaUtils;

/**
 * 权限分组服务实现
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
public class RacPermGroupSvcImpl extends
    BaseSvcImpl<java.lang.Long, RacPermGroupAddTo, RacPermGroupModifyTo, RacPermGroupDelTo, RacPermGroupOneTo, RacPermGroupListTo, RacPermGroupPageTo, RacPermGroupMo, RacPermGroupJo, RacPermGroupMapper, RacPermGroupDao>
    implements RacPermGroupSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private RacPermGroupSvc thisSvc;

    @Resource
    private RacPermSvc      racPermSvc;

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<RacPermGroupMo> getMoClass() {
        return RacPermGroupMo.class;
    }

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.Long, RacPermGroupAddTo, RacPermGroupModifyTo, RacPermGroupDelTo, RacPermGroupOneTo, RacPermGroupListTo, RacPermGroupPageTo, RacPermGroupMo, RacPermGroupJo> getThisSvc() {
        return thisSvc;
    }

    /**
     * 添加权限分组
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public RacPermGroupMo add(final RacPermGroupAddTo to) {
        final RacPermGroupMo mo = OrikaUtils.map(to, getMoClass());
        final RacPermGroupOneTo qo = new RacPermGroupOneTo();
        qo.setRealmId(to.getRealmId());
        final Long count = getThisSvc().countSelective(qo);
        // 最初添加的权限分组顺序从0开始,新添加的权限分组顺序为最大
        mo.setSeqNo((byte) (count + 0));
        return getThisSvc().addMo(mo);
    }

    /**
     * 删除权限分组
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delById(final Long id) {
        final RacPermGroupMo qo = getById(id);
        final int rowCount = _mapper.deleteByPrimaryKey(id);
        if (rowCount == 0) {
            throw new RuntimeExceptionX("删除记录异常，记录已不存在或有变动");
        }
        if (rowCount != 1) {
            throw new RuntimeExceptionX("删除记录异常，影响行数为" + rowCount);
        }
        // 删除后对其余权限分组进行顺序号更新
        _mapper.updateSeqNoByDeleteAfter(qo);
    }

    /**
     * 上移动
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void moveUp(final RacPermGroupModifyTo to) {
        // 获取当前这条数据的具体数据
        final RacPermGroupMo qo = _mapper.selectByPrimaryKey(to.getId()).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        final RacPermGroupMo permGroupQo = new RacPermGroupMo();
        permGroupQo.setSeqNo((byte) (qo.getSeqNo() - 1));
        permGroupQo.setRealmId(qo.getRealmId());
        final RacPermGroupMo permGroupUp = _mapper.selectOne(permGroupQo).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        // 修改当前这条数据上面一条的数据的顺序号
        permGroupUp.setSeqNo((byte) (permGroupUp.getSeqNo() + 1));
        final RacPermGroupMo mo = OrikaUtils.map(permGroupUp, getMoClass());
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
    public void moveDown(final RacPermGroupModifyTo to) {
        // 获取当前这条数据的具体数据
        final RacPermGroupMo qo = _mapper.selectByPrimaryKey(to.getId()).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        // 获取当前这条数据下面一条的具体数据
        final RacPermGroupMo permGroupQo = new RacPermGroupMo();
        permGroupQo.setSeqNo((byte) (qo.getSeqNo() + 1));
        permGroupQo.setRealmId(qo.getRealmId());
        final RacPermGroupMo permGroupDown = _mapper.selectOne(permGroupQo).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        // 修改当前这条数据下面一条的数据的顺序号
        permGroupDown.setSeqNo((byte) (permGroupDown.getSeqNo() - 1));
        final RacPermGroupMo mo = OrikaUtils.map(permGroupDown, getMoClass());
        getThisSvc().modifyMoById(mo);
        // 修改当前这条数据的顺序号
        to.setSeqNo((byte) (qo.getSeqNo() + 1));
        getThisSvc().modifyById(to);
    }

    /**
     * 启用权限分组
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void enable(final RacPermGroupModifyTo to) {
        final RacPermGroupMo mo = OrikaUtils.map(to, getMoClass());
        _mapper.updateByPrimaryKeySelective(mo);
    }

    /**
     * 启动权限分组联动子节点
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void enableLinkage(final RacPermGroupModifyTo to) {
        final RacPermGroupMo mo = OrikaUtils.map(to, getMoClass());
        _mapper.updateByPrimaryKeySelective(mo);
        // 禁用字节点权限
        final RacPermMo permTo = new RacPermMo();
        permTo.setGroupId(to.getId());
        permTo.setIsEnabled(to.getIsEnabled());
        racPermSvc.updateByGroupId(permTo);
    }

    /**
     * 禁用权限分组
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void disable(final RacPermGroupModifyTo to) {
        final RacPermGroupMo mo = OrikaUtils.map(to, getMoClass());
        _mapper.updateByPrimaryKeySelective(mo);
    }

    /**
     * 禁用权限分组联动子节点
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void disableLinkage(final RacPermGroupModifyTo to) {
        thisSvc.disable(to);
        // 禁用字节点权限
        final RacPermMo permTo = new RacPermMo();
        permTo.setGroupId(to.getId());
        permTo.setIsEnabled(to.getIsEnabled());
        racPermSvc.updateByGroupId(permTo);
    }

    /**
     * 查询权限分组
     */
    @Override
    public List<RacPermGroupMo> list(final RacPermGroupListTo qo) {
        final RacPermGroupMo mo = OrikaUtils.map(qo, getMoClass());
        return _mapper.selectListOrderByPermGroup(mo);
    }
}
