package rebue.scx.cap.svc.impl;

import rebue.scx.cap.svc.CaptchaCacheService;
import rebue.scx.cap.util.CacheUtil;

/**
 * 对于分布式部署的应用，我们建议应用自己实现CaptchaCacheService，比如用Redis，参考service/spring-boot代码示例。
 * 如果应用是单点的，也没有使用redis，那默认使用内存。
 * 内存缓存只适合单节点部署的应用，否则验证码生产与验证在节点之间信息不同步，导致失败。
 * @Title: 默认使用内存当缓存
 */
public class CaptchaCacheServiceMemImpl implements CaptchaCacheService {
    @Override
    public void set(final String key, final String value, final long expiresInSeconds) {

        CacheUtil.set(key, value, expiresInSeconds);
    }

    @Override
    public boolean exists(final String key) {
        return CacheUtil.exists(key);
    }

    @Override
    public void delete(final String key) {
        CacheUtil.delete(key);
    }

    @Override
    public String get(final String key) {
        return CacheUtil.get(key);
    }

    @Override
    public Long increment(final String key, final long val) {
        final Long ret = Long.valueOf(CacheUtil.get(key))+val;
        CacheUtil.set(key,ret+"",0);
        return ret;
    }

    @Override
    public String type() {
        return "local";
    }
}
