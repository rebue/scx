package rebue.scx.rac.api.ex;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.ra.SignUpOrInRa;

/**
 * 代理登录API
 */
public interface RacAgentSignInApi {

    /**
     * 代理登录
     *
     * @param accountId      登录账户ID
     * @param agentAccountId 代理账户ID
     * @param appId          要登录的应用ID
     * @param agentAppId     代理账户之前登录的应用ID
     * @param urlBeforeAgent 代理之前的URL(退出代理登录时回退到此URL)
     *
     * @return 登录成功或失败的结果
     */
    Ro<SignUpOrInRa> signIn(Long accountId, Long agentAccountId, String appId, String agentAppId, String urlBeforeAgent);

}
