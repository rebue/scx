package rebue.scx.sgn.api.ex;

import java.util.Map;

import rebue.robotech.ro.Ro;

public interface SgnVerifyApi {

    Ro<?> verify(Map<String, ?> paramMap);

}