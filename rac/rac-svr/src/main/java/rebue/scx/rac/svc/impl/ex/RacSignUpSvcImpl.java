package rebue.scx.rac.svc.impl.ex;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.jwt.api.JwtApi;
import rebue.scx.jwt.ra.JwtSignRa;
import rebue.scx.jwt.to.JwtSignTo;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.svc.RacAppSvc;
import rebue.scx.rac.svc.ex.RacSignUpSvc;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.ex.SignUpByAccountNameTo;

/**
 * API账户注册服务的实现类
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
public class RacSignUpSvcImpl implements RacSignUpSvc {

    @DubboReference(application = "jwt-svr")
    private JwtApi        jwtApi;

    @Resource
    private RacAccountSvc accountSvc;
    @Resource
    private RacAppSvc     appSvc;

    @Resource
    private Mapper        dozerMapper;

    /**
     * 通过账户名称注册
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro<SignUpOrInRa> signUpByAccountName(final SignUpByAccountNameTo to) {
        log.info("根据应用ID获取应用信息");
        final RacAppMo appMo = appSvc.getById(to.getAppId());
        if (appMo == null) {
            final String msg = "未发现此应用的信息";
            log.warn(msg + ": {}", to.getAppId());
            return new Ro<>(ResultDic.WARN, msg + ": " + to.getAppId());
        }

        // 添加账户
        final RacAccountAddTo addTo     = dozerMapper.map(to, RacAccountAddTo.class);
        final Long            accountId = accountSvc.add(addTo).getId();

        // 如果添加成功，JWT签名
        if (accountId == null) {
            return new Ro<>(ResultDic.FAIL, "添加账户失败");
        }
        final JwtSignTo signTo = new JwtSignTo(accountId.toString(), to.getAppId());
        final JwtSignRa signRo = jwtApi.sign(signTo);
        if (signRo.isSuccess()) {
            final SignUpOrInRa ra = new SignUpOrInRa(
                    accountId,
                    appMo.getUrl(),
                    signRo.getSign(),
                    signRo.getExpirationTime());
            return new Ro<>(ResultDic.SUCCESS, "注册账户成功", ra);
        }
        else {
            return new Ro<>(ResultDic.FAIL, "JWT签名失败");
        }
    }

}
