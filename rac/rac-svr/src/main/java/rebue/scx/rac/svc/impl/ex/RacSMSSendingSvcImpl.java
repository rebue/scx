package rebue.scx.rac.svc.impl.ex;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.api.CapApi;
import rebue.scx.cap.api.CapSMSSendingApi;
import rebue.scx.cap.to.CapSMSTo;
import rebue.scx.rac.mapper.RacAccountMapper;
import rebue.scx.rac.mo.ex.RacAccountNonDesensitizedMo;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.svc.ex.RacSMSSendingSvc;
import rebue.scx.rac.svc.impl.RacAccountSvcImpl;
import rebue.scx.rac.to.ex.RacSMSTo;
import rebue.wheel.api.exception.RuntimeExceptionX;

/**
 * 发送短信的实现类
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
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
@Slf4j
public class RacSMSSendingSvcImpl implements RacSMSSendingSvc {

    @Resource
    private RacAccountSvc     accountSvc;

    @Resource
    private Mapper            dozerMapper;
    @Resource
    private RacAccountMapper  racAccountMapper;
    @Resource
    private RacAccountSvcImpl accountSvcImpl;

    @DubboReference
    private CapApi            capApi;
    @DubboReference
    private CapSMSSendingApi  capSMSSendingApi;

    @Override
    public Ro<?> sendTemplateSMS(RacSMSTo to) {
        RacAccountNonDesensitizedMo mo = racAccountMapper.selectByKey(to.getAccountId()).orElse(null);
        if (mo == null && mo.getSignInMobile() == null) {
            throw new RuntimeExceptionX(mo.getSignInName() + "该帐号未绑定手机号");
        }
        CapSMSTo smsTo = new CapSMSTo();
        smsTo.setPhoneNumber(mo.getSignInMobile());
        smsTo.setCaptchaVerification(to.getCaptchaVerification());
        return capSMSSendingApi.sendTemplateSMS(smsTo);
    }

}
