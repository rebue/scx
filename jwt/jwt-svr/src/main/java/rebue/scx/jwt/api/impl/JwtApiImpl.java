package rebue.scx.jwt.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import com.nimbusds.jwt.SignedJWT;

import rebue.scx.jwt.api.JwtApi;
import rebue.scx.jwt.ra.JwtSignInfo;
import rebue.scx.jwt.ra.JwtSignRa;
import rebue.scx.jwt.svc.JwtSvc;
import rebue.scx.jwt.to.JwtSignTo;

@DubboService
public class JwtApiImpl implements JwtApi {

    @Resource
    private JwtSvc svc;

    @Override
    public JwtSignRa sign(final JwtSignTo to)
    {
        return svc.sign(to);
    }

    @Override
    public SignedJWT rawSign(final JwtSignTo to)
    {
        return svc.rawSign(to);
    }

    @Override
    public JwtSignRa verify(final String sign)
    {
        return svc.verify(sign);
    }

    @Override
    public JwtSignInfo verifyNotUpdate(String sign)
    {
        return svc.verifyNotUpdate(sign);
    }

}
