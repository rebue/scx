package rebue.scx.rac.pub;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import rebue.scx.rac.to.RacOpLogAddTo;

/**
 * 资源访问控制消息的订阅类
 *
 * @author zbz
 *
 */
@Service
public class RacPub {

    @Value("${rac.send-timeout:5000}")
    private Long           sendTimeout;

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void addOpLog(final RacOpLogAddTo to) {
        RacPubUtils.addOpLog(rabbitTemplate, to, sendTimeout);
    }

}
