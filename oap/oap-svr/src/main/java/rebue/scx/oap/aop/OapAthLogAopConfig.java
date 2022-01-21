package rebue.scx.oap.aop;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.oap.svc.OapAuthLogSvc;
import rebue.scx.oap.to.OapAuthLogAddTo;

@Aspect
@Configuration(proxyBeanMethods = false)
@Slf4j
public class OapAthLogAopConfig {
    @Resource
    private OapAuthLogSvc oapAuthLogSvc;

    @Around("@annotation(rebue.scx.oap.ann.OapAuthLog)")
    public Object around(final ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            Ro<Object> ro = Ro.fail(e.getMessage());
            addAuthLog(ro);
            return Mono.just(ro);
        }
        @SuppressWarnings("unchecked")
        final Mono<Ro<?>> mono = (Mono<Ro<?>>) result;
        return mono.flatMap(ro -> {
            addAuthLog(ro);
            return mono;
        });
    }

    /**
     * 添加日志
     * 
     * @param ro
     */
    public void addAuthLog(Ro<?> ro) {
        OapAuthLogAddTo add = new OapAuthLogAddTo();
        boolean         boo = ResultDic.SUCCESS.equals(ro.getResult());
        add.setIsSuccess(boo);
        add.setOpDatetime(LocalDateTime.now());
        add.setReason(boo ? "登录成功" : ro.getMsg());
        oapAuthLogSvc.add(add);
    }

}
