package rebue.scx.rac.api.ex;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.ra.AgentSignOutRa;

/**
 * 退出代理登录API
 */
public interface RacAgentSignOutApi {

    /**
     * 退出代理登录
     *
     * @param agentAccountId 代理账户ID
     * @param agentSysId     代理账户之前登录的系统ID
     * @param urlBeforeAgent 代理之前的URL
     *
     * @return 登录成功或失败的结果
     */
    Ro<AgentSignOutRa> signOut(Long agentAccountId, String agentSysId, String urlBeforeAgent);

}
