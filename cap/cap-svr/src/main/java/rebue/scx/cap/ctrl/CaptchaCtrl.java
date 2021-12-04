package rebue.scx.cap.ctrl;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.api.CapApi;
import rebue.scx.cap.mo.CaptchaVO;

/**
 * 验证码控制器
 */
@RestController
@CrossOrigin
public class CaptchaCtrl {

    // @Autowired
    // private CaptchaService captchaService;
    @Resource
    private CapApi capApi;

    /**
     * 获取验证码
     * 
     * @param to
     * 
     * @return
     */
    @PostMapping("/cap/captcha/get")
    public Mono<Ro<?>> get(@RequestBody final CaptchaVO to) {
        return Mono.create(callback -> callback.success(capApi.getVo(to)));
    }

    /**
     * 校验验证码
     * 
     * @param to
     * 
     * @return
     */
    @PostMapping("/cap/captcha/check")
    public Mono<Ro<?>> check(@RequestBody final CaptchaVO to) {
        return Mono.create(callback -> callback.success(capApi.checkVo(to)));
    }

    // 测试使用
    // @PostMapping("/cap/captcha/verify")
    public Mono<Ro<?>> verify(@RequestBody final CaptchaVO data) {
        return Mono.create(callback -> callback.success(capApi.verification(data)));
    }

}