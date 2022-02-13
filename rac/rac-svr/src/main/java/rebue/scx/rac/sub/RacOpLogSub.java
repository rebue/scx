package rebue.scx.rac.sub;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import rebue.scx.rac.api.RacAccountApi;
import rebue.scx.rac.api.RacAppApi;
import rebue.scx.rac.co.RacAmpqCo;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.svc.RacOpLogSvc;
import rebue.scx.rac.to.RacAccountOneTo;
import rebue.scx.rac.to.RacOpLogAddTo;
import rebue.wheel.api.exception.RuntimeExceptionX;

/**
 * 操作日志消息的订阅类
 *
 * @author zbz
 *
 */
@Service
public class RacOpLogSub {
    @Resource
    private RacOpLogSvc   racOpLogSvc;

    @Resource
    private RacAccountApi racAccountApi;
    @Resource
    private RacAppApi     racAppApi;

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
        Long           accountId = to.getAccountId();
        RacAccountMo   accountMo = racAccountApi.getById(accountId).getExtra().getOne();
        final RacAppMo appMo     = racAppApi.getById(to.getAppId()).getExtra().getOne();
        final boolean  flag      = accountMo.getRealmId().equals(appMo.getRealmId());
        if (!flag) {
            final RacAccountOneTo oneTo = new RacAccountOneTo();
            oneTo.setRealmId(appMo.getRealmId());
            if (accountMo.getUnionId() != null) {
                oneTo.setUnionId(accountMo.getUnionId());
                final RacAccountMo oneMo = racAccountApi.getOne(oneTo).getExtra().getOne();
                accountMo = oneMo;
                accountId = accountMo.getId();
                to.setAccountId(accountId);
            }
            else {
                throw new RuntimeExceptionX("查找不到当前账户: " + accountId + "的联合账户:" + accountMo.getId());
            }
        }
        racOpLogSvc.add(to);
    }

}
