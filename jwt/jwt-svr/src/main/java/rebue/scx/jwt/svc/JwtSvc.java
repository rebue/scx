package rebue.scx.jwt.svc;

import rebue.scx.jwt.ra.JwtSignRa;
import rebue.scx.jwt.to.JwtSignTo;

public interface JwtSvc {
    /**
     * JWT签名
     *
     * @param to 签名中储存的账户信息
     */
    JwtSignRa sign(JwtSignTo to);

    /**
     * 验证JWT签名
     * 如果验证成功，重新生成新的签名，提供给应用刷新有效期
     */
    JwtSignRa verify(String sign);

}
