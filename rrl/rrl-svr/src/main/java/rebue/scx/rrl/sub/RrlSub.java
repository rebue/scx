package rebue.scx.rrl.sub;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import rebue.scx.rrl.co.RrlAmpqCo;
import rebue.scx.rrl.svc.RrlReqLogSvc;
import rebue.scx.rrl.svc.RrlRespLogSvc;
import rebue.scx.rrl.to.RrlReqLogAddTo;
import rebue.scx.rrl.to.RrlRespLogAddTo;

/**
 * 请求与响应消息的订阅类
 *
 * @author zbz
 *
 */
@Service
public class RrlSub {

    @Resource
    private RrlReqLogSvc  rrlReqLogSvc;
    @Resource
    private RrlRespLogSvc rrlRespLogSvc;

    /**
     * 添加请求日志
     *
     * @param to 添加请求日志的消息体
     */
    @RabbitListener(bindings = @QueueBinding(//
        value = @Queue(RrlAmpqCo.ADD_REQ_LOG), //
        exchange = @Exchange(RrlAmpqCo.ADD_REQ_LOG), //
        key = RrlAmpqCo.ADD_REQ_LOG), //
        ackMode = "AUTO")
    public void addReqLog(final RrlReqLogAddTo to) {
        rrlReqLogSvc.add(to);
    }

    /**
     * 添加响应日志
     *
     * @param to 添加响应日志的消息体
     */
    @RabbitListener(bindings = @QueueBinding(//
        value = @Queue(RrlAmpqCo.ADD_RESP_LOG), //
        exchange = @Exchange(RrlAmpqCo.ADD_RESP_LOG), //
        key = RrlAmpqCo.ADD_RESP_LOG), //
        ackMode = "AUTO")
    public void addRespLog(final RrlRespLogAddTo to) {
        rrlRespLogSvc.add(to);
    }

}
