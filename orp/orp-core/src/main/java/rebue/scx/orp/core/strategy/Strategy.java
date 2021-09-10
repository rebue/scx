package rebue.scx.orp.core.strategy;

import rebue.scx.orp.core.ro.UserInfoRo;
import rebue.scx.orp.core.to.AuthCodeTo;
import rebue.scx.orp.core.to.AuthTo;

/**
 * 认证的策略
 * 
 * @author zbz
 *
 */
public interface Strategy {

    /**
     * 获取认证Url(获取认证Url后前端跳转此URL)
     */
    String getAuthUrl(AuthTo authTo);

    /**
     * 认证授权码(OP服务器收到认证请求后重定向redirectUrl，通过此方法向OP服务器发出获取access_token的请求)
     */
    UserInfoRo authCode(AuthCodeTo authCodeTo);

}
