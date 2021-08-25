package rebue.scx.cap.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.anji.captcha.util.StringUtils;

@RestController
@CrossOrigin
//@RequestMapping("/cap-svr")
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    @PostMapping("/cap/captcha/get")
    public ResponseModel get(@RequestBody final CaptchaVO data) {
       // assert request.getRemoteHost()!=null;
        //data.setBrowserInfo(getRemoteId(request));
        return captchaService.get(data);
    }
    @PostMapping("/cap/captcha/check")
    public ResponseModel check(@RequestBody final CaptchaVO data) {
        //data.setBrowserInfo(getRemoteId(request));
        return captchaService.check(data);
    }

    @PostMapping("/cap/captcha/verify")
    public ResponseModel verify(@RequestBody final CaptchaVO data) {
        return captchaService.verification(data);
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