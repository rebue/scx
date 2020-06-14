package rebue.scx.jwt.svc.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
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
import rebue.scx.jwt.ra.JwtSignRa;
import rebue.scx.jwt.svc.JwtSvc;
import rebue.scx.jwt.to.JwtSignTo;
import rebue.scx.jwt.to.JwtVerifyTo;
import rebue.wheel.turing.JwtUtils;

@Slf4j
@Service
public class JwtSvcImpl implements JwtSvc {

    /**
     * 签名的key
     */
    @Value("#{'${jwt.key}'.getBytes('utf-8')}")
    private byte[] key;
    /**
     * 签发者
     */
    @Value("${jwt.iss}")
    private String iss;
    /**
     * 过期分钟数（默认是30分钟）
     * 传入分钟数，自动计算为毫秒数
     */
    @Value("#{${jwt.expiration:30}*60*1000}")
    private Long   expirationMs;

    /**
     * JWT签名
     * 
     * @param to
     *            签名中储存的用户信息
     */
    @Override
    public Ro<JwtSignRa> sign(final JwtSignTo to) {
        try {
            // Prepare JWT with claims set
            final long now = System.currentTimeMillis();
            final Date expirationTime = new Date(now + expirationMs);
            Builder builder = new JWTClaimsSet.Builder()//
                    .issuer(iss)                                                // 签发者
                    .issueTime(new Date(now))                                   // 签发时间
                    .notBeforeTime(new Date(now))                               // 不接受当前时间在此之前
                    .expirationTime(expirationTime)                             // 过期时间
                    .claim("userId", to.getUserId());                           // 放入用户ID
            if (to.getAddition() != null) {
                builder = builder.claim("addition", to.getAddition());          // 放入签名的附加信息
            }
            final JWTClaimsSet claimsSet = builder.build();

            // 计算签名
            final String sign = JwtUtils.sign(key, claimsSet);

            return new Ro<>(ResultDic.SUCCESS, "JWT签名成功: userId-" + to.getUserId() + "; sign-" + sign, null, new JwtSignRa(sign, expirationTime));
        } catch (final JOSEException e) {
            return new Ro<>(ResultDic.FAIL, "JWT签名失败: to-" + to);
        }
    }

    /**
     * 验证JWT签名
     * 如果验证成功，重新生成新的签名，提供给应用刷新有效期
     * 
     * @param to
     *            验证签名是否正确的传递参数
     */
    @Override
    public Ro<JwtSignRa> verify(final JwtVerifyTo to) {
        try {
            // 解析JWT签名
            final SignedJWT signedJWT = JwtUtils.parse(to.getSign());

            log.debug("解析后检查head和payload部分是否正确");
            final JOSEObjectType joseObjectType = signedJWT.getHeader().getType();
            if (joseObjectType != null && !JOSEObjectType.JWT.equals(joseObjectType)) {
                return new Ro<>(ResultDic.FAIL, "验证JWT签名失败: 错误的JWT签名-" + joseObjectType + ", 正确的应该是-" + JOSEObjectType.JWT);
            }
            final JWSAlgorithm algorithm = signedJWT.getHeader().getAlgorithm();
            if (!JWSAlgorithm.HS512.equals(algorithm)) {
                return new Ro<>(ResultDic.FAIL, "验证JWT签名失败: 错误的算法-" + algorithm + ", 正确的应该是-" + JWSAlgorithm.HS512);
            }
            final String issuer = signedJWT.getJWTClaimsSet().getIssuer();
            if (!iss.equals(issuer)) {
                return new Ro<>(ResultDic.FAIL, "验证JWT签名失败: 错误的签发者-" + issuer + ", 正确的应该是-" + iss);
            }
            final Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (new Date().after(expirationTime)) {
                return new Ro<>(ResultDic.FAIL, "验证JWT签名失败-签名过期: " + expirationTime);
            }
            final Date notBeforeTime = signedJWT.getJWTClaimsSet().getNotBeforeTime();
            if (new Date().before(notBeforeTime)) {
                return new Ro<>(ResultDic.FAIL, "验证JWT签名失败: 当前时间早于签名时间-" + notBeforeTime);
            }
            final String userId = signedJWT.getJWTClaimsSet().getClaim("userId").toString();
            if (userId == null) {
                return new Ro<>(ResultDic.FAIL, "验证JWT签名失败: 用户ID为空");
            }
            if (!to.getUserId().equals(userId)) {
                return new Ro<>(ResultDic.FAIL, "验证JWT签名失败: 错误的用户ID-" + userId + ", 正确的应该是-" + to.getUserId());
            }
            if (!JwtUtils.verify(key, signedJWT)) {
                return new Ro<>(ResultDic.FAIL, "验证JWT签名失败: 错误的签名-" + to.getSign() + ", 正确的应该是-" + signedJWT.getSignature());
            }

            @SuppressWarnings("unchecked")
            final Map<String, Object> addition = (Map<String, Object>) signedJWT.getJWTClaimsSet().getClaim("addition");
            final Ro<JwtSignRa> ro = sign(new JwtSignTo(userId, addition));
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
