package com.github.rebue.scx.svc;

import com.github.rebue.scx.config.OidcConfig;
import com.github.rebue.scx.mapper.OapGrantMapper;
import com.github.rebue.scx.mo.OapGrantMo;
import com.github.rebue.scx.to.OapGrantAddTo;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;
import com.nimbusds.oauth2.sdk.token.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccessTokenService {

    @Resource
    private OapGrantMapper oapGrantMapper;

    @Autowired
    private OapGrantSvc oapGrantSvc;

    public void saveToken(long accountId, BearerAccessToken accessToken, RefreshToken refreshToken)
    {
        long now = System.currentTimeMillis();
        OapGrantMo mo = new OapGrantMo();
        mo.setAccountId(accountId);
        List<OapGrantMo> list = oapGrantMapper.selectSelective(mo);
        for (OapGrantMo oapGrantMo : list) {
            boolean shouldDelete = oapGrantMo.getExpireTimestamp() == null
                    || now > oapGrantMo.getExpireTimestamp();
            if (shouldDelete) {
                oapGrantMapper.deleteByPrimaryKey(oapGrantMo.getId());
            }
        }
        OapGrantAddTo oapGrantMo = new OapGrantAddTo();
        oapGrantMo.setAccountId(accountId);
        oapGrantMo.setAccessToken(accessToken.getValue());
        oapGrantMo.setRefreshToken(refreshToken.getValue());
        oapGrantMo.setAccessTokenJson(accessToken.toJSONString());
        oapGrantMo.setRefreshTokenJson(refreshToken.toJSONString());
        oapGrantMo.setExpireTimestamp(now + OidcConfig.ACCESS_TOKEN_LIFETIME * 1000);
        oapGrantMo.setCreateTimestamp(now);
        oapGrantSvc.add(oapGrantMo);
    }

    public OapGrantMo getByRefreshToken(String value)
    {
        return null;
    }

}
