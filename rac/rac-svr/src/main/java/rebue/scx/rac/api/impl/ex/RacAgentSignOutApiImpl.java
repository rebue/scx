package rebue.scx.rac.api.impl.ex;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacAgentSignOutApi;
import rebue.scx.rac.ra.AgentSignOutRa;
import rebue.scx.rac.svc.ex.RacAgentSignOutSvc;

/**
 * 退出代理登录API的实现类
 */
@DubboService
public class RacAgentSignOutApiImpl implements RacAgentSignOutApi {

    @Resource
    private RacAgentSignOutSvc svc;

    /**
     * 退出代理登录
     *
     * @param agentAccountId 代理账户ID
     * @param agentSysId     代理账户之前登录的系统ID
     * @param urlBeforeAgent 代理之前的URL
     *
     * @return 登录成功或失败的结果
     */
    @Override
    public Ro<AgentSignOutRa> signOut(final Long agentAccountId, final String agentSysId, final String urlBeforeAgent) {
        return svc.signOut(agentAccountId, agentSysId, urlBeforeAgent);
    }

}
