package rebue.scx.orp.core.strategy;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import lombok.SneakyThrows;
import rebue.scx.orp.core.cache.StateCache;
import rebue.scx.orp.core.config.StrategyConfig;
import rebue.scx.orp.core.ro.UserInfoRo;
import rebue.scx.orp.core.to.AuthCodeTo;
import rebue.scx.orp.core.to.AuthTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.net.httpclient.HttpClient;

import java.util.Arrays;
import java.util.Map;

/**
 * 钉钉
 */
public class DingTalkStrategy extends AbstractStrategy<AuthCodeTo, Void, Void, AuthCodeTo, OapiSnsGetuserinfoBycodeResponse> {

    /**
     * 认证的URL
     */
    @Override
    protected String authUrl() {
        return "https://oapi.dingtalk.com/connect/qrconnect?%s";
    }

    /**
     * 获取用户信息的Url
     */
    @Override
    protected String getUserInfoUrl() {
        return "https://oapi.dingtalk.com/sns/getuserinfo_bycode";
    }

    public DingTalkStrategy(StrategyConfig orpConfig, StateCache stateCache, HttpClient httpClient) {
        super(orpConfig, stateCache, httpClient);
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
    }

    /**
     * 填充AuthTo默认的值
     */
    @Override
    protected void fillAuthToDefaultValue(AuthTo authTo) {
        authTo.setResponseType("code");
        authTo.setScopes(Arrays.asList("snsapi_login"));
    }

    /**
     * 获取AccessToken
     */
    @Override
    protected AuthCodeTo getAccessToken(AuthCodeTo authCodeTo) {
        return authCodeTo;
    }

    /**
     * 生成获取用户信息的参数
     */
    @Override
    protected AuthCodeTo genGetUserInfoTo(AuthCodeTo authCodeTo) {
        return authCodeTo;
    }

    /**
     * 发送获取用户信息的请求
     */
    @SneakyThrows
    @Override
    protected OapiSnsGetuserinfoBycodeResponse sendGetUserInfo(AuthCodeTo getUserInfoTo) {
        // 通过临时授权码获取授权用户的个人信息
        DefaultDingTalkClient           client           = new DefaultDingTalkClient(getUserInfoUrl());
        OapiSnsGetuserinfoBycodeRequest reqBycodeRequest = new OapiSnsGetuserinfoBycodeRequest();
        // 通过扫描二维码，跳转指定的redirect_uri后，向url中追加的code临时授权码
        reqBycodeRequest.setTmpAuthCode(getUserInfoTo.getCode());
        return client.execute(reqBycodeRequest, getUserInfoTo.getClientId(), getUserInfoTo.getClientSecret());
    }

    /**
     * 检查获取用户信息的结果是否正确
     */
    @Override
    protected void checkGetUserInfoRo(OapiSnsGetuserinfoBycodeResponse getUserInfoRo) {
        if (!getUserInfoRo.isSuccess()) {
            throw new RuntimeExceptionX("获取用户信息错误: 钉钉返回错误("
                    + getUserInfoRo.getErrcode() + ", "
                    + getUserInfoRo.getErrmsg() + ")");
        }

    }

    /**
     * 转换不同策略的用户信息为统一的用户信息
     */
    @Override
    protected UserInfoRo convertUserInfo(OapiSnsGetuserinfoBycodeResponse getUserInfoRo) {
        return UserInfoRo.builder()
                .openId(getUserInfoRo.getUserInfo().getOpenid())
                .unionId(getUserInfoRo.getUserInfo().getUnionid())
                .nickname(getUserInfoRo.getUserInfo().getNick())
                .build();
    }
}
