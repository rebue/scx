//package rebue.scx.cap.config;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RedisConfig {
//
//    public static RedisConfig self;
//
//    @PostConstruct
//    void Init() {
//        self = this;
//    }
//
//    StringRedisTemplate getRedis() {
//        return stringRedisTemplate;
//    }
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//}
