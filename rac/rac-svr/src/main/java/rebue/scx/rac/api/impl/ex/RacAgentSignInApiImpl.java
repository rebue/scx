package rebue.scx.rac.api.impl.ex;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacAgentSignInApi;
import rebue.scx.rac.ra.AgentSignInRa;
import rebue.scx.rac.svc.ex.RacAgentSignInSvc;

/**
 * 账户登录API的实现类
 */
@DubboService
public class RacAgentSignInApiImpl implements RacAgentSignInApi {

    @Resource
    private RacAgentSignInSvc svc;

    /**
     * 代理登录
     *
     * @param accountId      登录账户ID
     * @param agentAccountId 代理账户ID
     * @param sysId          要登录的系统ID
     * @param agentSysId     代理账户之前登录的系统ID
     *
     * @return 登录成功或失败的结果
     */
    @Override
    public Ro<AgentSignInRa> signIn(final Long accountId, final Long agentAccountId, final String sysId, final String agentSysId) {
        return svc.signIn(accountId, agentAccountId, sysId, agentSysId);
    }

}
