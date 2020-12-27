package rebue.scx.sgn.api.impl.ex;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.sgn.api.ex.SgnVerifyApi;
import rebue.scx.sgn.svc.ex.SgnVerifySvc;

@DubboService
public class SgnVerifyApiImpl implements SgnVerifyApi {
    @Resource
    private SgnVerifySvc svc;

    @Override
    public Ro<?> verify(final Map<String, Object> paramMap) {
        return svc.verify(paramMap);
    }

}
