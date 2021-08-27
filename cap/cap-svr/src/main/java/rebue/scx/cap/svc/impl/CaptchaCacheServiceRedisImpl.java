package rebue.scx.cap.svc.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import rebue.scx.cap.svc.CaptchaCacheService;

/**
 * 对于分布式部署的应用，我们建议应用自己实现CaptchaCacheService，比如用Redis，参考service/spring-boot代码示例。
 * 如果应用是单点的，也没有使用redis，那默认使用内存。
 * 内存缓存只适合单节点部署的应用，否则验证码生产与验证在节点之间信息不同步，导致失败。
 *
 * ☆☆☆ SPI： 在resources目录新建META-INF.services文件夹(两层)，参考当前服务resources。
 * 
 * @Title: 使用redis缓存
 */
@Component
public class CaptchaCacheServiceRedisImpl implements CaptchaCacheService {

    @Override
    public String type() {
        return "redis";
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void set(final String key, final String value, final long expiresInSeconds) {
        stringRedisTemplate.opsForValue().set(key, value, expiresInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public boolean exists(final String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public void delete(final String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public String get(final String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Long increment(final String key, final long val) {
        return stringRedisTemplate.opsForValue().increment(key, val);
    }
}
