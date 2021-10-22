package rebue.scx.msg.ctrl;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;

import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.msg.api.TemplateMessageSendingApi;

/**
 * 模板消息发送
 * 
 * @author yuanman
 *
 */
public class TemplateMessageSendingCtrl {
    @Resource
    private TemplateMessageSendingApi api;

    @PostMapping("/msg/sending")
    public Mono<Ro<?>> publishConfig() {
        return Mono.create(callback -> callback.success(api.sendTemplateSMS("18775885903", "545489")));
    }
}
