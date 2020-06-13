package rebue.scx.sgn.api.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.scx.sgn.api.SgnApi;
import rebue.scx.sgn.svc.SgnSvc;

@DubboService
public class SgnApiImpl implements SgnApi {
    @Resource
    private SgnSvc svc;

    @Override
    public boolean verify(final Map<String, ?> paramMap) {
        return svc.verify(paramMap);
    }

}
