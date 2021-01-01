package rebue.scx.rac.ctrl.ex;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacSignInApi;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.ex.SignInByUserNameTo;
import rebue.wheel.turing.JwtUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户登录的控制器
 */
@RestController
public class RacSignInCtrl {

    @Resource
    private RacSignInApi api;

    /**
     * 通过用户名称登录
     */
    @PostMapping("/rac/sign-in/sign-in-by-user-name")
    public Mono<Ro<SignUpOrInRa>> signInByUserName(@RequestBody final SignInByUserNameTo to, HttpServletResponse resp) {
        return Mono.create(callback -> {
            Ro<SignUpOrInRa> ro = api.signInByUserName(to);
            ro.getExtra().setSign(null);
            ro.getExtra().setExpirationTime(null);
            callback.success(ro);
        });
    }

//    /**
//     * JWT签名并将其加入Cookie
//     */
//    private void jwtSignWithCookie(final SignUpOrInRa signUpOrInRa, final String sysId, Map<String, Object> addition,
//                                   final HttpServletResponse resp) {
//        if (addition == null) {
//            addition = new LinkedHashMap<>();
//        }
//        addition.put("isTester", signUpOrInRa.getIsTester());
//        if (signUpOrInRa.getOrgId() != null) {
//            addition.put("orgId", signUpOrInRa.getOrgId());
//        }
//        final JwtUserInfoTo to = new JwtUserInfoTo();
//        to.setUserId(signUpOrInRa.getUserId().toString());
//        to.setSysId(sysId);
//        to.setAddition(addition);
//        final JwtSignRo signRo = jwtSvc.sign(to);
//        if (JwtSignResultDic.SUCCESS.equals(signRo.getResult())) {
//            JwtUtils.addCookie(signRo.getSign(), signRo.getExpirationTime(), resp);
//            signUpOrInRa.setSign(signRo.getSign());
//            signUpOrInRa.setExpirationTime(signRo.getExpirationTime());
//        }
//    }
}
