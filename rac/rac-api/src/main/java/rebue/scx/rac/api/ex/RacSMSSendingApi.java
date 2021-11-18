package rebue.scx.rac.api.ex;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.to.ex.RacSMSTo;

/**
 * 发短信API
 * 
 * @author yuanman
 *
 */
public interface RacSMSSendingApi {

    Ro<?> sendTemplateSMS(RacSMSTo to);
}
