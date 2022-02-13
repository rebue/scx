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

    public void saveToken(final long accountId, final BearerAccessToken accessToken, final RefreshToken refreshToken) {
        final long       now = System.currentTimeMillis();
        final OapGrantMo mo  = new OapGrantMo();
        mo.setAccountId(accountId);
        final List<OapGrantMo> list = oapGrantMapper.selectSelective(mo);
        for (final OapGrantMo oapGrantMo : list) {
            final boolean shouldDelete = oapGrantMo.getAccessTokenExpireTimestamp() == null
                    || now > oapGrantMo.getAccessTokenExpireTimestamp();
            if (shouldDelete) {
                oapGrantMapper.deleteByPrimaryKey(oapGrantMo.getId());
            }
        }
        final OapGrantAddTo oapGrantMo = new OapGrantAddTo();
        oapGrantMo.setAccountId(accountId);
        oapGrantMo.setAccessToken(accessToken.getValue());
        oapGrantMo.setRefreshToken(refreshToken.getValue());
        oapGrantMo.setAccessTokenJson(accessToken.toJSONString());
        oapGrantMo.setAccessTokenExpireTimestamp(now + OidcConfig.ACCESS_TOKEN_LIFETIME * 1000);
        oapGrantMo.setRefreshTokenExpiresTimestamp(now + OidcConfig.REFRESH_TOKEN_LIFETIME * 1000);
        oapGrantSvc.add(oapGrantMo);
    }

    public void updateToken(final OapGrantMo mo) {
        oapGrantMapper.updateByPrimaryKey(mo);
    }

    public OapGrantMo getByRefreshToken(final String refreshToken) {
        final OapGrantMo mo = new OapGrantMo();
        mo.setRefreshToken(refreshToken);
        final List<OapGrantMo> list = oapGrantMapper.selectSelective(mo);
        final long             now  = System.currentTimeMillis();
        for (final OapGrantMo item : list) {
            final boolean shouldDelete = item.getRefreshTokenExpiresTimestamp() == null
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

    public OapGrantMo getUserInfo(final String accessToken) {
        final OapGrantMo mo = new OapGrantMo();
        mo.setAccessToken(accessToken);
        return oapGrantMapper.selectOne(mo).orElse(null);
    }
}
