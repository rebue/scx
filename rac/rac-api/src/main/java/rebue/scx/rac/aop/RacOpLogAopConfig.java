package rebue.scx.rac.aop;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.co.RacCo;
import rebue.scx.rac.pub.RacPub;
import rebue.scx.rac.to.RacOpLogAddTo;
import rebue.wheel.turing.JwtUtils;

@Aspect
@Configuration(proxyBeanMethods = false)
public class RacOpLogAopConfig {
    @Resource
    private RacPub racPub;

    @Around("@annotation(rebue.scx.rac.ann.RacOpLog)")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        final Object result = joinPoint.proceed();

        // 获取请求的Cookie
        final ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        final HttpServletRequest       request                  = servletRequestAttributes.getRequest();
        final Cookie[]                 cookies                  = request.getCookies();
        String                         sign                     = null;
        String                         sysId                    = null;
        for (final Cookie cookie : cookies) {
            final String key = cookie.getName();
            switch (key) {
            case JwtUtils.JWT_TOKEN_NAME:
                sign = cookie.getValue();
                break;
            case RacCo.SYS_ID_KEY:
                sysId = cookie.getValue();
                break;
            }
        }

        if (StringUtils.isNoneBlank(sign, sysId)) {
            final Ro<?> ro = (Ro<?>) result;
            if (ResultDic.SUCCESS.equals(ro.getResult())) {
                final Long accountId = JwtUtils.getJwtAccountIdInSign(sign);
                if (accountId != null) {
                    final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                    final Method          method          = methodSignature.getMethod();
                    final RacOpLog        annotation      = method.getAnnotation(RacOpLog.class);

                    final RacOpLogAddTo   to              = new RacOpLogAddTo();
                    to.setOpType(annotation.opType());
                    to.setOpTitle(annotation.opTitle());
                    to.setOpDetail(annotation.opDetail());
                    to.setAccountId(accountId);
                    to.setSysId(sysId);
                    to.setOpDatetime(LocalDateTime.now());
                    racPub.addOpLog(to);
                }
            }
        }
        return result;
    }

}
