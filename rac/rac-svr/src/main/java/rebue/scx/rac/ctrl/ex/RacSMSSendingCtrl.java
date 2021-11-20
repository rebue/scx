package rebue.scx.rac.ctrl.ex;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacSMSSendingApi;
import rebue.scx.rac.to.ex.RacSMSTo;
import rebue.scx.rac.to.ex.RacSMSVerificationTo;

/**
 * 短信发送控制器
 * 
 * @author yuanman
 *
 */
@RestController
public class RacSMSSendingCtrl {

    @Resource
    private RacSMSSendingApi api;

    /**
     * 发送短信
     */
    @PostMapping("/rac/sms/sending")
    public Mono<Ro<?>> msgSMSSending(@RequestBody RacSMSTo to) {
        return Mono.create(callback -> callback.success(api.sendTemplateSMS(to)));
    }

    /**
     * 短信验证码校验
     *
     */
    @PostMapping("/rac/sms/verification")
    public Mono<Ro<?>> msgSMSVerification(@RequestBody RacSMSVerificationTo to) {
        return Mono.create(callback -> callback.success(api.msgSMSVerification(to)));
    }
}
