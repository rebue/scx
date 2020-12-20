package rebue.scx.sgn.svc.ex;

import java.util.Map;

import rebue.robotech.ro.Ro;

/**
 * 签名验证服务
 */
public interface SgnVerifySvc {

    Ro<?> verify(Map<String, ?> paramMap);

}