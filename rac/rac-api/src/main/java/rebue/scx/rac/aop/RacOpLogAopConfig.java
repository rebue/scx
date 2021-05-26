package rebue.scx.rac.aop;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.sbs.sb.ctx.ReactiveRequestContextHolder;
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.co.RacCo;
import rebue.scx.rac.pub.RacPub;
import rebue.scx.rac.to.RacOpLogAddTo;
import rebue.wheel.net.CookieUtils;
import rebue.wheel.turing.JwtUtils;

@Aspect
@Configuration(proxyBeanMethods = false)
public class RacOpLogAopConfig {
    @Resource
    private RacPub racPub;

    @Around("@annotation(rebue.scx.rac.ann.RacOpLog)")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        final Object      result = joinPoint.proceed();
        @SuppressWarnings("unchecked")
        final Mono<Ro<?>> mono   = (Mono<Ro<?>>) result;
        return mono.flatMap(ro -> {
            if (ResultDic.SUCCESS.equals(ro.getResult())) {
                return ReactiveRequestContextHolder.getRequest().flatMap(request -> {
                    final String sign  = CookieUtils.getValue(request, JwtUtils.JWT_TOKEN_NAME);
                    final String sysId = CookieUtils.getValue(request, RacCo.SYS_ID_KEY);

                    if (StringUtils.isNoneBlank(sign, sysId)) {
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
                    return Mono.just(ro);
                });
            }
            else {
                return mono;
            }
        });
    }

}
