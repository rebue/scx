package rebue.scx.orp.core.strategy;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.github.rebue.orp.core.OidcCore;
import com.nimbusds.jwt.JWT;
import com.nimbusds.oauth2.sdk.TokenResponse;
import com.nimbusds.oauth2.sdk.token.AccessToken;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import rebue.scx.orp.core.cache.StateCache;
import rebue.scx.orp.core.config.StrategyConfig;
import rebue.scx.orp.core.dic.OrpTypeDic;
import rebue.scx.orp.core.mo.ClientMo;
import rebue.scx.orp.core.ro.WechatGetUserInfoRo;
import rebue.scx.orp.core.to.AuthCodeTo;
import rebue.scx.orp.core.to.AuthTo;
import rebue.scx.orp.core.to.OidcRefreshAccessTokenTo;
import rebue.scx.orp.core.to.WechatGetUserInfoTo;
import rebue.scx.orp.core.to.WechatRefreshAccessTokenTo;
import rebue.scx.orp.ra.OrpUserInfoRa;
import rebue.wheel.net.httpclient.HttpClient;

@Slf4j
public class OidcStrategy extends AbstractStrategy<TokenResponse, OidcRefreshAccessTokenTo, TokenResponse, TokenResponse, Void> {

    @Override
    public OrpTypeDic getOrpType() {
        return OrpTypeDic.Oidc;
    }

    /**
     * 认证的URL
     */
    @Override
    protected String authUrl() {
        return "http://172.20.10.44:13080/oap-svr/oap/authorize?%s";
    }

    /**
     * 获取AccessToken的Url
     */
    @Override
    protected String getAccessTokenUrl() {
        return "http://172.20.10.44:13080/oap-svr/oap/token";
    }

    /**
     * 刷新AccessToken的Url
     */
    @Override
    protected String refreshAccessTokenUrl() {
        return "http://172.20.10.44:13080/oap-svr/oap/token";
    }

    /**
     * 获取用户信息的Url
     */
    @Override
    protected String getUserInfoUrl() {
        // FIXME 获取用户信息
        return "https://api.weixin.qq.com/sns/userinfo?%s";
    }

    public OidcStrategy(final StrategyConfig orpConfig, final Map<String, ClientMo> clients, final StateCache stateCache, final HttpClient httpClient, Map<String, String> extras) {
        super(orpConfig, clients, stateCache, httpClient);
    }

    /**
     * 映射注册
     */
    @Override
    protected void mapperRegister() {
        // 认证参数转换为Map类型的请求参数
        _mapperFactory.classMap(AuthTo.class, Map.class)
                .field("clientId", "client_id")
                .field("responseType", "response_type")
                .field("scopes", "scope")
                .field("redirectUri", "redirect_uri")
                .byDefault().register();
        // 认证授权码的参数转换为Map类型的请求参数
        _mapperFactory.classMap(AuthCodeTo.class, Map.class)
                .field("clientId", "client_id")
                .field("clientSecret", "secret")
                .field("grantType", "grant_type")
                .exclude("state")
                .byDefault().register();
        // 刷新AccessToken的参数转换为Map类型的请求参数
        _mapperFactory.classMap(WechatRefreshAccessTokenTo.class, Map.class)
                .field("clientId", "appid")
                .field("grantType", "grant_type")
                .field("refreshToken", "refresh_token")
                .byDefault().register();
        // 不同策略获取用户信息的参数转换为Map类型的请求参数
        _mapperFactory.classMap(WechatGetUserInfoTo.class, Map.class)
                .field("accessToken", "access_token")
                .byDefault().register();
        // 不同策略获取用户信息的结果转换为统一的用户信息的结果
        _mapperFactory.classMap(WechatGetUserInfoRo.class, OrpUserInfoRa.class)
                .field("openid", "openId")
                .field("unionid", "unionId")
                .field("headimgurl", "avatar")
                .byDefault().register();
    }

    @Override
    protected void fillAuthToDefaultValue(AuthTo authTo) {
        authTo.setResponseType("code");
        authTo.setScopes(Arrays.asList("openid"));

    }

    /**
     * 获取AccessToken
     */
    @Override
    protected TokenResponse getAccessToken(AuthCodeTo authCodeTo) {
        // 发出获取AccessToken请求
        TokenResponse getAccessTokenRo = sendGet(getAccessTokenUrl(), authCodeTo, TokenResponse.class);
        // 检查获取AccessToken的结果是否正确
        checkGetAccessTokenRo(getAccessTokenRo);
        return getAccessTokenRo;
    }

    @SuppressWarnings("unchecked")
    @Override
    @SneakyThrows
    protected <T> T sendGet(String tokenEndpoint, Object requestPamams, Class<T> clazz) {
        String        redirectUri   = "http://172.20.11.244:13080/orp-svr/orp/auth-code/oidc/unified-auth";
        // String redirectUri = "http://172.20.11.244:13080/";
        AuthCodeTo    authCodeTo    = (AuthCodeTo) requestPamams;
        TokenResponse tokenResponse = OidcCore.tokenRequest(
                tokenEndpoint,
                authCodeTo.getClientId(),
                authCodeTo.getClientSecret(),
                authCodeTo.getCode(),
                redirectUri);
        if (tokenResponse.indicatesSuccess()) {
            log.info(StringUtils.rightPad("*** 获取token成功 ***", 100));
            return (T) tokenResponse;
        }
        log.info(StringUtils.rightPad("*** 获取token失败 ***", 100));
        return null;
    }

    /**
     * 填充AuthCodeTo默认的值
     */
    @Override
    protected void fillAuthCodeToDefaultValue(final AuthCodeTo authCodeTo) {
        authCodeTo.setGrantType("authorization_code");
    }

    @Override
    protected void checkGetAccessTokenRo(TokenResponse getAccessTokenRo) {
        if (getAccessTokenRo == null) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    protected void checkRefreshAccessTokenRo(TokenResponse refreshAccessTokenRo) {
        if (refreshAccessTokenRo == null) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    protected void checkGetUserInfoRo(Void getUserInfoRo) {
        // TODO Auto-generated method stub

    }

    @Override
    protected OidcRefreshAccessTokenTo genRefreshAccessTokenTo(AuthCodeTo authCodeTo, TokenResponse getAccessTokenRo) {
        String value = getAccessTokenRo.toSuccessResponse().getTokens().getRefreshToken().getValue();
        return OidcRefreshAccessTokenTo.builder()
                .clientId(authCodeTo.getClientId())
                .grantType("refresh_token")
                .refreshToken(value)
                .build();
    }

    @Override
    protected void updateAccessToken(TokenResponse getAccessTokenRo, TokenResponse refreshAccessTokenRo) {
        // getAccessTokenRo.setAccessToken(refreshAccessTokenRo.getAccessToken());
        AccessToken accessToken = getAccessTokenRo.toSuccessResponse().getTokens().getAccessToken();
        accessToken = refreshAccessTokenRo.toSuccessResponse().getTokens().getAccessToken();
    }

    @Override
    @SneakyThrows
    protected OrpUserInfoRa getUserInfo(TokenResponse tokenResponse) {
        OrpUserInfoRa ra      = new OrpUserInfoRa();
        JWT           idToken = tokenResponse.toSuccessResponse().getTokens().toOIDCTokens().getIDToken();
        ra.setId(idToken.getJWTClaimsSet().getSubject());
        ra.setIdToken(idToken.serialize());
        ra.setAccessToken(tokenResponse.toSuccessResponse().getTokens().getAccessToken().getValue());
        return ra;
    }

    @Override
    protected TokenResponse genGetUserInfoTo(TokenResponse tokenResponse) {
        return tokenResponse;
    }

}
