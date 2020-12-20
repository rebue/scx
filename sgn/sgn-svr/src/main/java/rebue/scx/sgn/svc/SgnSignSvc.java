package rebue.scx.sgn.svc;

import java.util.Map;

import rebue.robotech.ro.Ro;

public interface SgnSignSvc {

    Ro<?> verify(Map<String, ?> paramMap);

}