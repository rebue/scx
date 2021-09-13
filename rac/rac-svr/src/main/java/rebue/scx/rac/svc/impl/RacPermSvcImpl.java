package rebue.scx.rac.svc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.rac.dao.RacPermDao;
import rebue.scx.rac.jo.RacPermJo;
import rebue.scx.rac.mapper.RacPermMapper;
import rebue.scx.rac.mo.RacPermGroupMo;
import rebue.scx.rac.mo.RacPermMo;
import rebue.scx.rac.ra.PermListWithGroupRa;
import rebue.scx.rac.svc.RacPermGroupSvc;
import rebue.scx.rac.svc.RacPermSvc;
import rebue.scx.rac.to.RacPermAddTo;
import rebue.scx.rac.to.RacPermDelTo;
import rebue.scx.rac.to.RacPermGroupListTo;
import rebue.scx.rac.to.RacPermGroupModifyTo;
import rebue.scx.rac.to.RacPermListTo;
import rebue.scx.rac.to.RacPermModifyTo;
import rebue.scx.rac.to.RacPermOneTo;
import rebue.scx.rac.to.RacPermPageTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.util.OrikaUtils;

/**
 * 权限服务实现
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
public class RacPermSvcImpl
    extends BaseSvcImpl<java.lang.Long, RacPermAddTo, RacPermModifyTo, RacPermDelTo, RacPermOneTo, RacPermListTo, RacPermPageTo, RacPermMo, RacPermJo, RacPermMapper, RacPermDao>
    implements RacPermSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private RacPermSvc      thisSvc;

    @Lazy
    @Resource
    private RacPermGroupSvc permGroupSvc;

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<RacPermMo> getMoClass() {
        return RacPermMo.class;
    }

    /**
     * 查询带分组的权限列表
     *
     * @param realmId 领域ID
     */
    @Override
    public Ro<PermListWithGroupRa> listWithGroup(final String realmId) {
        final PermListWithGroupRa ra = new PermListWithGroupRa();
        final RacPermListTo permQo = new RacPermListTo();
        permQo.setRealmId(realmId);
        ra.setPermList(thisSvc.list(permQo));
        final RacPermGroupListTo permGroupQo = new RacPermGroupListTo();
        permGroupQo.setRealmId(realmId);
        ra.setGroupList(permGroupSvc.list(permGroupQo));
        return new Ro<>(ResultDic.SUCCESS, "查询带分组的权限列表成功", ra);
    }

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.Long, RacPermAddTo, RacPermModifyTo, RacPermDelTo, RacPermOneTo, RacPermListTo, RacPermPageTo, RacPermMo, RacPermJo> getThisSvc() {
        return thisSvc;
    }

    /**
     * 添加权限
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public RacPermMo add(final RacPermAddTo to) {
        final RacPermMo mo = OrikaUtils.map(to, getMoClass());
        final RacPermOneTo qo = new RacPermOneTo();
        qo.setRealmId(to.getRealmId());
        qo.setGroupId(to.getGroupId());
        // 查询根据当前权限分组的使用状态设置权限的状态
        final RacPermGroupMo groupMo = permGroupSvc.getById(to.getGroupId());
        mo.setIsEnabled(groupMo.getIsEnabled());
        final Long count = getThisSvc().countSelective(qo);
        // 最初添加的权限顺序从0开始,新添加的权限顺序为最大
        mo.setSeqNo((byte) (count + 0));
        return getThisSvc().addMo(mo);
    }

    /**
     * 删除
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delById(final Long id) {
        final RacPermMo qo = getById(id);
        final int rowCount = _mapper.deleteByPrimaryKey(id);
        if (rowCount == 0) {
            throw new RuntimeExceptionX("删除记录异常，记录已不存在或有变动");
        }
        if (rowCount != 1) {
            throw new RuntimeExceptionX("删除记录异常，影响行数为" + rowCount);
        }
        // 删除后对其余权限进行顺序号更新
        _mapper.updateSeqNoByDeleteAfter(qo);
    }

    /**
     * 上移动
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void moveUp(final RacPermModifyTo to) {
        // 获取当前这条数据的具体数据
        final RacPermMo qo = _mapper.selectByPrimaryKey(to.getId()).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        // 获取当前这条数据上面一条的数据的顺序号
        final RacPermMo rermQo = new RacPermMo();
        rermQo.setSeqNo((byte) (qo.getSeqNo() - 1));
        rermQo.setRealmId(qo.getRealmId());
        rermQo.setGroupId(qo.getGroupId());
        final RacPermMo permUp = _mapper.selectOne(rermQo).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        // 修改当前这条数据上面一条的数据的顺序号
        permUp.setSeqNo((byte) (permUp.getSeqNo() + 1));
        final RacPermMo mo = OrikaUtils.map(permUp, getMoClass());
        getThisSvc().modifyMoById(mo);
        // 修改当前这条数据的顺序号
        final RacPermMo qoMo = OrikaUtils.map(qo, getMoClass());
        qoMo.setSeqNo((byte) (qo.getSeqNo() - 1));
        getThisSvc().modifyMoById(qoMo);
    }

    /**
     * 下移动
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void moveDown(final RacPermModifyTo to) {
        // 获取当前这条数据的具体数据
        final RacPermMo qo = _mapper.selectByPrimaryKey(to.getId()).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        // 获取当前这条数据下面一条的具体数据
        final RacPermMo permQo = new RacPermMo();
        permQo.setSeqNo((byte) (qo.getSeqNo() + 1));
        permQo.setRealmId(qo.getRealmId());
        permQo.setGroupId(qo.getGroupId());
        final RacPermMo permDown = _mapper.selectOne(permQo).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        // 修改当前这条数据下面一条的数据的顺序号
        permDown.setSeqNo((byte) (permDown.getSeqNo() - 1));
        final RacPermMo mo = OrikaUtils.map(permDown, getMoClass());
        getThisSvc().modifyMoById(mo);
        // 修改当前这条数据的顺序号
        final RacPermMo qoMo = OrikaUtils.map(qo, getMoClass());
        qoMo.setSeqNo((byte) (qo.getSeqNo() + 1));
        getThisSvc().modifyMoById(qoMo);
    }

    /**
     * 启用权限
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void enable(final RacPermModifyTo to) {
        final RacPermMo mo = OrikaUtils.map(to, getMoClass());
        _mapper.updateByPrimaryKeySelective(mo);
        // 进行判断联动启用
        final RacPermMo qo = _mapper.selectOne(mo).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        final RacPermOneTo permTo = new RacPermOneTo();
        permTo.setGroupId(qo.getGroupId());
        permTo.setIsEnabled(to.getIsEnabled());
        final Long count = countSelective(permTo);
        if (count > 0) {
            final RacPermGroupModifyTo permGroupModifyTo = new RacPermGroupModifyTo();
            permGroupModifyTo.setId(qo.getGroupId());
            permGroupModifyTo.setIsEnabled(qo.getIsEnabled());
            permGroupSvc.enable(permGroupModifyTo);
        }
    }

    /**
     * 禁用权限
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void disable(final RacPermModifyTo to) {
        final RacPermMo mo = OrikaUtils.map(to, getMoClass());
        _mapper.updateByPrimaryKeySelective(mo);
        // 进行判断联动禁用
        final RacPermMo qo = _mapper.selectOne(mo).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        final RacPermOneTo permTo = new RacPermOneTo();
        permTo.setGroupId(qo.getGroupId());
        permTo.setIsEnabled(!to.getIsEnabled());
        final Long count = countSelective(permTo);
        if (count == 0) {
            final RacPermGroupModifyTo permGroupModifyTo = new RacPermGroupModifyTo();
            permGroupModifyTo.setId(qo.getGroupId());
            permGroupModifyTo.setIsEnabled(qo.getIsEnabled());
            permGroupSvc.disable(permGroupModifyTo);
        }
    }

    /**
     * 查询权限
     */
    @Override
    public List<RacPermMo> list(final RacPermListTo qo) {
        final RacPermMo mo = OrikaUtils.map(qo, getMoClass());
        return _mapper.selectOrderByPerm(mo);
    }

    /**
     * 根据groupId 修改是否启用/禁用权限
     *
     * @param qo
     *
     * @return
     */
    @Override
    public int updateByGroupId(final RacPermMo qo) {
        return _mapper.updateByGroupId(qo);
    }
}
