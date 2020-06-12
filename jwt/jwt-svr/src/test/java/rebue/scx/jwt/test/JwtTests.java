package rebue.scx.jwt.test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.jwt.api.JwtApi;
import rebue.scx.jwt.dic.JwtSignResultDic;
import rebue.scx.jwt.dic.JwtVerifyResultDic;
import rebue.scx.jwt.ro.JwtSignRo;
import rebue.scx.jwt.ro.JwtVerifyRo;
import rebue.scx.jwt.to.JwtSignTo;

@Slf4j
@SpringBootTest
public class JwtTests {

    private final Long   _userId    = 517928358546243583L;

    private final String _sysId     = "rebue-platform";
    private final String _wxOpenId  = "oqTsm0gdD148UcBzibH4JTm2d9q4";
    private final String _wxUnionId = "oqTsm0gdD148UcBzibH4JTm2d9q5";
    private final Long   _orgId     = 517928358546243584L;

    @DubboReference
    private JwtApi       api;

    @Test
    public void test01() throws IOException {
        // JWT签名
        final Map<String, Object> addition = new LinkedHashMap<>();
        addition.put("sysId", _sysId);
        addition.put("wxOpenId", _wxOpenId);
        addition.put("wxUnionId", _wxUnionId);
        addition.put("orgId", _orgId.toString());
        final JwtSignTo to = new JwtSignTo(_userId.toString(), addition);
        log.info("签名: to-{}", to);
        final JwtSignRo signRo = api.sign(to);
        Assertions.assertNotNull(signRo);
        log.info("签名返回: {}", signRo);
        Assertions.assertEquals(JwtSignResultDic.SUCCESS, signRo.getResult());

        log.info("验证签名");
        final JwtVerifyRo veryfyRo = api.verify(signRo.getSign());
        log.info("验证签名返回: {}", veryfyRo);
        Assertions.assertNotNull(veryfyRo);
        System.out.println(veryfyRo);
        Assertions.assertEquals(JwtVerifyResultDic.SUCCESS, veryfyRo.getResult());
    }

}
