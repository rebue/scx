package rebue.scx.orp.svc;

import rebue.robotech.ro.Ro;
import rebue.scx.orp.ra.OrpUserInfoRa;
import rebue.scx.orp.to.ForgetSignInPswdTo;
import rebue.scx.orp.to.OrpCodeTo;
import rebue.scx.rac.ra.SignUpOrInRa;

public interface OrpSvc {

    /**
     * 获取认证Url(获取认证Url后前端跳转此URL)
     */
    String getAuthUrl(String orpType, String clientId, String redirectUri);

    /**
     * 认证授权码(OP服务器收到认证请求后重定向redirectUrl，通过此方法向OP服务器发出获取access_token的请求)
     * 
     * @param accountId
     */
    OrpUserInfoRa authCode(String orpType, String clientId, OrpCodeTo to);

    /**
     * 通过授权码登录
     */
    Ro<SignUpOrInRa> signInByCode(String appId, String orpType, String clientId, OrpCodeTo to);

    /**
     * 根据账户ID绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     * 
     */
    Ro<?> bindModify(String orpType, String clientId, Long accountId, OrpCodeTo to);

    /**
     * 解除绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     * 
     */
    Ro<?> unbindModify(String orpType, String clientId, Long accountId, OrpCodeTo to);

    /**
     * 根据账户ID校验微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     */
    Ro<?> verifiyAccount(String orpType, String clientId, Long accountId, OrpCodeTo to);

    Ro<?> forgetSignInPswdTo(String orpType, String clientId, ForgetSignInPswdTo to);
}
