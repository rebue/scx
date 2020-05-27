package rebue.scx.jwt.ctrl;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTClaimsSet.Builder;
import com.nimbusds.jwt.SignedJWT;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import rebue.scx.jwt.dic.JwtSignResultDic;
import rebue.scx.jwt.dic.JwtVerifyResultDic;
import rebue.scx.jwt.ro.JwtSignRo;
import rebue.scx.jwt.ro.JwtVerifyRo;
import rebue.scx.jwt.to.JwtUserInfoTo;
import rebue.wheel.turing.JwtUtils;

@RestController
@RefreshScope
public class JwtCtrl {
    private final static Logger _log = LoggerFactory.getLogger(JwtCtrl.class);

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
     *           签名中储存的用户信息
     */
    @Operation(summary = "JWT签名")
    @Parameters({ @Parameter(name = "JwtUserInfoTo", description = "签名中储存的用户信息") })
    @ApiResponses({ //
            @ApiResponse(description = "JWT签名的结果", //
                    content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = JwtSignRo.class))) })
    @PostMapping("/jwt/sign")
    JwtSignRo sign(@RequestBody final JwtUserInfoTo to) {
        _log.info("\r\n============================= 开始JWT签名 =============================\r\n");
        try {
            _log.info("JWT签名参数: JwtUserInfoTo={}", to);
            final JwtSignRo ro = new JwtSignRo();

            if (to.getUserId() == null) {
                ro.setResult(JwtSignResultDic.PARAM_ERROR);
                ro.setMsg("参数不正确-没有填写用户ID");
                return ro;
            }
            if (StringUtils.isBlank(to.getSysId())) {
                ro.setResult(JwtSignResultDic.PARAM_ERROR);
                ro.setMsg("参数不正确-没有填写系统ID");
                return ro;
            }

            try {
                // Prepare JWT with claims set
                final long now            = System.currentTimeMillis();
                final Date expirationTime = new Date(now + expirationMs);
                Builder    builder        = new JWTClaimsSet.Builder()//
                        .issuer(iss)                                                // 签发者
                        .issueTime(new Date(now))                                   // 签发时间
                        .notBeforeTime(new Date(now))                               // 不接受当前时间在此之前
                        .expirationTime(expirationTime)                             // 过期时间
                        .claim("sysId", to.getSysId())                              // 放入系统ID
                        .claim("userId", to.getUserId());                           // 放入用户ID
                if (to.getAddition() != null) {
                    builder = builder.claim("addition", to.getAddition());          // 放入签名的附加信息
                }
                final JWTClaimsSet claimsSet = builder.build();

                // 计算签名
                final String sign = JwtUtils.sign(key, claimsSet);

                final String msg = "JWT签名成功";
                _log.info("{}: {}", msg, sign);
                ro.setResult(JwtSignResultDic.SUCCESS);
                ro.setMsg(msg);
                ro.setSign(sign);
                ro.setExpirationTime(expirationTime);
                return ro;
            } catch (final JOSEException e) {
                final String msg = "JWT签名失败: JwtUserInfoTo=" + to;
                _log.error(msg, e);
                ro.setResult(JwtSignResultDic.FAIL);
                ro.setMsg(msg);
                return ro;
            }
        } finally {
            _log.info("\r\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 结束JWT签名 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\r\n");
        }

    }

    /**
     * 验证JWT签名
     * 
     * @param toVerifySign
     *                     要验证的签名
     */
    @Operation(summary = "验证JWT签名")
    @Parameters({ @Parameter(name = "toVerifySign", description = "要验证的签名") })
    @ApiResponses({ //
            @ApiResponse(//
                    description = "验证JWT签名的结果", //
                    content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = JwtVerifyRo.class))) })
    @GetMapping("/jwt/verify")
    JwtVerifyRo verify(@RequestParam("toVerifySign") final String toVerifySign) {
        _log.info("\r\n============================= 开始验证JWT签名 =============================\r\n");
        try {
            _log.info("验证JWT签名参数: toVerifySign={}", toVerifySign);

            final JwtVerifyRo ro = new JwtVerifyRo();

            if (StringUtils.isBlank(toVerifySign)) {
                ro.setResult(JwtVerifyResultDic.PARAM_ERROR);
                ro.setMsg("参数不正确-没有传入要验证的签名");
                return ro;
            }

            try {
                // 解析JWT签名
                final SignedJWT signedJWT = JwtUtils.parse(toVerifySign);

                _log.debug("解析后检查head和payload部分是否正确");
                final JOSEObjectType joseObjectType = signedJWT.getHeader().getType();
                if (joseObjectType != null && !JOSEObjectType.JWT.equals(joseObjectType)) {
                    final String msg = "验证JWT签名失败-不是JWT的签名";
                    _log.error("{}: {}", msg, joseObjectType);
                    ro.setResult(JwtVerifyResultDic.FAIL);
                    ro.setMsg(msg);
                    return ro;
                }
                final JWSAlgorithm algorithm = signedJWT.getHeader().getAlgorithm();
                if (!JWSAlgorithm.HS512.equals(algorithm)) {
                    final String msg = "验证JWT签名失败-算法不正确";
                    _log.error("{}: {}", msg, algorithm);
                    ro.setResult(JwtVerifyResultDic.FAIL);
                    ro.setMsg(msg);
                    return ro;
                }
                final String issuer = signedJWT.getJWTClaimsSet().getIssuer();
                if (!iss.equals(issuer)) {
                    final String msg = "验证JWT签名失败-签发者不正确";
                    _log.error("{}: {}", msg, issuer);
                    ro.setResult(JwtVerifyResultDic.FAIL);
                    ro.setMsg(msg);
                    return ro;
                }
                final Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
                if (new Date().after(expirationTime)) {
                    final String msg = "验证JWT签名失败-签名过期";
                    _log.error("{}: {}", msg, expirationTime);
                    ro.setResult(JwtVerifyResultDic.FAIL);
                    ro.setMsg(msg);
                    return ro;
                }
                final Date notBeforeTime = signedJWT.getJWTClaimsSet().getNotBeforeTime();
                if (new Date().before(notBeforeTime)) {
                    final String msg = "验证JWT签名失败-当前时间早于签名时间";
                    _log.error("{}: {}", msg, notBeforeTime);
                    ro.setResult(JwtVerifyResultDic.FAIL);
                    ro.setMsg(msg);
                    return ro;
                }
                final String sysId = (String) signedJWT.getJWTClaimsSet().getClaim("sysId");
                if (StringUtils.isBlank(sysId)) {
                    final String msg = "验证JWT签名失败-系统ID为空";
                    _log.error("{}: {}", msg, sysId);
                    ro.setResult(JwtVerifyResultDic.FAIL);
                    ro.setMsg(msg);
                    return ro;
                }
                final Long userId = (Long) signedJWT.getJWTClaimsSet().getClaim("userId");
                if (userId == null) {
                    final String msg = "验证JWT签名失败-用户ID为空";
                    _log.error("{}: {}", msg, userId);
                    ro.setResult(JwtVerifyResultDic.FAIL);
                    ro.setMsg(msg);
                    return ro;
                }
                @SuppressWarnings("unchecked")
                final Map<String, Object> addition = (Map<String, Object>) signedJWT.getJWTClaimsSet()
                        .getClaim("addition");

                if (!JwtUtils.verify(key, signedJWT)) {
                    final String msg = "验证JWT签名失败-签名不正确";
                    _log.error("{}: {}", msg, signedJWT.getSignature());
                    ro.setResult(JwtVerifyResultDic.FAIL);
                    ro.setMsg(msg);
                    return ro;
                }

                final String msg = "验证JWT签名成功";
                _log.info("{}: userId={}, sysId={}, addtion={}", msg, userId, sysId, addition);
                ro.setResult(JwtVerifyResultDic.SUCCESS);
                ro.setMsg(msg);
                ro.setSysId(sysId);
                ro.setUserId(userId);
                ro.setAddition(addition);
                return ro;
            } catch (final ParseException e) {
                final String msg = "验证JWT签名失败-解析不正确";
                _log.error(msg + ": " + toVerifySign, e);
                ro.setResult(JwtVerifyResultDic.FAIL);
                ro.setMsg(msg);
                return ro;
            } catch (final JOSEException e) {
                final String msg = "验证JWT签名失败-验证出现异常";
                _log.error(msg + ": " + toVerifySign, e);
                ro.setResult(JwtVerifyResultDic.FAIL);
                ro.setMsg(msg);
                return ro;
            }
        } finally {
            _log.info("\r\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 结束验证JWT签名 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\r\n");
        }
    }
}
