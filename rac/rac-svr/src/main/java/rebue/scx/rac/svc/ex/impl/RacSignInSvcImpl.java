package rebue.scx.rac.svc.ex.impl;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.dic.SignUpOrInWayDic;
import rebue.scx.rac.mo.RacSysMo;
import rebue.scx.rac.mo.RacUserMo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.svc.RacOpLogSvc;
import rebue.scx.rac.svc.RacSysSvc;
import rebue.scx.rac.svc.RacUserSvc;
import rebue.scx.rac.svc.ex.RacSignInSvc;
import rebue.scx.rac.to.RacOpLogAddTo;
import rebue.scx.rac.to.ex.SignInByUserNameTo;
import rebue.wheel.DateUtils;
import rebue.wheel.RegexUtils;
import rebue.wheel.turing.DigestUtils;

/**
 * 用户注册服务的实现类
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
     * 后面跟用户ID拼接成Key
     * Value为失败次数
     */
    private static final String REDIS_KEY_WRONG_PSWD_TIMES_OF_SIGN_IN_PREFIX = "rebue.scx.rac.svc.sign-in.wrong-pswd-times-of-sign-in.";

    // @DubboReference(application = "jwt-svr")
    // private JwtApi jwtApi;

    @Resource
    private RacUserSvc  userSvc;
    @Resource
    private RacSysSvc   sysSvc;
    @Resource
    private RacOpLogSvc opLogSvc;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    private Mapper      dozerMapper;

    /**
     * 通过用户名称登录(按照 邮箱->手机->登录名 的顺序查找用户)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro<SignUpOrInRa> signInByUserName(final SignInByUserNameTo to) {
        log.info("根据系统ID获取系统信息");
        final RacSysMo sysMo = sysSvc.getById(to.getSysId());
        if (sysMo == null) {
            return new Ro<>(ResultDic.FAIL, "未发现此系统信息: " + to.getSysId());
        }

        RacUserMo        userMo    = null;
        SignUpOrInWayDic signInWay = null;
        if (RegexUtils.matchEmail(to.getUserName())) {
            userMo = userSvc.getOneByEmail(sysMo.getDomainId(), to.getOrgId(), to.getUserName());
            if (userMo != null) {
                signInWay = SignUpOrInWayDic.EMAIL;
            }
        }
        else if (RegexUtils.matchMobile(to.getUserName())) {
            userMo = userSvc.getOneByMobile(sysMo.getDomainId(), to.getOrgId(), to.getUserName());
            if (userMo != null) {
                signInWay = SignUpOrInWayDic.MOBILE;
            }
        }

        if (userMo == null) {
            userMo = userSvc.getOneBySignInName(sysMo.getDomainId(), to.getOrgId(), to.getUserName());
            if (userMo != null) {
                signInWay = SignUpOrInWayDic.SIGN_IN_NAME;
            }
        }

        if (userMo == null) {
            final String msg = "找不到此用户";
            log.warn(msg + ": to-{}", to);
            return new Ro<>(ResultDic.FAIL, msg + ": " + to.getUserName());
        }

        if (userMo.getSignInPswd() == null) {
            final String msg = "该用户没有设置登录密码，请设置好登录密码才能登录";
            log.warn(msg + ": to-{}", to);
            return new Ro<>(ResultDic.FAIL, msg + ": " + to.getUserName());
        }

        if (!userMo.getIsEnabled()) {
            final String msg = "该用户已被禁止登录";
            log.warn(msg + ": to-{}", to);
            return new Ro<>(ResultDic.FAIL, msg + ": " + to.getUserName());
        }

        log.info("检查用户输错密码是否超过限定次数");
        final Long wrongPswdTimesOfSignIn = getWrongPswdTimesOfSignIn(userMo.getId());
        if (wrongPswdTimesOfSignIn != null && wrongPswdTimesOfSignIn >= ALLOW_WRONG_PSWD_TIMES_OF_SIGN_IN) {
            final String msg = "因连续多次输入错误密码，该用户已被临时锁定(明日零时解锁)";
            log.warn(msg + ": to-{}", to);
            return new Ro<>(ResultDic.FAIL, msg + ": " + to.getUserName());
        }

        log.info("校验密码是否正确");
        if (!userMo.getSignInPswd().equals(saltPswd(to.getSignInPswd(), userMo.getSignInPswdSalt()))) {
            final Long allowErrCount = ALLOW_WRONG_PSWD_TIMES_OF_SIGN_IN - incrWrongPswdTimesOfSignIn(userMo.getId());

            String     msg;
            if (allowErrCount == 0) {
                msg = "因连续多次输入错误密码，该用户已被临时锁定(明日零时解锁)";
            }
            else {
                msg = "密码输入错误，还可以重试" + allowErrCount + "次";
            }

            log.warn(msg + ": to-{}", to);
            return new Ro<>(ResultDic.FAIL, msg);
        }

        if (wrongPswdTimesOfSignIn != null && wrongPswdTimesOfSignIn > 0) {
            log.info("校验密码正确后，清除输错密码次数");
            delWrongPswdTimesOfSignIn(userMo.getId());
        }

        return returnSuccessSignIn(sysMo, userMo, signInWay);
    }

    /**
     * 获取用户输入登录密码错误次数
     */
    private Long getWrongPswdTimesOfSignIn(final Long userId) {
        log.info("获取用户输入错误登录密码的次数: {}", userId);
        final String result = stringRedisTemplate.opsForValue().get(REDIS_KEY_WRONG_PSWD_TIMES_OF_SIGN_IN_PREFIX + userId);
        if (result == null || result == "" || result == "nil") {
            return null;
        }
        return Long.parseLong(result);
    }

    /**
     * 递增用户输入登录密码错误次数
     */
    private Long incrWrongPswdTimesOfSignIn(final Long userId) {
        log.info("递增用户输入错误登录密码的次数: {}", userId);
        final Long result = stringRedisTemplate.opsForValue().increment(REDIS_KEY_WRONG_PSWD_TIMES_OF_SIGN_IN_PREFIX + userId);
        stringRedisTemplate.expire(REDIS_KEY_WRONG_PSWD_TIMES_OF_SIGN_IN_PREFIX + userId, DateUtils.getSecondUtilTomorrow(), TimeUnit.SECONDS);
        return result;
    }

    /**
     * 删除输入登录密码错误次数
     */
    private Boolean delWrongPswdTimesOfSignIn(final Long userId) {
        log.info("删除用户输入错误登录密码的次数: {}", userId);
        return stringRedisTemplate.delete(REDIS_KEY_WRONG_PSWD_TIMES_OF_SIGN_IN_PREFIX + userId);
    }

    /**
     * 加盐摘要密码
     *
     * @param pswd 登录密码(不是明文，而是将明文MD5传过来)
     * @param salt 盐值
     *
     * @return
     */
    private String saltPswd(final String pswd, final String salt) {
        return DigestUtils.md5AsHexStr((pswd + salt).toLowerCase().getBytes());
    }

    /**
     * 返回成功登录
     *
     * @param loginTo
     *                  登录参数
     * @param loginType
     *                  登录类型
     * @param userMo
     *                  获取到的用户信息
     *
     * @param sysId
     * @param userMo
     * 
     * @return
     */
    private Ro<SignUpOrInRa> returnSuccessSignIn(final RacSysMo sysMo, final RacUserMo userMo, final SignUpOrInWayDic signInWay) {
        final RacOpLogAddTo opLogAddTo = new RacOpLogAddTo();
        final LocalDateTime now        = LocalDateTime.now();
        opLogAddTo.setOpType("登录");
        opLogAddTo.setSysId(sysMo.getId());
        opLogAddTo.setUserId(userMo.getId());
        opLogAddTo.setOpTitle("用户登录-" + signInWay.getDesc());
        opLogAddTo.setOpDetail("用户通过" + signInWay.getDesc() + "登录系统");
        opLogAddTo.setOpDatetime(now);
        opLogSvc.add(opLogAddTo);

        final SignUpOrInRa ra = new SignUpOrInRa();
        ra.setId(userMo.getId());
        ra.setHomePath(sysMo.getHomePath());
        return new Ro<>(ResultDic.SUCCESS, "用户登录成功", ra);
    }

}
