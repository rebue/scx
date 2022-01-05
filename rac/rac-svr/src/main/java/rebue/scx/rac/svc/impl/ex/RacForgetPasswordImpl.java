package rebue.scx.rac.svc.impl.ex;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.api.CapApi;
import rebue.scx.cap.api.CapMessageSendingApi;
import rebue.scx.cap.mo.CaptchaVO;
import rebue.scx.cap.to.CapEmailVerificationTo;
import rebue.scx.cap.to.CapSMSVerificationTo;
import rebue.scx.rac.api.RacOpLogApi;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.svc.RacAppSvc;
import rebue.scx.rac.svc.ex.RacForgetPasswordSvc;
import rebue.scx.rac.to.RacOpLogAddTo;
import rebue.scx.rac.to.ex.CheckAccountNumberTo;
import rebue.scx.rac.to.ex.ForgetSignInPswdToSetTo;
import rebue.wheel.core.util.RegexUtils;

/**
 * 账户忘记密码服务的实现类
 *
 * <pre>
 * 注意：
 * 1. 查询数据库操作的方法，不用设置默认 @Transactional
 *    在类上方已经设置默认为 readOnly=true, propagation=Propagation.SUPPORTS
 *    而涉及到 增删改 数据库操作的方法时，要设置 readOnly=false, propagation=Propagation.REQUIRED
 * 2. 事务不会针对受控异常（checked exception）回滚
 *    要想回滚事务，须抛出运行时异常(RuntimeException)
 * 3. 如果类上方不带任何参数的 @Transactional 注解时，如同下面的设置
 *    propagation(传播模式)=REQUIRED，readOnly=false，isolation(事务隔离级别)=READ_COMMITTED
 * </pre>
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
@Slf4j
public class RacForgetPasswordImpl implements RacForgetPasswordSvc {

    @Resource
    private RacAccountSvc        accountSvc;
    @Resource
    private RacAppSvc            appSvc;
    @Resource
    private RacOpLogApi          opLogApi;

    @DubboReference
    private CapApi               capApi;
    @DubboReference
    private CapMessageSendingApi capSMSSendingApi;

    /**
     * 校验帐号存在
     */
    @Override
    public Ro<?> checkAccountNumber(CheckAccountNumberTo to, String appId) {
        // 校验图形验证码
        final CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(to.getCaptchaVerification());
        final Ro<?> model = capApi.verification(captchaVO);
        if (model.getResult().getCode() != 1) {
            return model;
        }
        log.info("根据应用ID获取应用信息");
        final RacAppMo appMo = appSvc.getById(appId);
        if (appMo == null) {
            final String msg = "未发现此应用的信息";
            log.warn(msg + ": {}", appId);
            return new Ro<>(ResultDic.WARN, msg + appId);
        }

        RacAccountMo accountMo = null;
        if (RegexUtils.matchEmail(to.getSignInName())) {
            accountMo = accountSvc.getOneByEmail(appMo.getRealmId(), to.getSignInName());
        }
        else if (RegexUtils.matchMobile(to.getSignInName())) {
            accountMo = accountSvc.getOneByMobile(appMo.getRealmId(), to.getSignInName());
        }
        if (accountMo == null) {
            accountMo = accountSvc.getOneBySignInName(appMo.getRealmId(), to.getSignInName());
        }

        if (accountMo == null) {
            final String msg = "找不到此账户";
            log.warn(msg + ": to-{}", to);
            return new Ro<>(ResultDic.WARN, msg + ": " + to.getSignInName());
        }
        capApi.deleteVerifiyCode(captchaVO);
        return new Ro<>(ResultDic.SUCCESS, "验证成功", accountMo);
    }

    /**
     * 忘记密码通过信息验证码修改密码
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro<?> forgetSignInPswdToSetTo(ForgetSignInPswdToSetTo to) {
        final CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(to.getCaptchaVerification());
        final Ro<?> model = capApi.verification(captchaVO);
        if (model.getResult().getCode() != 1) {
            return model;
        }
        if (to.getForgetTpye() == 0) {
            // 校验短信验证码
            RacAccountMo         accountMoById = accountSvc.getAccountMoById(to.getId());
            CapSMSVerificationTo verifiy       = new CapSMSVerificationTo();
            verifiy.setPhoneNumber(accountMoById.getSignInMobile());
            verifiy.setCode(to.getCode());
            Ro<?> verification = capSMSSendingApi.msgSMSVerification(verifiy);
            if (verification.getResult().getCode() != 1) {
                final String msg = verification.getMsg();
                log.warn(msg + ": {}", to);
                return new Ro<>(ResultDic.WARN, msg);
            }
            setSignInPswd(to.getId(), to.getSignInPswd());
            // 成功后清理验证码
            capSMSSendingApi.deleteVerifiyMobilCode(verifiy);
            capApi.deleteVerifiyCode(captchaVO);
            final RacOpLogAddTo appTo = new RacOpLogAddTo();
            appTo.setAccountId(to.getId());
            appTo.setAgentId(null);
            appTo.setAppId("unified-auth");
            appTo.setOpType("修改密码");
            appTo.setOpTitle("忘记密码");
            appTo.setOpDetail("账户忘记密码而进行的修改密码操作");
            appTo.setOpDatetime(LocalDateTime.now());
            opLogApi.add(appTo);
            return new Ro<>(ResultDic.SUCCESS, "修改成功");
        }
        if (to.getForgetTpye() == 1) {
            // 校验邮箱验证码
            RacAccountMo           accountMoById = accountSvc.getAccountMoById(to.getId());
            CapEmailVerificationTo verifiy       = new CapEmailVerificationTo();
            verifiy.setEmail(accountMoById.getSignInEmail());
            verifiy.setCode(to.getCode());
            Ro<?> verification = capSMSSendingApi.msgEmailVerification(verifiy);
            if (verification.getResult().getCode() != 1) {
                final String msg = verification.getMsg();
                log.warn(msg + ": {}", to);
                return new Ro<>(ResultDic.WARN, msg);
            }
            setSignInPswd(to.getId(), to.getSignInPswd());
            // 成功后清理验证码
            capSMSSendingApi.deleteVerifiyEmailCode(verifiy);
            capApi.deleteVerifiyCode(captchaVO);
            final RacOpLogAddTo appTo = new RacOpLogAddTo();
            appTo.setAccountId(to.getId());
            appTo.setAgentId(null);
            appTo.setAppId("unified-auth");
            appTo.setOpType("修改密码");
            appTo.setOpTitle("忘记密码");
            appTo.setOpDetail("账户忘记密码而进行的修改密码操作");
            appTo.setOpDatetime(LocalDateTime.now());
            opLogApi.add(appTo);
            return new Ro<>(ResultDic.SUCCESS, "修改成功");
        }
        return new Ro<>(ResultDic.FAIL, "暂不支持该类型找回密码");
    }

    /**
     * 忘记密码通过微信钉钉校验修改密码
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro<?> forgetSignInPswdToSetTo(Long id, String pswd, String appId) {

        setSignInPswd(id, pswd);
        // 成功后清理验证码
        final RacOpLogAddTo appTo = new RacOpLogAddTo();
        appTo.setAccountId(id);
        appTo.setAgentId(null);
        appTo.setAppId("unified-auth");
        appTo.setOpType("修改密码");
        appTo.setOpTitle("忘记密码");
        appTo.setOpDetail("账户忘记密码而进行的修改密码操作");
        appTo.setOpDatetime(LocalDateTime.now());
        opLogApi.add(appTo);
        return new Ro<>(ResultDic.SUCCESS, "修改成功");
    }

    /**
     * 设置密码
     * 
     * @param id
     * @param pswd
     */
    private void setSignInPswd(Long id, String pswd) {
        RacAccountMo mo = new RacAccountMo();
        mo.setId(id);
        mo.setSignInPswd(pswd);
        mo.setExpirationDatetime(LocalDateTime.now().plusDays(accountSvc.getPasswordDoverdue()));
        accountSvc.modifyPswdById(mo);
    }
}
