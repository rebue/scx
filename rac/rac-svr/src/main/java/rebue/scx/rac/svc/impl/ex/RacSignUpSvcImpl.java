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
import rebue.scx.rac.mo.RacSysMo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.svc.RacSysSvc;
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
    private RacSysSvc     sysSvc;

    @Resource
    private Mapper        dozerMapper;

    /**
     * 通过账户名称注册
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro<SignUpOrInRa> signUpByAccountName(final SignUpByAccountNameTo to) {
        log.info("根据系统ID获取系统信息");
        final RacSysMo sysMo = sysSvc.getById(to.getSysId());
        if (sysMo == null) {
            return new Ro<>(ResultDic.FAIL, "未发现此系统信息: " + to.getSysId());
        }

        // 添加账户
        final RacAccountAddTo addTo     = dozerMapper.map(to, RacAccountAddTo.class);
        final Long            accountId = accountSvc.add(addTo);

        // 如果添加成功，JWT签名
        if (accountId != null) {
            final JwtSignTo     signTo = new JwtSignTo(accountId.toString());
            final Ro<JwtSignRa> signRo = jwtApi.sign(signTo);
            if (ResultDic.SUCCESS.equals(signRo.getResult())) {
                final SignUpOrInRa ra = new SignUpOrInRa(
                    accountId,
                    signRo.getExtra().getSign(),
                    signRo.getExtra().getExpirationTime());
                return new Ro<>(ResultDic.SUCCESS, "注册账户成功", ra);
            }
            else {
                return new Ro<>(ResultDic.FAIL, "JWT签名失败");
            }
        }
        else {
            return new Ro<>(ResultDic.FAIL, "添加账户失败");
        }

    }

}
