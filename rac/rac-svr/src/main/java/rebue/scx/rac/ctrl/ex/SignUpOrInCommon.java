package rebue.scx.rac.ctrl.ex;

import javax.servlet.http.HttpServletResponse;

import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.wheel.turing.JwtUtils;

public class SignUpOrInCommon {
    /**
     * JWT签名并将其加入Cookie
     */
    public static void jwtSignWithCookie(final SignUpOrInRa signUpOrInRa, final String sysId,
            final HttpServletResponse resp) {
        JwtUtils.addCookie(signUpOrInRa.getSign(), signUpOrInRa.getExpirationTime(), resp);
        signUpOrInRa.setSign(null);
        signUpOrInRa.setExpirationTime(null);
    }
}
