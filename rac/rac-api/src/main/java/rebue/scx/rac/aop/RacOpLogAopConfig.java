package rebue.scx.rac.aop;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.sbs.sb.ctx.ReactiveRequestAndResponseContextHolder;
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.co.RacCookieCo;
import rebue.scx.rac.co.RacJwtSignCo;
import rebue.scx.rac.pub.RacPub;
import rebue.scx.rac.to.RacOpLogAddTo;
import rebue.wheel.core.LombokUtils;
import rebue.wheel.net.CookieUtils;
import rebue.wheel.turing.JwtUtils;

@Aspect
@Configuration(proxyBeanMethods = false)
@Slf4j
public class RacOpLogAopConfig {
    @Resource
    private RacPub racPub;

    @Bean
    public RacPub getRacPub(RacPubProperties racPubProperties, RabbitTemplate rabbitTemplate) {
        return new RacPub(racPubProperties.getSendTimeout(), rabbitTemplate);
    }

    @Around("@annotation(rebue.scx.rac.ann.RacOpLog)")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        final Object      result = joinPoint.proceed();
        @SuppressWarnings("unchecked")
        final Mono<Ro<?>> mono   = (Mono<Ro<?>>) result;
        return mono.flatMap(ro -> {
            if (ResultDic.SUCCESS.equals(ro.getResult())) {
                return ReactiveRequestAndResponseContextHolder.getRequestAndResponseContext().map(context -> {
                    String sign  = CookieUtils.getValue(context.getResponse(), JwtUtils.JWT_TOKEN_NAME);
                    String appId = CookieUtils.getValue(context.getResponse(), RacCookieCo.APP_ID_KEY);

                    if (!StringUtils.isNoneBlank(sign, appId)) {
                        sign  = CookieUtils.getValue(context.getRequest(), JwtUtils.JWT_TOKEN_NAME);
                        appId = CookieUtils.getValue(context.getRequest(), RacCookieCo.APP_ID_KEY);
                    }

                    if (StringUtils.isNoneBlank(sign, appId)) {
                        final Long accountId = JwtUtils.getJwtAccountIdFromSign(sign);
                        if (accountId != null) {
                            final Object               target               = joinPoint.getTarget();
                            final Object[]             args                 = joinPoint.getArgs();
                            final AspectExpressContext aspectExpressContext = new AspectExpressContext(target, args, result);

                            final MethodSignature      methodSignature      = (MethodSignature) joinPoint.getSignature();
                            final Method               method               = methodSignature.getMethod();
                            final RacOpLog             annotation           = method.getAnnotation(RacOpLog.class);

                            // 从JWT签名中获取代理账户ID
                            Long agentAccountId = null;
                            try {
                                final Object agentAccountIdObj = JwtUtils.getJwtAdditionItemFromSign(sign, RacJwtSignCo.AGENT_ACCOUNT_ID);
                                if (agentAccountIdObj != null) {
                                    final String agentAccountIdString = agentAccountIdObj.toString();
                                    if (StringUtils.isNotBlank(agentAccountIdString)) {
                                        agentAccountId = Long.valueOf(agentAccountIdString);
                                    }
                                }
                            } catch (final ParseException e) {
                                log.error("解析JWT签名出现异常", e);
                            }

                            final RacOpLogAddTo to = new RacOpLogAddTo();
                            to.setOpType(annotation.opType());
                            to.setOpTitle(aspectExpressContext.getString(annotation.opTitle()));
                            to.setOpDetail(aspectExpressContext.getString(annotation.opDetail()));
                            to.setAccountId(accountId);
                            to.setAgentId(agentAccountId);
                            to.setAppId(appId);
                            to.setOpDatetime(LocalDateTime.now());
                            racPub.addOpLog(to);
                        }
                    }
                    return ro;
                });
            }
            return Mono.just(ro);
        });
    }

    /**
     * @author wurenhai
     *
     * @since 2019/1/11 9:44
     */
    class AspectExpressContext {

        private final EvaluationContext context       = new StandardEvaluationContext();
        private final ParserContext     parserContext = new TemplateParserContext();
        private final ExpressionParser  parser        = new SpelExpressionParser(new SpelParserConfiguration(true, true));

        public AspectExpressContext(final Object target, final Object[] args, final Object result) {
            context.setVariable("target", target);
            context.setVariable("result", result);
            for (int i = 0; i < args.length; i++) {
                context.setVariable("p" + i, args[i]);
            }
        }

        public String getString(final String express) {
            final Expression expression = parser.parseExpression(express, parserContext);
            return LombokUtils.removeToStringNullValues(expression.getValue(context).toString());
        }

    }
}
