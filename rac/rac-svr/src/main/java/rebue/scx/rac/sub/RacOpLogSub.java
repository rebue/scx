package rebue.scx.rac.sub;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import rebue.scx.rac.co.RacAmpqCo;
import rebue.scx.rac.svc.RacOpLogSvc;
import rebue.scx.rac.to.RacOpLogAddTo;

/**
 * 操作日志消息的订阅类
 *
 * @author zbz
 *
 */
@Service
public class RacOpLogSub {
    @Resource
    private RacOpLogSvc racOpLogSvc;

    /**
     * 添加请求日志
     *
     * @param to 添加请求日志的消息体
     */
    @RabbitListener(bindings = @QueueBinding(//
        value = @Queue(RacAmpqCo.ADD_OP_LOG), //
        exchange = @Exchange(RacAmpqCo.ADD_OP_LOG), //
        key = RacAmpqCo.ADD_OP_LOG), //
        ackMode = "AUTO")
    public void addOpLog(final RacOpLogAddTo to) {
        racOpLogSvc.add(to);
    }

}
