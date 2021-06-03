package rebue.scx.rac.api.ex;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.ra.AgentSignInRa;

/**
 * 代理登录API
 *
 * Validated注解按规范应该写在接口上
 *
 * Valid注解在参数是POJO类时，写在参数类型的前面，具体约束的注解写在参数类型的属性的上方
 * Valid注解在参数是普通参数时，写在方法的上方，具体约束的注解直接写在参数类型的前面
 */
@Validated
public interface RacAgentSignInApi {

    /**
     * 代理登录
     *
     * @param accountId      登录账户ID
     * @param agentAccountId 代理账户ID
     * @param sysId          系统ID
     *
     * @return 登录成功或失败的结果
     */
    Ro<AgentSignInRa> signIn(@NotNull Long accountId, @NotNull Long agentAccountId, @NotNull String sysId);

}
