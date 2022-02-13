package rebue.scx.oap.svc.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.oap.dao.OapAuthLogDao;
import rebue.scx.oap.jo.OapAuthLogJo;
import rebue.scx.oap.mapper.OapAuthLogMapper;
import rebue.scx.oap.mo.OapAuthLogMo;
import rebue.scx.oap.svc.OapAuthLogSvc;
import rebue.scx.oap.to.OapAuthLogAddTo;
import rebue.scx.oap.to.OapAuthLogDelTo;
import rebue.scx.oap.to.OapAuthLogListTo;
import rebue.scx.oap.to.OapAuthLogModifyTo;
import rebue.scx.oap.to.OapAuthLogOneTo;
import rebue.scx.oap.to.OapAuthLogPageTo;
import rebue.scx.rac.api.RacOpLogApi;

/**
 * 认证记录服务实现
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
public class OapAuthLogSvcImpl extends
    BaseSvcImpl<java.lang.Long, OapAuthLogAddTo, OapAuthLogModifyTo, OapAuthLogDelTo, OapAuthLogOneTo, OapAuthLogListTo, OapAuthLogPageTo, OapAuthLogMo, OapAuthLogJo, OapAuthLogMapper, OapAuthLogDao>
    implements OapAuthLogSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private OapAuthLogSvc thisSvc;

    @DubboReference
    private RacOpLogApi   racOpLogApi;

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.Long, OapAuthLogAddTo, OapAuthLogModifyTo, OapAuthLogDelTo, OapAuthLogOneTo, OapAuthLogListTo, OapAuthLogPageTo, OapAuthLogMo, OapAuthLogJo> getThisSvc() {
        return thisSvc;
    }

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<OapAuthLogMo> getMoClass() {
        return OapAuthLogMo.class;
    }

    /**
     * 认证概况及账户概况
     */
    @Override
    public Map<String, Long> countSurvey(OapAuthLogPageTo qo) {
        Map<String, Long> map = new HashMap<String, Long>();
        qo.setIsSuccess(false);
        long countFail = _mapper.countSurvey(qo);
        map.put("认证失败", countFail);
        qo.setIsSuccess(true);
        long countSuccess = _mapper.countSurvey(qo);
        map.put("认证成功", countSuccess);
        map.put("认证总数", countSuccess + countFail);
        // RacOpLogPageTo to = new RacOpLogPageTo();
        // to.setEndDate(qo.getEndDate());
        // to.setStartDate(qo.getStartDate());
        // Ro<Map<String, Long>> countSurvey = racOpLogApi.countSurvey(to);
        // Map<String, Long> extra = countSurvey.getExtra();
        // map.putAll(extra);
        return map;
    }
}
