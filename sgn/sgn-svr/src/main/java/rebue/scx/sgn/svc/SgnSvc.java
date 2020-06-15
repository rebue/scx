package rebue.scx.sgn.svc;

import java.util.Map;

import rebue.robotech.ro.Ro;

public interface SgnSvc {

    Ro<?> verify(Map<String, ?> paramMap);

}