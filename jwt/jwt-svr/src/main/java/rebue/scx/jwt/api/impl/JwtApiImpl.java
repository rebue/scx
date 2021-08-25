package rebue.scx.jwt.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.jwt.api.JwtApi;
import rebue.scx.jwt.ra.JwtSignRa;
import rebue.scx.jwt.svc.JwtSvc;
import rebue.scx.jwt.to.JwtSignTo;
import rebue.scx.jwt.to.JwtVerifyTo;

@DubboService
public class JwtApiImpl implements JwtApi {

    @Resource
    private JwtSvc svc;

    @Override
    public JwtSignRa sign(final JwtSignTo to) {
        return svc.sign(to);
    }

    @Override
    public JwtSignRa verify(String sign) {
        return svc.verify(sign);
    }

}
