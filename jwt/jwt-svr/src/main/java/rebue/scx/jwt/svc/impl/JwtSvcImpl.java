package rebue.scx.jwt.svc.impl;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTClaimsSet.Builder;
import com.nimbusds.jwt.SignedJWT;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.jwt.config.JwtProperties;
import rebue.scx.jwt.ra.JwtSignRa;
import rebue.scx.jwt.svc.JwtSvc;
import rebue.scx.jwt.to.JwtSignTo;
import rebue.scx.jwt.to.JwtVerifyTo;
import rebue.wheel.core.LocalDateTimeUtils;
import rebue.wheel.turing.JwtUtils;

@Slf4j
@Service
@EnableConfigurationProperties(JwtProperties.class)
public class JwtSvcImpl implements JwtSvc {

    @Resource
    private JwtProperties jwtProperties;

    /**
     * JWT签名
     *
     * @param to 签名中储存的账户信息
     */
    @Override
    public Ro<JwtSignRa> sign(final JwtSignTo to) {
        try {
            // Prepare JWT with claims set
            final LocalDateTime nowLocalDateTime            = LocalDateTime.now();
            final LocalDateTime expirationTimeLocalDateTime = nowLocalDateTime.plusSeconds(jwtProperties.getExpirationDuration().getSeconds());
            final Date          now                         = LocalDateTimeUtils.localDateTime2Date(nowLocalDateTime);
            final Date          expirationTime              = LocalDateTimeUtils.localDateTime2Date(expirationTimeLocalDateTime);

            Builder             builder                     = new JWTClaimsSet.Builder()    //
                .issuer(jwtProperties.getIssuer())                                      // 签发者
                .issueTime(now)                                                         // 签发时间
                .notBeforeTime(now)                                                     // 不接受当前时间在此之前
                .expirationTime(expirationTime)                                         // 过期时间
                .claim("accountId", to.getAccountId());                                       // 账户ID
            if (to.getAddition() != null) {
                builder = builder.claim("addition", to.getAddition());                      // 放入签名的附加信息
            }
            final JWTClaimsSet claimsSet = builder.build();

            // 计算签名
            final String sign = JwtUtils.sign(jwtProperties.getSignKey(), claimsSet);

            return new Ro<>(ResultDic.SUCCESS, "JWT签名成功: accountId-" + to.getAccountId() + "; sign-" + sign,
                new JwtSignRa(sign, expirationTimeLocalDateTime));
        } catch (final JOSEException e) {
            return new Ro<>(ResultDic.FAIL, "JWT签名失败: to-" + to);
        }
    }

    /**
     * 验证JWT签名 如果验证成功，重新生成新的签名，提供给应用刷新有效期
     *
     * @param to 验证签名是否正确的传递参数
     */
    @Override
    public Ro<JwtSignRa> verify(final JwtVerifyTo to) {
        try {
            // 解析JWT签名
            final SignedJWT signedJWT = JwtUtils.parse(to.getSign());

            log.debug("解析后检查head和payload部分是否正确");
            final JOSEObjectType joseObjectType = signedJWT.getHeader().getType();
            if (joseObjectType != null && !JOSEObjectType.JWT.equals(joseObjectType)) {
                return new Ro<>(ResultDic.FAIL,
                    "验证JWT签名失败: 错误的JWT签名-" + joseObjectType + ", 正确的应该是-" + JOSEObjectType.JWT);
            }
            final JWSAlgorithm algorithm = signedJWT.getHeader().getAlgorithm();
            if (!JWSAlgorithm.HS512.equals(algorithm)) {
                return new Ro<>(ResultDic.FAIL, "验证JWT签名失败: 错误的算法-" + algorithm + ", 正确的应该是-" + JWSAlgorithm.HS512);
            }
            final String issuer = signedJWT.getJWTClaimsSet().getIssuer();
            if (!jwtProperties.getIssuer().equals(issuer)) {
                return new Ro<>(ResultDic.FAIL,
                    "验证JWT签名失败: 错误的签发者-" + issuer + ", 正确的应该是-" + jwtProperties.getIssuer());
            }
            final Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (new Date().after(expirationTime)) {
                return new Ro<>(ResultDic.FAIL, "验证JWT签名失败-签名过期: " + expirationTime);
            }
            final Date notBeforeTime = signedJWT.getJWTClaimsSet().getNotBeforeTime();
            if (new Date().before(notBeforeTime)) {
                return new Ro<>(ResultDic.FAIL, "验证JWT签名失败: 当前时间早于签名时间-" + notBeforeTime);
            }
            final String accountId = signedJWT.getJWTClaimsSet().getClaim("accountId").toString();
            if (accountId == null) {
                return new Ro<>(ResultDic.FAIL, "验证JWT签名失败: 账户ID为空");
            }
            if (!to.getAccountId().equals(accountId)) {
                return new Ro<>(ResultDic.FAIL, "验证JWT签名失败: 错误的账户ID-" + accountId + ", 正确的应该是-" + to.getAccountId());
            }
            if (!JwtUtils.verify(jwtProperties.getSignKey(), signedJWT)) {
                return new Ro<>(ResultDic.FAIL,
                    "验证JWT签名失败: 错误的签名-" + to.getSign() + ", 正确的应该是-" + signedJWT.getSignature());
            }

            // 如果验证成功，需要重新签名延长过期时间
            @SuppressWarnings("unchecked")
            final Map<String, Object> addition = (Map<String, Object>) signedJWT.getJWTClaimsSet().getClaim("addition");
            final Ro<JwtSignRa>       ro       = sign(new JwtSignTo(accountId, addition));
            // 如果签名成功，因为是由验证签名的方法调用的，把返回信息改一下。也有可能签名不成功，那就不改了，直接返回什么错误
            if (ResultDic.SUCCESS.equals(ro.getResult())) {
                ro.setMsg("验证" + ro.getMsg());
            }
            return ro;
        } catch (final ParseException e) {
            return new Ro<>(ResultDic.FAIL, "验证JWT签名失败: 解析不正确 to-" + to);
        } catch (final JOSEException e) {
            return new Ro<>(ResultDic.FAIL, "验证JWT签名失败: 验证出现异常 to-" + to);
        }
    }

}
