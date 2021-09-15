package rebue.scx.rac.svc.impl;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageInfo;

import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.rac.dao.RacDisableLogDao;
import rebue.scx.rac.jo.RacDisableLogJo;
import rebue.scx.rac.mapper.RacDisableLogMapper;
import rebue.scx.rac.mo.RacDisableLogMo;
import rebue.scx.rac.svc.RacDisableLogSvc;
import rebue.scx.rac.to.RacDisableLogAddTo;
import rebue.scx.rac.to.RacDisableLogDelTo;
import rebue.scx.rac.to.RacDisableLogListTo;
import rebue.scx.rac.to.RacDisableLogModifyTo;
import rebue.scx.rac.to.RacDisableLogOneTo;
import rebue.scx.rac.to.RacDisableLogPageTo;

/**
 * 账户启/禁用日志服务实现
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
public class RacDisableLogSvcImpl extends
        BaseSvcImpl<java.lang.Long, RacDisableLogAddTo, RacDisableLogModifyTo, RacDisableLogDelTo, RacDisableLogOneTo, RacDisableLogListTo, RacDisableLogPageTo, RacDisableLogMo, RacDisableLogJo, RacDisableLogMapper, RacDisableLogDao>
        implements RacDisableLogSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private RacDisableLogSvc thisSvc;

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.Long, RacDisableLogAddTo, RacDisableLogModifyTo, RacDisableLogDelTo, RacDisableLogOneTo, RacDisableLogListTo, RacDisableLogPageTo, RacDisableLogMo, RacDisableLogJo> getThisSvc() {
        return thisSvc;
    }

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<RacDisableLogMo> getMoClass() {
        return RacDisableLogMo.class;
    }

    /**
     * 启用账户日志
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public RacDisableLogMo updateDisableLog(RacDisableLogModifyTo qo) {
        final int rowCount = _mapper.updateDisableLogEx(qo);
        if (rowCount == 0) {
            final RacDisableLogAddTo ato = new RacDisableLogAddTo();
            ato.setRealmId(qo.getRealmId());
            // ato.setLockOpId(0L);
            ato.setDisableDatetime(LocalDateTime.now());
            ato.setDisableReason("账户未找到禁用记录");
            ato.setAccountId(qo.getAccountId());
            // ato.setLockAccountId(qo.getLockAccountId());
            ato.setEnableOpId(qo.getEnableOpId());
            ato.setEnableDatetime(LocalDateTime.now());
            ato.setEnableReason(qo.getEnableReason());
            // 账户未找到锁定记录时添加禁用日志
            thisSvc.add(ato);
        }
        // XXX 注意这里是this，而不是getThisSvc()，这是避免使用到了缓存
        return getById(qo.getId());
    }

    /**
     * 分页查询日志
     */
    @Override
    public PageInfo<RacDisableLogMo> page(RacDisableLogPageTo qo) {
        // final MO mo = _dozerMapper.map(qo, getMoClass());
        final ISelect select = () -> _mapper.selectEx(qo);
        return getThisSvc().page(select, qo.getPageNum(), qo.getPageSize(), qo.getOrderBy());
    }

}
