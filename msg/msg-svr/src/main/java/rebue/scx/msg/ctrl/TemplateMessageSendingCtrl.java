package rebue.scx.msg.ctrl;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;

import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.msg.api.TemplateMessageSendingApi;
import rebue.scx.msg.to.MsgSMSTo;

/**
 * 模板消息发送
 * 
 * @author yuanman
 *
 */
public class TemplateMessageSendingCtrl {
    @Resource
    private TemplateMessageSendingApi api;

    /**
     * 发送短信
     */
    @PostMapping("/msg/sms/sending")
    public Mono<Ro<?>> msgSMSSending() {
        return Mono.create(callback -> callback.success(api.sendTemplateSMS("18775885903")));
    }

    /**
     * 短信验证码校验
     * 
     */
    @PostMapping("/msg/sms/verification")
    public Mono<Ro<?>> msgSMSVerification(MsgSMSTo to) {
        return Mono.create(callback -> callback.success(api.msgSMSVerification(to)));
    }
}
