package rebue.scx.jwt.svc;

import rebue.robotech.ro.Ro;
import rebue.scx.jwt.ra.JwtSignRa;
import rebue.scx.jwt.to.JwtSignTo;
import rebue.scx.jwt.to.JwtVerifyTo;

public interface JwtSvc {
    /**
     * JWT签名
     * 
     * @param to
     *            签名中储存的账户信息
     */
    Ro<JwtSignRa> sign(JwtSignTo to);

    /**
     * 验证JWT签名
     * 如果验证成功，重新生成新的签名，提供给应用刷新有效期
     * 
     * @param to
     *            验证签名是否正确的传递参数
     */
    Ro<JwtSignRa> verify(JwtVerifyTo to);
}
