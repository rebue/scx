package rebue.scx.jwt.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.Service;

import rebue.scx.jwt.api.JwtApi;
import rebue.scx.jwt.ro.JwtSignRo;
import rebue.scx.jwt.ro.JwtVerifyRo;
import rebue.scx.jwt.svc.JwtSvc;
import rebue.scx.jwt.to.JwtSignTo;

@Service
public class JwtApiImpl implements JwtApi {

    @Resource
    private JwtSvc svc;

    @Override
    public JwtSignRo sign(final JwtSignTo to) {
        return svc.sign(to);
    }

    @Override
    public JwtVerifyRo verify(final String signToVerify) {
        return svc.verify(signToVerify);
    }

}
