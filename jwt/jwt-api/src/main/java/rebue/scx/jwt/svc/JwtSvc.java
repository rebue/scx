package rebue.scx.jwt.svc;

import rebue.scx.jwt.ro.JwtSignRo;
import rebue.scx.jwt.ro.JwtVerifyRo;
import rebue.scx.jwt.to.JwtUserInfoTo;

public interface JwtSvc {
    /**
     * JWT签名
     * 
     * @param to
     *            签名中储存的用户信息
     */
    JwtSignRo sign(JwtUserInfoTo to);

    /**
     * 验证JWT签名
     * 
     * @param toVerifySign
     *            要验证的签名
     */
    JwtVerifyRo verify(String toVerifySign);
}
