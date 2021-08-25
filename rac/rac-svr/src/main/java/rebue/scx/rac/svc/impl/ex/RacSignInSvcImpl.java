package rebue.scx.rac.svc.impl.ex;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;
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
import rebue.scx.rac.dic.SignUpOrInWayDic;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.svc.RacAppSvc;
import rebue.scx.rac.svc.RacOpLogSvc;
import rebue.scx.rac.svc.ex.RacSignInSvc;
import rebue.scx.rac.to.ex.SignInByAccountNameTo;
import rebue.scx.rac.util.PswdUtils;
import rebue.wheel.core.DateUtils;
import rebue.wheel.core.RegexUtils;

/**
 * 账户登录服务的实现类
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
public class RacSignInSvcImpl implements RacSignInSvc {

    /**
     * 允许输入登录密码错误次数
     */
    private static final Long   ALLOW_WRONG_PSWD_TIMES_OF_SIGN_IN            = 5L;

    /**
     * 缓存当天连续输入登录密码错误的次数的Key的前缀
     * 后面跟账户ID拼接成Key
     * Value为失败次数
     */
    private static final String REDIS_KEY_WRONG_PSWD_TIMES_OF_SIGN_IN_PREFIX = "rebue.scx.rac.sign-in.wrong-pswd-times::";

    @DubboReference(application = "jwt-svr")
    private JwtApi              jwtApi;

    @Resource
    private RacAccountSvc       accountSvc;
    @Resource
    private RacAppSvc           appSvc;
    @Resource
    private RacOpLogSvc         opLogSvc;

    @Resource
    StringRedisTemplate         stringRedisTemplate;

    @Resource
    private Mapper              dozerMapper;

    /**
     * 通过账户名称登录(按照 邮箱->手机->登录名 的顺序查找账户)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro<SignUpOrInRa> signInByAccountName(final SignInByAccountNameTo to) {
        log.info("根据应用ID获取应用信息");
        final RacAppMo appMo = appSvc.getById(to.getAppId());
        if (appMo == null) {
            return new Ro<>(ResultDic.FAIL, "未发现此应用信息: " + to.getAppId());
        }

        RacAccountMo     accountMo = null;
        SignUpOrInWayDic signInWay = null;
        if (RegexUtils.matchEmail(to.getAccountName())) {
            accountMo = accountSvc.getOneByEmail(appMo.getRealmId(), to.getAccountName());
            if (accountMo != null) {
                signInWay = SignUpOrInWayDic.EMAIL;
            }
        }
        else if (RegexUtils.matchMobile(to.getAccountName())) {
            accountMo = accountSvc.getOneByMobile(appMo.getRealmId(), to.getAccountName());
            if (accountMo != null) {
                signInWay = SignUpOrInWayDic.MOBILE;
            }
        }

        if (accountMo == null) {
            accountMo = accountSvc.getOneBySignInName(appMo.getRealmId(), to.getAccountName());
            if (accountMo != null) {
                signInWay = SignUpOrInWayDic.SIGN_IN_NAME;
            }
        }

        if (accountMo == null) {
            final String msg = "找不到此账户";
            log.warn(msg + ": to-{}", to);
            return new Ro<>(ResultDic.WARN, msg + ": " + to.getAccountName());
        }

        if (accountMo.getSignInPswd() == null) {
            final String msg = "该账户没有设置登录密码，请设置好登录密码才能登录";
            log.warn(msg + ": to-{}", to);
            return new Ro<>(ResultDic.WARN, msg + ": " + to.getAccountName());
        }

        if (!accountMo.getIsEnabled()) {
            final String msg = "该账户已被禁止登录";
            log.warn(msg + ": to-{}", to);
            return new Ro<>(ResultDic.WARN, msg + ": " + to.getAccountName());
        }

        log.info("检查账户输错密码是否超过限定次数");
        final Long wrongPswdTimesOfSignIn = getWrongPswdTimesOfSignIn(accountMo.getId());
        if (wrongPswdTimesOfSignIn != null && wrongPswdTimesOfSignIn >= ALLOW_WRONG_PSWD_TIMES_OF_SIGN_IN) {
            final String msg = "账户已被锁定，请明天再试";
            log.warn(msg + ": to-{}", to);
            return new Ro<>(ResultDic.WARN, msg);
        }

        log.info("校验密码是否正确");
        if (!accountMo.getSignInPswd().equals(PswdUtils.saltPswd(to.getSignInPswd(), accountMo.getSignInPswdSalt()))) {
            final Long allowErrCount = ALLOW_WRONG_PSWD_TIMES_OF_SIGN_IN - incrWrongPswdTimesOfSignIn(accountMo.getId());

            String     msg;
            if (allowErrCount == 0) {
                msg = "密码错误，账户已被锁定，请明天再试";
            }
            else {
                msg = "密码错误，还可以重试" + allowErrCount + "次";
            }

            log.warn(msg + ": to-{}", to);
            return new Ro<>(ResultDic.WARN, msg);
        }

        if (wrongPswdTimesOfSignIn != null && wrongPswdTimesOfSignIn > 0) {
            log.info("校验密码正确后，清除输错密码次数");
            delWrongPswdTimesOfSignIn(accountMo.getId());
        }

        return returnSuccessSignIn(accountMo, to.getAppId(), signInWay);
    }

    /**
     * 获取账户输入登录密码错误次数
     */
    private Long getWrongPswdTimesOfSignIn(final Long accountId) {
        log.info("获取账户输入错误登录密码的次数: {}", accountId);
        final String result = stringRedisTemplate.opsForValue().get(REDIS_KEY_WRONG_PSWD_TIMES_OF_SIGN_IN_PREFIX + accountId);
        if (result == null || result.equals("") || result.equals("nil")) {
            return null;
        }
        return Long.parseLong(result);
    }

    /**
     * 递增账户输入登录密码错误次数
     */
    private Long incrWrongPswdTimesOfSignIn(final Long accountId) {
        log.info("递增账户输入错误登录密码的次数: {}", accountId);
        final Long result = stringRedisTemplate.opsForValue().increment(REDIS_KEY_WRONG_PSWD_TIMES_OF_SIGN_IN_PREFIX + accountId);
        stringRedisTemplate.expire(REDIS_KEY_WRONG_PSWD_TIMES_OF_SIGN_IN_PREFIX + accountId, DateUtils.getSecondUtilTomorrow(), TimeUnit.SECONDS);
        return result;
    }

    /**
     * 删除输入登录密码错误次数
     */
    private Boolean delWrongPswdTimesOfSignIn(final Long accountId) {
        log.info("删除账户输入错误登录密码的次数: {}", accountId);
        return stringRedisTemplate.delete(REDIS_KEY_WRONG_PSWD_TIMES_OF_SIGN_IN_PREFIX + accountId);
    }

    /**
     * 返回成功登录
     *
     * @param accountMo 获取到的账户信息
     * @param appId     应用ID
     * @param signInWay 登录方式
     */
    private Ro<SignUpOrInRa> returnSuccessSignIn(final RacAccountMo accountMo, final String appId, final SignUpOrInWayDic signInWay) {
        final JwtSignTo     signTo = new JwtSignTo(accountMo.getId().toString(), appId);
        final JwtSignRa signRo = jwtApi.sign(signTo);
        if (signRo.isSuccess()) {
            final SignUpOrInRa ra = new SignUpOrInRa(
                accountMo.getId(),
                signRo.getSign(),
                signRo.getExpirationTime());
            return new Ro<>(ResultDic.SUCCESS, "账户登录成功", ra);
        }
        else {
            return new Ro<>(ResultDic.FAIL, "JWT签名失败");
        }
    }

}
