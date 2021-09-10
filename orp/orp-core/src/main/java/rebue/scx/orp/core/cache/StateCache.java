package rebue.scx.orp.core.cache;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

public class StateCache {
    /**
     * State的Key的前缀
     * 后面跟clientId拼接成Key
     * Value为自动生成的State的值
     */
    private static final String REDIS_KEY_STATE_PREFIX = "rebue.scx.orp.state::";

    /**
     * 过期时间间隔(默认5分钟)
     */
    private Duration            _expiration;

    private StringRedisTemplate _stringRedisTemplate;

    public StateCache(StringRedisTemplate stringRedisTemplate, Duration expiration) {
        _stringRedisTemplate = stringRedisTemplate;
        _expiration          = expiration;
    }

    public String get(String clientId) {
        String key = REDIS_KEY_STATE_PREFIX + clientId;
        String result= _stringRedisTemplate.opsForValue().get(key);
        _stringRedisTemplate.delete(key);
        return result;
    }

    public void set(String clientId, String state) {
        String key = REDIS_KEY_STATE_PREFIX + clientId;
        _stringRedisTemplate.opsForValue().set(key, state, _expiration);
    }

}
