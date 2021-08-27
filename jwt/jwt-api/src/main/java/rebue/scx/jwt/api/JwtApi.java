package rebue.scx.jwt.api;

import com.nimbusds.jwt.SignedJWT;
import org.springframework.validation.annotation.Validated;
import rebue.scx.jwt.ra.JwtSignInfo;
import rebue.scx.jwt.ra.JwtSignRa;
import rebue.scx.jwt.to.JwtSignTo;

import javax.validation.Valid;

@Validated
public interface JwtApi {
    /**
     * JWT签名
     *
     * @param to 签名中储存的账户信息
     */
    JwtSignRa sign(@Valid JwtSignTo to);

    SignedJWT rawSign(JwtSignTo to);

    /**
     * 验证JWT签名
     * 如果验证成功，重新生成新的签名，提供给应用刷新有效期
     */
    JwtSignRa verify(String sign);

    JwtSignInfo verifyNotUpdate(String sign);

}
