package rebue.scx.rac.api.impl.ex;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacAgentSignInApi;
import rebue.scx.rac.ra.AgentSignInRa;
import rebue.scx.rac.svc.ex.RacAgentSignInSvc;
import rebue.scx.rac.to.ex.AgentSignInTo;

/**
 * 账户登录API的实现类
 */
@DubboService
public class RacAgentSignInApiImpl implements RacAgentSignInApi {

    @Resource
    private RacAgentSignInSvc svc;

    /**
     * 代理登录
     */
    @Override
    public Ro<AgentSignInRa> signIn(@Valid final AgentSignInTo to) {
        return svc.signIn(to);
    }

}
