package rebue.scx.sgn.api;

import java.util.Map;

import rebue.robotech.ro.Ro;

public interface SgnApi {

    Ro<?> verify(Map<String, ?> paramMap);

}