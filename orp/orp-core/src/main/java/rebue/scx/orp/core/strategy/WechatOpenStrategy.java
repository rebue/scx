package rebue.scx.orp.core.strategy;

import java.util.Arrays;
import java.util.Map;

import rebue.scx.orp.core.cache.StateCache;
import rebue.scx.orp.core.config.StrategyConfig;
import rebue.scx.orp.core.dic.OrpTypeDic;
import rebue.scx.orp.core.mo.ClientMo;
import rebue.scx.orp.core.ro.UserInfoRo;
import rebue.scx.orp.core.ro.WechatGetAccessTokenRo;
import rebue.scx.orp.core.ro.WechatGetUserInfoRo;
import rebue.scx.orp.core.to.AuthCodeTo;
import rebue.scx.orp.core.to.AuthTo;
import rebue.scx.orp.core.to.WechatGetUserInfoTo;
import rebue.scx.orp.core.to.WechatRefreshAccessTokenTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.net.httpclient.HttpClient;

/**
 * 微信开放平台
 */
public class WechatOpenStrategy extends AbstractStrategy<WechatGetAccessTokenRo, WechatRefreshAccessTokenTo, WechatGetAccessTokenRo, WechatGetUserInfoTo, WechatGetUserInfoRo> {

    @Override
    public OrpTypeDic getOrpType() {
        return OrpTypeDic.WeChatOpen;
    }

    /**
     * 认证的URL
     */
    @Override
    protected String authUrl() {
        return "https://open.weixin.qq.com/connect/qrconnect?%s#wechat_redirect";
    }

    /**
     * 获取AccessToken的Url
     */
    @Override
    protected String getAccessTokenUrl() {
        return "https://api.weixin.qq.com/sns/oauth2/access_token?%s";
    }

    /**
     * 刷新AccessToken的Url
     */
    @Override
    protected String refreshAccessTokenUrl() {
        return "https://api.weixin.qq.com/sns/oauth2/refresh_token?%s";
    }

    /**
     * 获取用户信息的Url
     */
    @Override
    protected String getUserInfoUrl() {
        return "https://api.weixin.qq.com/sns/userinfo?%s";
    }

    public WechatOpenStrategy(final StrategyConfig orpConfig, final Map<String, ClientMo> clients, final StateCache stateCache, final HttpClient httpClient) {
        super(orpConfig, clients, stateCache, httpClient);
    }

    /**
     * 映射注册
     */
    @Override
    protected void mapperRegister() {
        // 认证参数转换为Map类型的请求参数
        _mapperFactory.classMap(AuthTo.class, Map.class)
                .field("clientId", "appid")
                .field("responseType", "response_type")
                .field("scopes", "scope")
                .field("redirectUri", "redirect_uri")
                .byDefault().register();
        // 认证授权码的参数转换为Map类型的请求参数
        _mapperFactory.classMap(AuthCodeTo.class, Map.class)
                .field("clientId", "appid")
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
        _mapperFactory.classMap(WechatGetUserInfoRo.class, UserInfoRo.class)
                .field("openid", "openId")
                .field("unionid", "unionId")
                .field("headimgurl", "avatar")
                .byDefault().register();
    }

    /**
     * 填充AuthTo默认的值
     */
    @Override
    protected void fillAuthToDefaultValue(final AuthTo authTo) {
        authTo.setResponseType("code");
        authTo.setScopes(Arrays.asList("snsapi_login"));
    }

    /**
     * 填充AuthCodeTo默认的值
     */
    @Override
    protected void fillAuthCodeToDefaultValue(final AuthCodeTo authCodeTo) {
        authCodeTo.setGrantType("authorization_code");
    }

    /**
     * 检查获取AccessToken的结果是否正确
     */
    @Override
    protected void checkGetAccessTokenRo(final WechatGetAccessTokenRo getAccessTokenRo) {
        if (getAccessTokenRo.getErrcode() != null && !getAccessTokenRo.getErrcode().equals(0L)) {
            throw new RuntimeExceptionX("获取AccessToken错误: 微信返回错误("
                    + getAccessTokenRo.getErrcode() + ", "
                    + getAccessTokenRo.getErrmsg() + ")");
        }
    }

    /**
     * 生成刷新AccessToken的参数
     */
    @Override
    protected WechatRefreshAccessTokenTo genRefreshAccessTokenTo(final AuthCodeTo authCodeTo, final WechatGetAccessTokenRo getAccessTokenRo) {
        return WechatRefreshAccessTokenTo.builder()
                .clientId(authCodeTo.getClientId())
                .grantType("refresh_token")
                .refreshToken(getAccessTokenRo.getRefreshToken())
                .build();
    }

    /**
     * 检查刷新AccessToken的结果是否正确
     */
    @Override
    protected void checkRefreshAccessTokenRo(final WechatGetAccessTokenRo refreshAccessTokenRo) {
        if (refreshAccessTokenRo.getErrcode() != null && !refreshAccessTokenRo.getErrcode().equals(0L)) {
            throw new RuntimeExceptionX("刷新AccessToken错误: 微信返回错误("
                    + refreshAccessTokenRo.getErrcode() + ", "
                    + refreshAccessTokenRo.getErrmsg() + ")");
        }
    }

    /**
     * 更新AccessToken
     */
    @Override
    protected void updateAccessToken(final WechatGetAccessTokenRo getAccessTokenRo, final WechatGetAccessTokenRo refreshAccessTokenRo) {
        getAccessTokenRo.setAccessToken(refreshAccessTokenRo.getAccessToken());
    }

    /**
     * 生成获取用户信息的参数
     */
    @Override
    protected WechatGetUserInfoTo genGetUserInfoTo(final WechatGetAccessTokenRo getAccessTokenRo) {
        return WechatGetUserInfoTo.builder()
                .accessToken(getAccessTokenRo.getAccessToken())
                .openid(getAccessTokenRo.getOpenid())
                .lang("zh_CN")
                .build();
    }

    /**
     * 发送获取用户信息的请求
     */
    @Override
    protected WechatGetUserInfoRo sendGetUserInfo(final WechatGetUserInfoTo getUserInfoTo) {
        return sendGet(getUserInfoUrl(), getUserInfoTo, WechatGetUserInfoRo.class, "ISO-8859-1");
    }

    /**
     * 检查获取用户信息的结果是否正确
     */
    @Override
    protected void checkGetUserInfoRo(final WechatGetUserInfoRo wechatGetUserInfoRo) {
        if (wechatGetUserInfoRo.getErrcode() != null && !wechatGetUserInfoRo.getErrcode().equals(0L)) {
            throw new RuntimeExceptionX("获取用户信息错误: 微信返回错误("
                    + wechatGetUserInfoRo.getErrcode() + ", "
                    + wechatGetUserInfoRo.getErrmsg() + ")");
        }
    }

    /**
     * 设置Token信息
     */
    @Override
    protected void setTokenInfo(final UserInfoRo userInfo, final WechatGetAccessTokenRo wechatGetAccessTokenRo) {
        userInfo.setAccessToken(wechatGetAccessTokenRo.getAccessToken());
    }
}
