package rebue.scx.jwt.svc.impl;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTClaimsSet.Builder;
import com.nimbusds.jwt.SignedJWT;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import rebue.scx.jwt.config.JwtKey;
import rebue.scx.jwt.config.JwtProperties;
import rebue.scx.jwt.ra.JwtSignRa;
import rebue.scx.jwt.svc.JwtSvc;
import rebue.scx.jwt.to.JwtSignTo;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
@EnableConfigurationProperties(JwtProperties.class)
public class JwtSvcImpl implements JwtSvc {

    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private JwtKey jwtKey;

    private static final ZoneId ZONE_ID = ZoneId.systemDefault();

    @SneakyThrows
    public JwtSignRa sign(JwtSignTo to)
    {
        Date now = new Date();
        Date exp = new Date(now.getTime() + jwtKey.getExp());
        Builder builder = new Builder()
                .issuer(jwtProperties.getIssuer())
                .subject(to.getAccountId())
                .audience(to.getAppId())
                .expirationTime(exp)
                .issueTime(now);
        if (to.getAddition() != null) {
            builder.claim("addition", to.getAddition());
        }
        JWTClaimsSet claims = builder.build();
        JWSSigner signer = new RSASSASigner(jwtKey.getPrivateKey());
        SignedJWT jwt = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claims);
        jwt.sign(signer);
        String sign = jwt.serialize();
        LocalDateTime expLocalDateTime = exp.toInstant().atZone(ZONE_ID).toLocalDateTime();
        return JwtSignRa.success(sign, expLocalDateTime);
    }

    /**
     * 验证JWT签名 如果验证成功，重新生成新的签名，提供给应用刷新有效期
     */
    @Override
    public JwtSignRa verify(String sign)
    {
        try {
            SignedJWT signedJWT = SignedJWT.parse(sign);
            boolean verify = signedJWT.verify(new RSASSAVerifier(jwtKey.getPublicKey()));
            if (!verify) {
                return JwtSignRa.error();
            }

            // 如果验证成功，需要重新签名延长过期时间
            String subject = signedJWT.getJWTClaimsSet().getSubject();
            String audience = signedJWT.getJWTClaimsSet().getAudience().get(0);
            Map<String, Object> addition = (Map<String, Object>) signedJWT.getJWTClaimsSet().getClaim("addition");
            JwtSignTo signTo = JwtSignTo.builder()
                    .accountId(subject)
                    .appId(audience)
                    .addition(addition)
                    .build();
            return sign(signTo);
        } catch (Exception e) {
            return JwtSignRa.error();
        }
    }

}
