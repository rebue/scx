package rebue.scx.rac.api.impl.ex;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacAgentSignOutApi;
import rebue.scx.rac.ra.SignUpOrInRa;
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
     * @param agentAppId     代理账户之前登录的应用ID
     * @param urlBeforeAgent 代理之前的URL
     *
     * @return 登录成功或失败的结果
     */
    @Override
    public Ro<SignUpOrInRa> signOut(final Long agentAccountId, final String agentAppId, final String urlBeforeAgent) {
        return svc.signOut(agentAccountId, agentAppId, urlBeforeAgent);
    }

}
