package rebue.scx.orp.core.strategy;

import java.util.Arrays;
import java.util.Map;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;

import lombok.SneakyThrows;
import rebue.scx.orp.core.cache.StateCache;
import rebue.scx.orp.core.config.StrategyConfig;
import rebue.scx.orp.core.dic.OrpTypeDic;
import rebue.scx.orp.core.mo.ClientMo;
import rebue.scx.orp.core.to.AuthCodeTo;
import rebue.scx.orp.core.to.AuthTo;
import rebue.scx.orp.ra.OrpUserInfoRa;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.net.httpclient.HttpClient;

/**
 * 钉钉
 */
public class DingTalkStrategy extends AbstractStrategy<AuthCodeTo, Void, Void, AuthCodeTo, OapiSnsGetuserinfoBycodeResponse> {

    @Override
    public OrpTypeDic getOrpType() {
        return OrpTypeDic.DingTalk;
    }

    /**
     * 认证的URL
     */
    @Override
    protected String authUrl() {
        return "https://oapi.dingtalk.com/connect/oauth2/sns_authorize?%s";
    }

    /**
     * 获取用户信息的Url
     */
    @Override
    protected String getUserInfoUrl() {
        return "https://oapi.dingtalk.com/sns/getuserinfo_bycode";
    }

    public DingTalkStrategy(final StrategyConfig orpConfig, final Map<String, ClientMo> clients, final StateCache stateCache, final HttpClient httpClient) {
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
     * 获取AccessToken
     */
    @Override
    protected AuthCodeTo getAccessToken(final AuthCodeTo authCodeTo) {
        return authCodeTo;
    }

    /**
     * 生成获取用户信息的参数
     */
    @Override
    protected AuthCodeTo genGetUserInfoTo(final AuthCodeTo authCodeTo) {
        return authCodeTo;
    }

    /**
     * 发送获取用户信息的请求
     */
    @SneakyThrows
    @Override
    protected OapiSnsGetuserinfoBycodeResponse sendGetUserInfo(final AuthCodeTo getUserInfoTo) {
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
    protected void checkGetUserInfoRo(final OapiSnsGetuserinfoBycodeResponse getUserInfoRo) {
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
    protected OrpUserInfoRa convertUserInfo(final OapiSnsGetuserinfoBycodeResponse getUserInfoRo) {
        return OrpUserInfoRa.builder()
                .openId(getUserInfoRo.getUserInfo().getOpenid())
                .unionId(getUserInfoRo.getUserInfo().getUnionid())
                .nickname(getUserInfoRo.getUserInfo().getNick())
                .build();
    }

}
