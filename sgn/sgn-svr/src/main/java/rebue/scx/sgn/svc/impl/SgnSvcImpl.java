package rebue.scx.sgn.svc.impl;

import java.util.Map;

import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.svc.impl.RedisBaseSvcImpl;
import rebue.scx.sgn.svc.SgnSvc;
import rebue.wheel.turing.SignUtils;

@Slf4j
@Service
public class SgnSvcImpl extends RedisBaseSvcImpl<String> implements SgnSvc {
    protected String REDIS_KEY_PREFIX = "rebue.scx.sgn.signid.";

    @Override
    public boolean verify(final Map<String, ?> paramMap) {
        log.info("验证签名: paramMap-{}", paramMap);

        if (paramMap == null) {
            log.error("验证签名错误: 请求参数为null");
            return false;
        }

        // 获取签名ID
        final String signId = (String) paramMap.get("signId");
        if (StringUtils.isBlank(signId)) {
            log.error("验证签名错误: 请求参数中没有signId");
            return false;
        }

        // 通过签名ID获取签名key
        final String signKey = get(signId);
        if (StringUtils.isBlank(signKey)) {
            log.error("验证签名错误: signId错误");
            return false;
        }

        return SignUtils.verify1(paramMap, signKey);
    }
}
