package rebue.scx.cap.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.api.CapApi;
import rebue.scx.cap.commom.ResponseModel;
import rebue.scx.cap.mo.CaptchaVO;
import rebue.scx.cap.ra.CaptchaVORa;
import rebue.scx.cap.svc.CaptchaService;
import rebue.scx.cap.util.StringUtils;

@RestController
@CrossOrigin
//@RequestMapping("/cap-svr")
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;
    @Reference
    private CapApi api;

    @PostMapping("/cap/captcha/get")
    public Mono<Ro<CaptchaVORa>> get(@RequestBody final CaptchaVO to) {
       // assert request.getRemoteHost()!=null;
        //data.setBrowserInfo(getRemoteId(request));
        //return Mono.create(callback -> callback.success(api.getVo(to)));
        final CaptchaVORa ra=new  CaptchaVORa();
        final ResponseModel model = captchaService.get(to);
        ra.setDataVo((CaptchaVO) model.getRepData());
        //return new Ro<>(ResultDic.SUCCESS, "获取验证码成功", ra);
        return    Mono.create(callback -> callback.success(new Ro<>(ResultDic.SUCCESS, "获取验证码成功", ra)));
    }
    @PostMapping("/cap/captcha/check")
    public Mono<Ro<CaptchaVORa>> check(@RequestBody final CaptchaVO data) {
        //data.setBrowserInfo(getRemoteId(request));
        final CaptchaVORa ra=new  CaptchaVORa();
        final ResponseModel check = captchaService.check(data);
        if (check.getRepCode().equals("0000")) {
            ra.setDataVo((CaptchaVO) check.getRepData());
            return    Mono.create(callback -> callback.success(new Ro<>(ResultDic.SUCCESS, "验证码校验成功", ra)));
        }
        else {
            return    Mono.create(callback -> callback.success(new Ro<>(ResultDic.FAIL, check.getRepMsg())));
        }
    }

    @PostMapping("/cap/captcha/verify")
    public ResponseModel verify(@RequestBody final CaptchaVO data) {
        final ResponseModel verification = captchaService.verification(data);
        return verification;
    }

    public static final String getRemoteId(final HttpServletRequest request) {
        final String xfwd = request.getHeader("X-Forwarded-For");
        final String ip = getRemoteIpFromXfwd(xfwd);
        final String ua = request.getHeader("user-agent");
        if (StringUtils.isNotBlank(ip)) {
            return ip + ua;
        }
        return request.getRemoteAddr() + ua;
    }

    private static String getRemoteIpFromXfwd(final String xfwd) {
        if (StringUtils.isNotBlank(xfwd)) {
            final String[] ipList = xfwd.split(",");
            return StringUtils.trim(ipList[0]);
        }
        return null;
    }

}