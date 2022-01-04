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
public class OidcStrategy extends AbstractStrategy<TokenResponse, OidcRefreshAccessTokenTo, TokenResponse, OrpUserInfoRa, OrpUserInfoRa> {

    private final String requestDomainName = _orpConfig.getRequestDomainName();

    @Override
    public OrpTypeDic getOrpType() {
        return OrpTypeDic.Oidc;
    }

    /**
     * 认证的URL
     */
    @Override
    protected String authUrl() {
        return requestDomainName + "/oap-svr/oap/authorize?%s";
    }

    /**
     * 获取AccessToken的Url
     */
    @Override
    protected String getAccessTokenUrl() {
        return requestDomainName + "/oap-svr/oap/token";
    }

    /**
     * 刷新AccessToken的Url
     */
    @Override
    protected String refreshAccessTokenUrl() {
        return requestDomainName + "/oap-svr/oap/token";
    }

    /**
     * 获取用户信息的Url
     */
    @Override
    protected String getUserInfoUrl() {
        return requestDomainName + "/oap-svr/oap/get-user-info?%s";
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
    @SneakyThrows
    protected TokenResponse sendGetAccessToken(AuthCodeTo authCodeTo) {
        String redirectUri = "http://172.20.11.244:13080/orp-svr/orp/auth-code/oidc/unified-auth";
        log.info(StringUtils.rightPad("*** 开始获取token ***参数" + authCodeTo.toString(), 100));
        String        tokenEndpoint = getAccessTokenUrl();
        TokenResponse tokenResponse = OidcCore.tokenRequest(
                tokenEndpoint,
                authCodeTo.getClientId(),
                authCodeTo.getClientSecret(),
                authCodeTo.getCode(),
                redirectUri);
        if (tokenResponse.indicatesSuccess()) {
            log.info(StringUtils.rightPad("*** 获取token成功 ***", 100));
            return tokenResponse;
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

    /**
     * 刷新token
     */
    @Override
    protected void refreshAccessToken(TokenResponse getAccessTokenRo, OidcRefreshAccessTokenTo refreshAccessTokenTo) {
        // // 发出刷新AccessToken请求
        // REFRESH_ACCESS_TOKEN_RO refreshAccessTokenRo = sendGet(refreshAccessTokenUrl(), refreshAccessTokenTo, REFRESH_ACCESS_TOKEN_RO());
        // // 检查刷新AccessToken的结果是否正确
        // checkRefreshAccessTokenRo(refreshAccessTokenRo);
        // // 更新AccessToken
        // updateAccessToken(getAccessTokenRo, refreshAccessTokenRo);
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
        AccessToken accessToken = getAccessTokenRo.toSuccessResponse().getTokens().getAccessToken();
        accessToken = refreshAccessTokenRo.toSuccessResponse().getTokens().getAccessToken();
    }

    @Override
    protected <T> T sendGet(String url, Object requestPamams, Class<T> clazz, String encoding) {
        return super.sendGet(url, requestPamams, clazz, encoding);
    }

    @Override
    @SneakyThrows
    protected OrpUserInfoRa genGetUserInfoTo(TokenResponse tokenResponse) {
        JWT           idToken = tokenResponse.toSuccessResponse().getTokens().toOIDCTokens().getIDToken();
        OrpUserInfoRa ra      = new OrpUserInfoRa();
        ra.setIdToken(idToken.serialize());
        ra.setAccessToken(tokenResponse.toSuccessResponse().getTokens().getAccessToken().getValue());
        return ra;
    }

    @Override
    protected void checkGetUserInfoRo(OrpUserInfoRa getUserInfoRo) {
        // TODO Auto-generated method stub

    }

}
