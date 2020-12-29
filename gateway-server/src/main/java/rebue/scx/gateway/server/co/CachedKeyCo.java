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
     * 链接中的请求参数
     */
    public static final String REQUEST_QUERY_PARAMS = "@requestQueryParams";
    /**
     * Body中的请求参数M
     */
    public static final String REQUEST_BODY_PARAMS  = "@requestBodyParams";
}
