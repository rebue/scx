package rebue.scx.rac.svc.ex;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.ra.AgentSignOutRa;

/**
 * 退出代理登录服务接口
 *
 * <pre>
 * 1. 在接口上方必须写上 @Validated 注解
 * 2. 参数是POJO类时用 @Valid 注解在参数类型的前面进行修饰
 *    参数是普通参数时，直接在参数类型的前面加上具体约束的注解
 * 3. (待验证)有分组时，在方法上方必须写上 @Validated 注解及分组
 * 4. 踩坑留痕：
 *    如果方法的返回值为void，在方法上方加上 @Valid 注解会出现异常，报HV000132错误
 * </pre>
 */
@Validated
public interface RacAgentSignOutSvc {

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
