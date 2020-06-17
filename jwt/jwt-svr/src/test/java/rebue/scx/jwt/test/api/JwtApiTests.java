package rebue.scx.jwt.test.api;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.jwt.api.JwtApi;
import rebue.scx.jwt.ra.JwtSignRa;
import rebue.scx.jwt.to.JwtSignTo;
import rebue.scx.jwt.to.JwtVerifyTo;

@Slf4j
@SpringBootTest
public class JwtApiTests {

    private final Long _userId = 517928358546243583L;

    private final String _sysId     = "rebue-platform";
    private final String _wxOpenId  = "oqTsm0gdD148UcBzibH4JTm2d9q4";
    private final String _wxUnionId = "oqTsm0gdD148UcBzibH4JTm2d9q5";
    private final Long   _orgId     = 517928358546243584L;

    @DubboReference
    private JwtApi api;

    @Test
    public void test01() throws IOException, InterruptedException {
        // JWT签名
        final Map<String, Object> addition = new LinkedHashMap<>();
        addition.put("sysId", _sysId);
        addition.put("wxOpenId", _wxOpenId);
        addition.put("wxUnionId", _wxUnionId);
        addition.put("orgId", _orgId.toString());
        final JwtSignTo to = new JwtSignTo(_userId.toString(), addition);
        log.info("签名: to-{}", to);
        final Ro<JwtSignRa> signRo = api.sign(to);
        Assertions.assertNotNull(signRo);
        log.info("签名返回: {}", signRo);
        Assertions.assertEquals(ResultDic.SUCCESS, signRo.getResult());

        log.info("验证签名");
        Ro<JwtSignRa> veryfyRo = api.verify(new JwtVerifyTo(_userId.toString(), signRo.getAddition().getSign()));
        log.info("验证签名返回: {}", veryfyRo);
        Assertions.assertNotNull(veryfyRo);
        System.out.println(veryfyRo);
        Assertions.assertEquals(ResultDic.SUCCESS, veryfyRo.getResult());

        Thread.sleep(3000);
        veryfyRo = api.verify(new JwtVerifyTo(_userId.toString(), signRo.getAddition().getSign()));
        log.info("验证签名返回: {}", veryfyRo);
        Assertions.assertNotNull(veryfyRo);
        System.out.println(veryfyRo);
        Assertions.assertEquals(ResultDic.FAIL, veryfyRo.getResult());
    }

}
