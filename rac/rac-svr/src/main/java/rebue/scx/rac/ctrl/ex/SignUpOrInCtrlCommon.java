package rebue.scx.rac.ctrl.ex;

import org.springframework.http.server.reactive.ServerHttpResponse;

import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.wheel.turing.JwtUtils;

public class SignUpOrInCtrlCommon {
    /**
     * JWT签名并将其加入Cookie
     */
    public static void jwtSignWithCookie(final SignUpOrInRa signUpOrInRa, final ServerHttpResponse resp) {
        JwtUtils.addCookie(signUpOrInRa.getSign(), signUpOrInRa.getExpirationTime(), resp);
        signUpOrInRa.setSign(null);
        signUpOrInRa.setExpirationTime(null);
    }
}
