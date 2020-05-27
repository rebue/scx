package rebue.jwt.test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import rebue.jwt.test.to.JwtUserInfoTo;
import rebue.scx.jwt.dic.JwtSignResultDic;
import rebue.scx.jwt.dic.JwtVerifyResultDic;
import rebue.scx.jwt.ro.JwtSignRo;
import rebue.scx.jwt.ro.JwtVerifyRo;
import rebue.wheel.http.HttpClient;
import rebue.wheel.http.impl.OkHttpClientImpl;

public class JwtTests {

    private final String _hostUrl = "http://localhost:9500";
//    private final String _hostUrl = "http://localhost:8080/jwt-svr";
    private final Long _userId = 517928358546243583L;

    private final String _wxOpenId  = "oqTsm0gdD148UcBzibH4JTm2d9q4";
    private final String _wxUnionId = "oqTsm0gdD148UcBzibH4JTm2d9q5";
    private final Long   _orgId     = 517928358546243584L;

    private final ObjectMapper _objectMapper = new ObjectMapper();

    private final HttpClient _httpClient = new OkHttpClientImpl();

    @Test
    public void test01() throws IOException {
        // JWT签名
        String              url = _hostUrl + "/jwt/sign";
        final JwtUserInfoTo to  = new JwtUserInfoTo();
        to.setUserId(_userId.toString());
        to.setSysId("damai-admin");
        final Map<String, Object> addition = new LinkedHashMap<>();
        addition.put("wxOpenId", _wxOpenId);
        addition.put("wxUnionId", _wxUnionId);
        addition.put("orgId", _orgId.toString());
        to.setAddition(addition);
        final String    jsonParams = _objectMapper.writeValueAsString(to);
        final JwtSignRo signRo     = _objectMapper.readValue(_httpClient.postByJsonParams(url, jsonParams),
                JwtSignRo.class);
        Assert.assertNotNull(signRo);
        System.out.println(signRo);
        Assert.assertEquals(JwtSignResultDic.SUCCESS, signRo.getResult());
        // 验证JWT签名
        url = _hostUrl + "/jwt/verify?toVerifySign=" + signRo.getSign();
        final JwtVerifyRo veryfyRo = _objectMapper.readValue(_httpClient.get(url), JwtVerifyRo.class);
        Assert.assertNotNull(veryfyRo);
        System.out.println(veryfyRo);
        Assert.assertEquals(JwtVerifyResultDic.SUCCESS, veryfyRo.getResult());
    }

}
