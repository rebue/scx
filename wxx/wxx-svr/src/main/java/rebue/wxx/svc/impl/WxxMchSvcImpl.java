package rebue.wxx.svc.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.wxx.dao.WxxMchDao;
import rebue.wxx.jo.WxxMchJo;
import rebue.wxx.mapper.WxxMchMapper;
import rebue.wxx.mo.WxxMchMo;
import rebue.wxx.svc.WxxMchSvc;
import rebue.wxx.to.WxxMchAddTo;
import rebue.wxx.to.WxxMchDelTo;
import rebue.wxx.to.WxxMchListTo;
import rebue.wxx.to.WxxMchModifyTo;
import rebue.wxx.to.WxxMchOneTo;
import rebue.wxx.to.WxxMchPageTo;

/**
 * 商户(微信支付账户)服务实现
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
public class WxxMchSvcImpl
    extends BaseSvcImpl<java.lang.String, WxxMchAddTo, WxxMchModifyTo, WxxMchDelTo, WxxMchOneTo, WxxMchListTo, WxxMchPageTo, WxxMchMo, WxxMchJo, WxxMchMapper, WxxMchDao>
    implements WxxMchSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private WxxMchSvc thisSvc;

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.String, WxxMchAddTo, WxxMchModifyTo, WxxMchDelTo, WxxMchOneTo, WxxMchListTo, WxxMchPageTo, WxxMchMo, WxxMchJo> getThisSvc() {
        return thisSvc;
    }

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<WxxMchMo> getMoClass() {
        return WxxMchMo.class;
    }
}
