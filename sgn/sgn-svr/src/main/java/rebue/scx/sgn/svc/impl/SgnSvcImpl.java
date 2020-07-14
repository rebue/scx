package rebue.scx.sgn.svc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import rebue.robotech.ro.Ro;
import rebue.robotech.svc.impl.RedisBaseSvcImpl;
import rebue.scx.sgn.svc.SgnSvc;

@Service
public class SgnSvcImpl extends RedisBaseSvcImpl<String> implements SgnSvc {
    protected String REDIS_KEY_PREFIX = "rebue.scx.sgn.signid.";

    @Override
    public Ro<?> verify(final Map<String, ?> paramMap) {
//        // 获取签名ID
//        final String signId = (String) paramMap.get("signId");
//        if (StringUtils.isBlank(signId)) {
//            return new Ro<>(ResultDic.PARAM_ERROR, "验证签名错误: 请求参数中没有signId");
//        }
//
//        // 通过签名ID获取签名key
//        final String signKey = get(signId);
//        if (StringUtils.isBlank(signKey)) {
//            return new Ro<>(ResultDic.WARN, "验证签名错误: signId错误");
//        }
//
//        if (SignUtils.verify1(paramMap, signKey)) {
//            return new Ro<>(ResultDic.SUCCESS, "验证签名正确");
//        } else {
//            return new Ro<>(ResultDic.WARN, "验证签名错误: 签名不正确");
//        }
        return null;
    }
}
