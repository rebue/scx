package rebue.scx.orp.core.strategy;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.SneakyThrows;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import rebue.scx.orp.core.cache.StateCache;
import rebue.scx.orp.core.config.StrategyConfig;
import rebue.scx.orp.core.mo.ClientMo;
import rebue.scx.orp.core.to.AuthCodeTo;
import rebue.scx.orp.core.to.AuthTo;
import rebue.scx.orp.ra.OrpUserInfoRa;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.GenericTypeUtils;
import rebue.wheel.core.MapUtils;
import rebue.wheel.net.httpclient.HttpClient;

/**
 * 抽象的策略类
 * 
 * @param <GET_ACCESS_TOKEN_RO>     GetAccessTokenRo 获取AccessToken的结果
 * @param <REFRESH_ACCESS_TOKEN_TO> RefreshAccessTokenTo 刷新AccessToken的参数
 * @param <REFRESH_ACCESS_TOKEN_RO> RefreshAccessTokenRo 刷新AccessToken的结果
 * @param <GET_USER_INFO_TO>        GetUserInfoTo 获取用户信息的参数
 * @param <GET_USER_INFO_RO>        GetUserInfoTo 获取用户信息的结果
 */
public abstract class AbstractStrategy<GET_ACCESS_TOKEN_RO, REFRESH_ACCESS_TOKEN_TO, REFRESH_ACCESS_TOKEN_RO, GET_USER_INFO_TO, GET_USER_INFO_RO> implements Strategy {

    @Getter
    private Map<String, ClientMo> clients;

    private StrategyConfig        _orpConfig;

    private StateCache            _stateCache;

    private HttpClient            _httpClient;

    /**
     * 映射工厂(用于不同类型的对象之间复制属性)
     */
    protected MapperFactory       _mapperFactory = new DefaultMapperFactory.Builder().build();

    private AbstractStrategy() {
        mapperRegister();
    }

    public AbstractStrategy(final StrategyConfig orpConfig, final Map<String, ClientMo> clients, final StateCache stateCache, final HttpClient httpClient) {
        this();
        this.clients     = clients;
        this._orpConfig  = orpConfig;
        this._stateCache = stateCache;
        this._httpClient = httpClient;
    }

    /**
     * 获取认证Url(获取认证Url后前端跳转此URL)
     */
    @Override
    public String getAuthUrl(final AuthTo authTo) {
        // 检查AuthTo参数是否符合规范
        checkAuthTo(authTo);
        // 填充AuthTo默认的值
        fillAuthToDefaultValue(authTo);

        // 转换认证参数为实际请求的参数
        @SuppressWarnings("unchecked")
        final Map<String, Object> requestParams = _mapperFactory.getMapperFacade().map(authTo, Map.class);
        if (_orpConfig.getIsCheckState()) {
            final String state = UUID.randomUUID().toString();
            _stateCache.set(getOrpType().name(), authTo.getClientId(), state);
            requestParams.put("state", state);
        }
        return String.format(authUrl(), MapUtils.map2UrlParams(requestParams));
    }

    /**
     * 认证授权码(OP服务器收到认证请求后重定向redirectUrl，通过此方法向OP服务器发出获取access_token的请求)
     */
    @Override
    public OrpUserInfoRa authCode(final AuthCodeTo authCodeTo) {
        // 检查AuthCodeTo参数是否符合规范
        checkAuthCodeTo(authCodeTo);
        // 填充AuthCodeTo默认的值
        fillAuthCodeToDefaultValue(authCodeTo);
        final GET_ACCESS_TOKEN_RO getAccessTokenRo = getAccessToken(authCodeTo);
        final OrpUserInfoRa       userInfo         = getUserInfo(genGetUserInfoTo(getAccessTokenRo));
        // 设置Token信息
        setTokenInfo(userInfo, getAccessTokenRo);
        return userInfo;
    }

    /**
     * 认证的URL
     */
    protected String authUrl() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取AccessToken的Url
     */
    protected String getAccessTokenUrl() {
        throw new UnsupportedOperationException();
    }

    /**
     * 刷新AccessToken的Url
     */
    protected String refreshAccessTokenUrl() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取用户信息的Url
     */
    protected String getUserInfoUrl() {
        throw new UnsupportedOperationException();
    }

    /**
     * GET_ACCESS_TOKEN_RO泛型的class
     */
    @SuppressWarnings("unchecked")
    private Class<GET_ACCESS_TOKEN_RO> GET_ACCESS_TOKEN_RO() {
        return (Class<GET_ACCESS_TOKEN_RO>) GenericTypeUtils.getGenericClass(0);
    }

    // @SuppressWarnings("unchecked")
    // private Class<REFRESH_ACCESS_TOKEN_TO> REFRESH_ACCESS_TOKEN_TO() {
    // return (Class<REFRESH_ACCESS_TOKEN_TO>) GenericTypeUtils.getGenericClass(1);
    // }

    /**
     * REFRESH_ACCESS_TOKEN_RO泛型的class
     */
    @SuppressWarnings("unchecked")
    private Class<REFRESH_ACCESS_TOKEN_RO> REFRESH_ACCESS_TOKEN_RO() {
        return (Class<REFRESH_ACCESS_TOKEN_RO>) GenericTypeUtils.getGenericClass(2);
    }

    // @SuppressWarnings("unchecked")
    // private Class<GET_USER_INFO_TO> GET_USER_INFO_TO() {
    // return (Class<GET_USER_INFO_TO>) GenericTypeUtils.getGenericClass(3);
    // }

    /**
     * GET_USER_INFO_RO泛型的class
     */
    @SuppressWarnings("unchecked")
    private Class<GET_USER_INFO_RO> GET_USER_INFO_RO() {
        return (Class<GET_USER_INFO_RO>) GenericTypeUtils.getGenericClass(4);
    }

    /**
     * 映射注册
     */
    protected abstract void mapperRegister();

    /**
     * 检查AuthTo参数是否符合规范
     */
    protected void checkAuthTo(final AuthTo authTo) {
        if (StringUtils.isBlank(authTo.getClientId())) {
            throw new RuntimeExceptionX("clientId不能为空");
        }
        if (!this.getClients().containsKey(authTo.getClientId())) {
            throw new RuntimeExceptionX("没有配置此clientId: " + authTo.getClientId());
        }
        if (StringUtils.isBlank(authTo.getRedirectUri())) {
            throw new RuntimeExceptionX("redirectUri不能为空");
        }
    }

    /**
     * 填充AuthTo默认的值
     */
    protected abstract void fillAuthToDefaultValue(AuthTo authTo);

    /**
     * 检查AuthCodeTo参数是否符合规范
     */
    protected void checkAuthCodeTo(final AuthCodeTo authCodeTo) {
        if (StringUtils.isBlank(authCodeTo.getClientId())) {
            throw new RuntimeExceptionX("clientId不能为空");
        }

        final ClientMo clientMo = this.getClients().get(authCodeTo.getClientId());
        if (clientMo == null) {
            throw new RuntimeExceptionX("没有配置此clientId: " + authCodeTo.getClientId());
        }
        // 填充secret
        authCodeTo.setClientSecret(clientMo.getSecret());

        if (StringUtils.isBlank(authCodeTo.getCode())) {
            throw new RuntimeExceptionX("code不能为空");
        }
        if (_orpConfig.getIsCheckState()) {
            if (StringUtils.isBlank(authCodeTo.getState())) {
                throw new RuntimeExceptionX("state不能为空");
            }
            final String state = _stateCache.get(getOrpType().name(), authCodeTo.getClientId(), authCodeTo.getState());
            if (StringUtils.isBlank(state)) {
                throw new RuntimeExceptionX("state错误: " + state);
            }
        }
    }

    /**
     * 填充AuthCodeTo默认的值
     */
    protected void fillAuthCodeToDefaultValue(final AuthCodeTo authCodeTo) {

    }

    /**
     * 获取AccessToken
     */
    @SneakyThrows
    protected GET_ACCESS_TOKEN_RO getAccessToken(final AuthCodeTo authCodeTo) {
        // 发出获取AccessToken请求
        GET_ACCESS_TOKEN_RO getAccessTokenRo = sendGet(getAccessTokenUrl(), authCodeTo, GET_ACCESS_TOKEN_RO());
        // 检查获取AccessToken的结果是否正确
        checkGetAccessTokenRo(getAccessTokenRo);
        // 刷新AccessToken
        refreshAccessToken(getAccessTokenRo, genRefreshAccessTokenTo(authCodeTo, getAccessTokenRo));
        return getAccessTokenRo;
    }

    /**
     * 检查获取AccessToken的结果是否正确
     */
    protected void checkGetAccessTokenRo(final GET_ACCESS_TOKEN_RO getAccessTokenRo) {
        throw new UnsupportedOperationException();
    }

    /**
     * 生成刷新AccessToken的参数
     */
    protected REFRESH_ACCESS_TOKEN_TO genRefreshAccessTokenTo(final AuthCodeTo authCodeTo, final GET_ACCESS_TOKEN_RO getAccessTokenRo) {
        throw new UnsupportedOperationException();
    }

    /**
     * 刷新AccessToken
     */
    @SneakyThrows
    protected void refreshAccessToken(final GET_ACCESS_TOKEN_RO getAccessTokenRo, final REFRESH_ACCESS_TOKEN_TO refreshAccessTokenTo) {
        // 发出刷新AccessToken请求
        REFRESH_ACCESS_TOKEN_RO refreshAccessTokenRo = sendGet(refreshAccessTokenUrl(), refreshAccessTokenTo, REFRESH_ACCESS_TOKEN_RO());
        // 检查刷新AccessToken的结果是否正确
        checkRefreshAccessTokenRo(refreshAccessTokenRo);
        // 更新AccessToken
        updateAccessToken(getAccessTokenRo, refreshAccessTokenRo);
    }

    /**
     * 检查刷新AccessToken的结果是否正确
     */
    protected void checkRefreshAccessTokenRo(final REFRESH_ACCESS_TOKEN_RO refreshAccessTokenRo) {
        throw new UnsupportedOperationException();
    }

    /**
     * 更新AccessToken
     */
    protected void updateAccessToken(final GET_ACCESS_TOKEN_RO getAccessTokenRo, final REFRESH_ACCESS_TOKEN_RO refreshAccessTokenRo) {
        throw new UnsupportedOperationException();
    }

    /**
     * 生成获取用户信息的参数
     */
    protected abstract GET_USER_INFO_TO genGetUserInfoTo(GET_ACCESS_TOKEN_RO getAccessTokenRo);

    /**
     * 获取用户信息
     */
    protected OrpUserInfoRa getUserInfo(final GET_USER_INFO_TO getUserInfoTo) {
        // 发出获取用户信息请求
        final GET_USER_INFO_RO getUserInfoRo = sendGetUserInfo(getUserInfoTo);
        // 检查获取AccessToken的结果是否正确
        checkGetUserInfoRo(getUserInfoRo);
        // 转换不同策略的用户信息为统一的用户信息
        return convertUserInfo(getUserInfoRo);
    }

    /**
     * 发送获取用户信息的请求
     */
    protected GET_USER_INFO_RO sendGetUserInfo(final GET_USER_INFO_TO getUserInfoTo) {
        return sendGet(getUserInfoUrl(), getUserInfoTo, GET_USER_INFO_RO());
    }

    /**
     * 检查获取用户信息的结果是否正确
     */
    protected abstract void checkGetUserInfoRo(GET_USER_INFO_RO getUserInfoRo);

    /**
     * 转换不同策略的用户信息为统一的用户信息
     */
    protected OrpUserInfoRa convertUserInfo(final GET_USER_INFO_RO getUserInfoRo) {
        return _mapperFactory.getMapperFacade().map(getUserInfoRo, OrpUserInfoRa.class);
    }

    /**
     * 设置Token信息
     */
    protected void setTokenInfo(final OrpUserInfoRa userInfo, final GET_ACCESS_TOKEN_RO getAccessTokenRo) {
    }

    /**
     * 发送Get请求
     */
    protected <T> T sendGet(final String url, final Object requestPamams, final Class<T> clazz) {
        @SuppressWarnings("unchecked")
        final Map<String, Object> map = _mapperFactory.getMapperFacade().map(requestPamams, Map.class);
        // return _httpClient.getWithJsonResponse(url, map, clazz, "ISO-8859-1");
        return _httpClient.getWithJsonResponse(url, map, clazz);
    }

    /**
     * 发送Get请求
     */
    protected <T> T sendGet(final String url, final Object requestPamams, final Class<T> clazz, final String encoding) {
        @SuppressWarnings("unchecked")
        final Map<String, Object> map = _mapperFactory.getMapperFacade().map(requestPamams, Map.class);
        return _httpClient.getWithJsonResponse(url, map, clazz, encoding);
    }

}
