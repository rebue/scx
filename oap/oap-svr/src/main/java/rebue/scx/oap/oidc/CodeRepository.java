package rebue.scx.oap.oidc;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;

import rebue.scx.oap.dto.CodeValue;

@Repository
public class CodeRepository {

    private static final String CODE_PREFIX = "cp:";

    @Autowired
    // @Reference
    private StringRedisTemplate stringRedisTemplate;

    public Optional<CodeValue> getAndRemoveCode(String code) {
        String key   = CODE_PREFIX + code;
        String jsStr = stringRedisTemplate.opsForValue().get(key);
        if (jsStr == null) {
            return Optional.empty();
        }
        stringRedisTemplate.delete(key);
        CodeValue value = JSONObject.parseObject(jsStr, CodeValue.class);
        return Optional.ofNullable(value);
    }

    public AuthorizationCode createCode(AuthenticationRequest aRequest, long accountId) {
        return createCode(
                aRequest.getClientID().getValue(),
                aRequest.getScope(),
                accountId);
    }

    public AuthorizationCode createCode(String clientId, Scope scope, long accountId) {
        AuthorizationCode code  = new AuthorizationCode(16);
        CodeValue         cv    = new CodeValue(clientId, scope, accountId);
        String            jsStr = JSONObject.toJSONString(cv);
        stringRedisTemplate.opsForValue().set(CODE_PREFIX + code.getValue(), jsStr, Duration.ofMinutes(5));
        return code;
    }

}
