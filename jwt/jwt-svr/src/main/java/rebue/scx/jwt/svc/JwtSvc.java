package rebue.scx.jwt.svc;

import javax.validation.Valid;

import rebue.scx.jwt.ro.JwtSignRo;
import rebue.scx.jwt.ro.JwtVerifyRo;
import rebue.scx.jwt.to.JwtSignTo;

public interface JwtSvc {
    /**
     * JWT签名
     * 
     * @param to
     *           签名中储存的用户信息
     */
    JwtSignRo sign(JwtSignTo to);

    /**
     * 验证JWT签名
     * 
     * @param signToVerify
     *                     要验证的签名
     */
    @Valid
    JwtVerifyRo verify(String signToVerify);
}
