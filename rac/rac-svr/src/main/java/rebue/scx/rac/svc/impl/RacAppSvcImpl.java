package rebue.scx.rac.svc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.rac.dao.RacAppDao;
import rebue.scx.rac.jo.RacAppJo;
import rebue.scx.rac.mapper.RacAppMapper;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.svc.RacAppSvc;
import rebue.scx.rac.to.RacAppAddTo;
import rebue.scx.rac.to.RacAppDelTo;
import rebue.scx.rac.to.RacAppEnabledTo;
import rebue.scx.rac.to.RacAppListTo;
import rebue.scx.rac.to.RacAppModifyTo;
import rebue.scx.rac.to.RacAppOneTo;
import rebue.scx.rac.to.RacAppPageTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.util.OrikaUtils;

/**
 * 应用服务实现
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
public class RacAppSvcImpl
        extends BaseSvcImpl<java.lang.String, RacAppAddTo, RacAppModifyTo, RacAppDelTo, RacAppOneTo, RacAppListTo, RacAppPageTo, RacAppMo, RacAppJo, RacAppMapper, RacAppDao>
        implements RacAppSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private RacAppSvc thisSvc;

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<RacAppMo> getMoClass() {
        return RacAppMo.class;
    }

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.String, RacAppAddTo, RacAppModifyTo, RacAppDelTo, RacAppOneTo, RacAppListTo, RacAppPageTo, RacAppMo, RacAppJo> getThisSvc() {
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
    public RacAppMo add(RacAppAddTo to) {
        RacAppOneTo oneTo = new RacAppOneTo();
        oneTo.setRealmId(to.getRealmId());
        // 排序顺序号
        Long count = thisSvc.countSelective(oneTo);
        to.setSeqNo((byte) (count + 0));
        final RacAppMo mo = OrikaUtils.map(to, RacAppMo.class);
        return thisSvc.addMo(mo);
    }

    /**
     * 是否启用应用
     *
     * @param to 修改的具体数据
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void enable(RacAppEnabledTo to) {
        final RacAppMo mo = OrikaUtils.map(to, RacAppMo.class);
        thisSvc.modifyMoById(mo);
    }

    /**
     * 通过ID删除记录
     *
     * @param id 要删除记录的ID
     *
     * @return 如果成功，且删除一条记录，正常返回，否则会抛出运行时异常
     */
    @Override
    public void delById(String id) {
        RacAppMo  byId     = getById(id);
        final int rowCount = _mapper.deleteByPrimaryKey(id);
        if (rowCount == 0) {
            throw new RuntimeExceptionX("删除记录异常，记录已不存在或有变动");
        }
        if (rowCount != 1) {
            throw new RuntimeExceptionX("删除记录异常，影响行数为" + rowCount);
        }
        // 删除后对其余应用进行顺序号更新
        _mapper.updateSeqNoByDeleteAfter(byId);
    }

    /**
     * 上移动
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void moveUp(final RacAppModifyTo to) {
        // 获取当前这条数据的具体数据
        final RacAppMo qo    = _mapper.selectByPrimaryKey(to.getId()).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        final RacAppMo appQo = new RacAppMo();
        appQo.setSeqNo((byte) (qo.getSeqNo() - 1));
        appQo.setRealmId(qo.getRealmId());
        final RacAppMo appUp = _mapper.selectOne(appQo).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        // 修改当前这条数据上面一条的数据的顺序号
        appUp.setSeqNo((byte) (appUp.getSeqNo() + 1));
        final RacAppMo mo = OrikaUtils.map(appUp, getMoClass());
        thisSvc.modifyMoById(mo);
        // 修改当前这条数据的顺序号
        final RacAppMo qoMo = OrikaUtils.map(qo, getMoClass());
        qoMo.setSeqNo((byte) (qo.getSeqNo() - 1));
        thisSvc.modifyMoById(qoMo);
    }

    /**
     * 下移动
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void moveDown(final RacAppModifyTo to) {
        // 获取当前这条数据的具体数据
        final RacAppMo qo    = _mapper.selectByPrimaryKey(to.getId()).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        // 获取当前这条数据下面一条的具体数据
        final RacAppMo appQo = new RacAppMo();
        appQo.setSeqNo((byte) (qo.getSeqNo() + 1));
        appQo.setRealmId(qo.getRealmId());
        final RacAppMo appDown = _mapper.selectOne(appQo).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        // 修改当前这条数据下面一条的数据的顺序号
        appDown.setSeqNo((byte) (appDown.getSeqNo() - 1));
        final RacAppMo mo = OrikaUtils.map(appDown, getMoClass());
        thisSvc.modifyMoById(mo);
        // 修改当前这条数据的顺序号
        final RacAppMo qoMo = OrikaUtils.map(qo, getMoClass());
        qoMo.setSeqNo((byte) (qo.getSeqNo() + 1));
        thisSvc.modifyMoById(qoMo);
    }

    /**
     * 根据顺序号查询App
     */
    @Override
    public List<RacAppMo> list(RacAppListTo qo) {
        final RacAppMo mo = OrikaUtils.map(qo, getMoClass());
        return _mapper.selectListOrderBySeqNo(mo);
    }

    /**
     * 查询应用
     */
    @Override
    public List<RacAppMo> listOrderBySeqNo(RacAppListTo qo) {
        final RacAppMo mo = OrikaUtils.map(qo, RacAppMo.class);
        return _mapper.selectSelectiveOrderBySeqNo(mo);
    }
}
