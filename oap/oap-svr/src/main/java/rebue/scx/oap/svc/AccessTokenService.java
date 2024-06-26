package rebue.scx.oap.svc;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimbusds.oauth2.sdk.token.BearerAccessToken;
import com.nimbusds.oauth2.sdk.token.RefreshToken;

import rebue.scx.oap.config.OidcConfig;
import rebue.scx.oap.mapper.OapGrantMapper;
import rebue.scx.oap.mo.OapGrantMo;
import rebue.scx.oap.to.OapGrantAddTo;

@Service
public class AccessTokenService {

    @Resource
    private OapGrantMapper oapGrantMapper;

    @Autowired
    private OapGrantSvc    oapGrantSvc;

    public void saveToken(long accountId, BearerAccessToken accessToken, RefreshToken refreshToken) {
        long       now = System.currentTimeMillis();
        OapGrantMo mo  = new OapGrantMo();
        mo.setAccountId(accountId);
        List<OapGrantMo> list = oapGrantMapper.selectSelective(mo);
        for (OapGrantMo oapGrantMo : list) {
            boolean shouldDelete = oapGrantMo.getAccessTokenExpireTimestamp() == null
                    || now > oapGrantMo.getAccessTokenExpireTimestamp();
            if (shouldDelete) {
                oapGrantMapper.deleteByPrimaryKey(oapGrantMo.getId());
            }
        }
        OapGrantAddTo oapGrantMo = new OapGrantAddTo();
        oapGrantMo.setAccountId(accountId);
        oapGrantMo.setAccessToken(accessToken.getValue());
        oapGrantMo.setRefreshToken(refreshToken.getValue());
        oapGrantMo.setCreateTimestamp(now);
        oapGrantMo.setAccessTokenJson(accessToken.toJSONString());
        oapGrantMo.setAccessTokenExpireTimestamp(now + OidcConfig.ACCESS_TOKEN_LIFETIME * 1000);
        oapGrantMo.setRefreshTokenExpiresTimestamp(now + OidcConfig.REFRESH_TOKEN_LIFETIME * 1000);
        oapGrantSvc.add(oapGrantMo);
    }

    public void updateToken(OapGrantMo mo) {
        oapGrantMapper.updateByPrimaryKey(mo);
    }

    public OapGrantMo getByRefreshToken(String refreshToken) {
        OapGrantMo mo = new OapGrantMo();
        mo.setRefreshToken(refreshToken);
        List<OapGrantMo> list = oapGrantMapper.selectSelective(mo);
        long             now  = System.currentTimeMillis();
        for (OapGrantMo item : list) {
            boolean shouldDelete = item.getRefreshTokenExpiresTimestamp() == null
                    || now > item.getRefreshTokenExpiresTimestamp();
            if (shouldDelete) {
                oapGrantMapper.deleteByPrimaryKey(item.getId());
                continue;
            }
            oapGrantMapper.deleteByPrimaryKey(item.getId());
            return item;
        }
        return null;
    }

    public OapGrantMo getUserInfo(String accessToken) {
        OapGrantMo mo = new OapGrantMo();
        mo.setAccessToken(accessToken);
        return oapGrantMapper.selectOne(mo).orElse(null);
    }
}
