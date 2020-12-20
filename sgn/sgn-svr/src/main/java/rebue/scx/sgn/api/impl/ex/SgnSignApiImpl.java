package rebue.scx.sgn.api.impl.ex;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.sgn.api.ex.SgnSignApi;
import rebue.scx.sgn.svc.SgnSignSvc;

@DubboService
public class SgnSignApiImpl implements SgnSignApi {
    @Resource
    private SgnSignSvc svc;

    @Override
    public Ro<?> verify(final Map<String, ?> paramMap) {
        return svc.verify(paramMap);
    }

}
