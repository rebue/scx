/*
 * Copyright © 2018 anji-plus
 * http://www.anji-plus.com
 * All rights reserved.
 */
package rebue.scx.cap.svc;

import java.util.Collection;
import java.util.Set;

/**
 * 验证码缓存接口
 *
 */
public interface CaptchaCacheService {

    void set(String key, String value, long expiresInSeconds);

    boolean exists(String key);

    void delete(String key);

    void delete(Collection<String> keys);

    String get(String key);

    Set<String> keys(String pattern);

    /**
     * 获取过期时间
     * 
     * @param key
     * 
     * @return 单位s
     */
    Long getExpire(String key);

    /**
     * 缓存类型-local/redis/memcache/..
     * 通过java SPI机制，接入方可自定义实现类
     * 
     * @return
     */
    String type();

    /***
     *
     * @param key
     * @param val
     * 
     * @return
     */
    default Long increment(final String key, final long val) {
        return 0L;
    }

}
