package rebue.scx.rrl.pub;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.rrl.co.RrlAmpqCo;
import rebue.scx.rrl.to.RrlReqLogAddTo;
import rebue.scx.rrl.to.RrlRespLogAddTo;
import rebue.wheel.serialization.rabbit.RabbitTemplateUtils;

/**
 * 请求与响应消息的订阅类
 *
 * @author zbz
 *
 */
@Slf4j
public class RrlPubUtils {

    public static void addReqLog(final RabbitTemplate rabbitTemplate, final RrlReqLogAddTo to, final Long sendTimeout) {
        // 发送消息
        if (!RabbitTemplateUtils.send(rabbitTemplate, RrlAmpqCo.ADD_REQ_LOG, RrlAmpqCo.ADD_REQ_LOG, to, sendTimeout)) {
            final String msg = "发送添加请求日志的消息失败";
            log.error(msg);
            throw new RuntimeException(msg);
        }
    }

    public static void addRespLog(final RabbitTemplate rabbitTemplate, final RrlRespLogAddTo to, final Long sendTimeout) {
        if (!RabbitTemplateUtils.send(rabbitTemplate, RrlAmpqCo.ADD_RESP_LOG, RrlAmpqCo.ADD_RESP_LOG, to, sendTimeout)) {
            final String msg = "发送添加响应日志的消息失败";
            log.error(msg);
            throw new RuntimeException(msg);
        }
    }

}
