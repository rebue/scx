package rebue.scx.rac.pub;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rac.co.RacAmpqCo;
import rebue.scx.rac.to.RacOpLogAddTo;
import rebue.wheel.serialization.rabbit.RabbitTemplateUtils;

/**
 * 请求与响应消息的订阅类
 *
 * @author zbz
 *
 */
@Slf4j
public class RacPubUtils {

    public static void addOpLog(final RabbitTemplate rabbitTemplate, final RacOpLogAddTo to, final Long sendTimeout) {
        // 发送消息
        if (!RabbitTemplateUtils.send(rabbitTemplate, RacAmpqCo.ADD_OP_LOG, RacAmpqCo.ADD_OP_LOG, to, sendTimeout)) {
            final String msg = "发送添加操作日志的消息失败";
            log.error(msg);
            throw new RuntimeException(msg);
        }
    }

}
