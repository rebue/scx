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
import rebue.scx.rac.co.RacJwtSignCo;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.svc.RacAppSvc;
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
    private RacAppSvc     appSvc;

    /**
     * 代理登录
     *
     * @param accountId      登录账户ID
     * @param agentAccountId 代理账户ID
     * @param appId          要登录的应用ID
     * @param agentAppId     代理账户之前登录的应用ID
     * @param urlBeforeAgent 代理之前的URL(退出代理登录时回退到此URL)
     * 
     * @return 登录成功或失败的结果
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro<SignUpOrInRa> signIn(final Long accountId, final Long agentAccountId, final String appId, final String agentAppId, final String urlBeforeAgent) {
        log.info("根据应用ID获取应用信息");
        final RacAppMo appMo = appSvc.getById(appId);
        if (appMo == null) {
            final String msg = "未发现此应用的信息";
            log.warn(msg + ": {}", appId);
            return new Ro<>(ResultDic.WARN, msg + ": " + appId);
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

        return returnSuccessSignIn(accountMo, agentAccountMo, agentAppId, urlBeforeAgent, appMo);
    }

    /**
     * 返回成功登录
     *
     * @param accountMo      获取到的账户信息
     * @param agentAccountMo 获取到的代理账户信息
     * @param agentAppId     代理应用ID
     */
    private Ro<SignUpOrInRa> returnSuccessSignIn(
            final RacAccountMo accountMo,
            final RacAccountMo agentAccountMo,
            final String agentAppId,
            final String urlBeforeAgent,
            final RacAppMo appMo) {
        final JwtSignTo           signTo   = new JwtSignTo(accountMo.getId().toString(), appMo.getId());
        final Map<String, Object> addition = new LinkedHashMap<>();
        addition.put(RacJwtSignCo.AGENT_ACCOUNT_ID, agentAccountMo.getId());
        addition.put(RacJwtSignCo.AGENT_APP_ID, agentAppId);
        addition.put(RacJwtSignCo.URL_BEFORE_AGENT, urlBeforeAgent);
        signTo.setAddition(addition);
        final JwtSignRa signRa = jwtApi.sign(signTo);
        if (signRa.isSuccess()) {
            final SignUpOrInRa ra = new SignUpOrInRa(
                    accountMo.getId(),
                    appMo.getUrl(),
                    signRa.getSign(),
                    signRa.getExpirationTime());
            return new Ro<>(ResultDic.SUCCESS, "账户代理登录成功", ra);
        }
        else {
            return new Ro<>(ResultDic.FAIL, "JWT签名失败");
        }

    }

}
