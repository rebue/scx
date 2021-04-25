package rebue.scx.gateway.server.co;

/**
 * 缓存Key常量
 * 可以通过key将缓存在exchange的Attributes的Map中值取出来
 *
 * @author zbz
 *
 */
public class CachedKeyCo {
    /**
     * 请求开始时间戳(值为Long)
     */
    public static final String REQUEST_TIMESTAMP    = "@requestTimestamp";
    /**
     * 请求ID(值为Long)
     */
    public static final String REQUEST_ID           = "@requestId";
    /**
     * 请求链接中的参数(值为MultiValueMap<String,String>)
     */
    public static final String REQUEST_QUERY_PARAMS = "@requestQueryParams";
    /**
     * 请求Body中的参数(值为Map<String,Object>)
     */
    public static final String REQUEST_BODY_PARAMS  = "@requestBodyParams";
    /**
     * 请求Body(值为String)
     */
    public static final String REQUEST_BODY         = "@requestBody";
}
