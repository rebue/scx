package rebue.scx.gateway.server.pub;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import rebue.scx.rrl.pub.RrlPubUtils;
import rebue.scx.rrl.to.RrlReqLogAddTo;
import rebue.scx.rrl.to.RrlRespLogAddTo;

/**
 * 请求与响应消息的订阅类
 *
 * @author zbz
 *
 */
@Service
public class RrlPub {

    @Value("${rrl.send-timeout:5000}")
    private Long           sendTimeout;

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void addReqLog(final RrlReqLogAddTo to) {
        RrlPubUtils.addReqLog(rabbitTemplate, to, sendTimeout);
    }

    public void addRespLog(final RrlRespLogAddTo to) {
        RrlPubUtils.addRespLog(rabbitTemplate, to, sendTimeout);
    }

}
