package rebue.scx.rac.pub;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import rebue.scx.rac.to.RacOpLogAddTo;

/**
 * 资源访问控制消息的订阅类
 *
 * @author zbz
 *
 */
@Data
@AllArgsConstructor
public class RacPub {

    // @Value("${rac.send-timeout:5000}")
    private Long sendTimeout;

    // @Resource
    private RabbitTemplate rabbitTemplate;

    public void addOpLog(final RacOpLogAddTo to) {
        RacPubUtils.addOpLog(rabbitTemplate, to, sendTimeout);
    }

}
