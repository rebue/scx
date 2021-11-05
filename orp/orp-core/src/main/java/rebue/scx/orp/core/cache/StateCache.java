package rebue.scx.orp.core.cache;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * state缓存
 * 
 * @author zbz
 *
 */
public class StateCache {
    /**
     * State的Key的前缀
     * 后面跟strategy+clientId+state拼接成Key
     * Value为自动生成的State的值
     */
    private static final String       REDIS_KEY_STATE_PREFIX = "rebue.scx.orp.state::";

    /**
     * 过期时间间隔(默认5分钟)
     */
    private final Duration            _expiration;

    private final StringRedisTemplate _stringRedisTemplate;

    public StateCache(final StringRedisTemplate stringRedisTemplate, final Duration expiration) {
        _stringRedisTemplate = stringRedisTemplate;
        _expiration          = expiration;
    }

    /**
     * 根据策略和客户端ID生成key
     */
    private String genKey(final String strategy, final String clientId, final String state) {
        return REDIS_KEY_STATE_PREFIX + strategy + clientId + state;
    }

    public String get(final String strategy, final String clientId, final String state) {
        final String key    = genKey(strategy, clientId, state);
        final String result = _stringRedisTemplate.opsForValue().get(key);
        // FIXME登录成功后 state 未进行删除处理
        // _stringRedisTemplate.delete(key);
        return result;
    }

    public void set(final String strategy, final String clientId, final String state) {
        final String key = genKey(strategy, clientId, state);
        _stringRedisTemplate.opsForValue().set(key, state, _expiration);
    }

}
