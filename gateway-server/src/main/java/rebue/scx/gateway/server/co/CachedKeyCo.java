package rebue.scx.gateway.server.co;

/**
 * 缓存Key常量
 * 可以通过key将缓存在exchange的Attributes的Map中值取出来
 *
 * @author zbz
 *
 */
public interface CachedKeyCo {
    /**
     * 请求开始时间(值为LocalDateTime)
     */
    String REQUEST_TIME        = "@requestDateTime";
    /**
     * 会话ID(值为Long)
     */
    String SESSION_ID          = "@sessionId";
    /**
     * 请求Body中的参数(值为Map<String,Object>)
     */
    String REQUEST_BODY_PARAMS = "@requestBodyParams";
    /**
     * 请求Body(值为String)
     */
    String REQUEST_BODY_STRING = "@requestBody";
}
