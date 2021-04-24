package rebue.scx.gateway.server.pub;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rebue.sbs.amqp.RabbitTemplateUtils;
import rebue.scx.rrl.co.RrlAmpqCo;
import rebue.scx.rrl.to.RrlReqLogAddTo;
import rebue.scx.rrl.to.RrlRespLogAddTo;

/**
 * 请求与响应消息的订阅类
 *
 * @author zbz
 *
 */
@Service
@Slf4j
public class RrlPub {

    @Value("${scx.gateway.send-timeout:5000}")
    private Long           sendTimeout;

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void addReqLog(final RrlReqLogAddTo to) {
        // 发送消息
        if (!RabbitTemplateUtils.send(rabbitTemplate, RrlAmpqCo.ADD_REQ_LOG, RrlAmpqCo.ADD_REQ_LOG, to, sendTimeout)) {
            final String msg = "发送添加请求日志的消息失败";
            log.error(msg);
            throw new RuntimeException(msg);
        }
    }

    public void addRespLog(final RrlRespLogAddTo to) {
        if (!RabbitTemplateUtils.send(rabbitTemplate, RrlAmpqCo.ADD_RESP_LOG, RrlAmpqCo.ADD_RESP_LOG, to, sendTimeout)) {
            final String msg = "发送添加响应日志的消息失败";
            log.error(msg);
            throw new RuntimeException(msg);
        }
    }

}
