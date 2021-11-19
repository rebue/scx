package rebue.scx.rac.api.ex;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.to.ex.RacSMSTo;
import rebue.scx.rac.to.ex.RacSMSVerificationTo;

/**
 * 发短信API
 * 
 * @author yuanman
 *
 */
public interface RacSMSSendingApi {

    Ro<?> sendTemplateSMS(RacSMSTo to);

    Ro<?> msgSMSVerification(RacSMSVerificationTo to);
}
