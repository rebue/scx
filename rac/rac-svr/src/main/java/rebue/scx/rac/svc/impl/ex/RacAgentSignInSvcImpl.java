package rebue.scx.rac.svc.impl.ex;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.jwt.api.JwtApi;
import rebue.scx.jwt.ra.JwtSignRa;
import rebue.scx.jwt.to.JwtSignTo;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.RacSysMo;
import rebue.scx.rac.ra.AgentSignInRa;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.svc.RacSysSvc;
import rebue.scx.rac.svc.ex.RacAgentSignInSvc;

/**
 * 代理登录服务的实现类
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
public class RacAgentSignInSvcImpl implements RacAgentSignInSvc {

    @DubboReference(application = "jwt-svr")
    private JwtApi        jwtApi;

    @Resource
    private RacAccountSvc accountSvc;
    @Resource
    private RacSysSvc     sysSvc;

    /**
     * 代理登录
     *
     * @param accountId      登录账户ID
     * @param agentAccountId 代理账户ID
     * @param sysId          系统ID
     *
     * @return 登录成功或失败的结果
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro<AgentSignInRa> signIn(final Long accountId, final Long agentAccountId, final String sysId) {
        log.info("根据系统ID获取系统信息");
        final RacSysMo sysMo = sysSvc.getById(sysId);
        if (sysMo == null) {
            return new Ro<>(ResultDic.FAIL, "未发现此系统信息: " + sysId);
        }

        final RacAccountMo accountMo = accountSvc.getById(accountId);
        if (accountMo == null) {
            final String msg = "找不到此账户";
            log.warn(msg + ": {}", accountId);
            return new Ro<>(ResultDic.WARN, msg);
        }

        if (accountMo.getSignInPswd() == null) {
            final String msg = "该账户没有设置登录密码，请设置好登录密码才能登录";
            log.warn(msg + ": ", accountId);
            return new Ro<>(ResultDic.WARN, msg);
        }

        if (!accountMo.getIsEnabled()) {
            final String msg = "该账户已被禁止登录";
            log.warn(msg + ": ", accountId);
            return new Ro<>(ResultDic.WARN, msg);
        }

        final RacAccountMo agentAccountMo = accountSvc.getById(agentAccountId);
        if (agentAccountMo == null) {
            final String msg = "找不到代理账户";
            log.warn(msg + ": {}", agentAccountId);
            return new Ro<>(ResultDic.WARN, msg);
        }

        if (agentAccountMo.getSignInPswd() == null) {
            final String msg = "代理账户没有设置登录密码，请设置好登录密码才能登录";
            log.warn(msg + ": ", agentAccountId);
            return new Ro<>(ResultDic.WARN, msg);
        }

        if (!agentAccountMo.getIsEnabled()) {
            final String msg = "代理账户已被禁止登录";
            log.warn(msg + ": {}", agentAccountId);
            return new Ro<>(ResultDic.WARN, msg);
        }

        return returnSuccessSignIn(sysMo, accountMo, agentAccountMo);
    }

    /**
     * 返回成功登录
     *
     * @param accountMo      获取到的账户信息
     * @param agentAccountMo 获取到的代理账户信息
     * @param sysId          系统ID
     */
    private Ro<AgentSignInRa> returnSuccessSignIn(final RacSysMo sysMo, final RacAccountMo accountMo, final RacAccountMo agentAccountMo) {
        final JwtSignTo           signTo   = new JwtSignTo(accountMo.getId().toString());
        final Map<String, Object> addition = new LinkedHashMap<>();
        addition.put("agentAccountId", agentAccountMo.getId());
        signTo.setAddition(addition);
        final Ro<JwtSignRa> signRo = jwtApi.sign(signTo);
        if (ResultDic.SUCCESS.equals(signRo.getResult())) {
            final AgentSignInRa ra = new AgentSignInRa(
                accountMo.getId(),
                agentAccountMo.getId(),
                signRo.getExtra().getSign(),
                signRo.getExtra().getExpirationTime());
            return new Ro<>(ResultDic.SUCCESS, "账户代理登录成功", ra);
        }
        else {
            return new Ro<>(ResultDic.FAIL, "JWT签名失败");
        }

    }

}
