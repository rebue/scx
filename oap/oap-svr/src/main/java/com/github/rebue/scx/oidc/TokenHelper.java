package com.github.rebue.scx.oidc;

import com.github.rebue.scx.config.OidcConfig;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;
import com.nimbusds.oauth2.sdk.token.RefreshToken;
import net.minidev.json.JSONObject;

import java.util.Map;

public class TokenHelper {

    public static void main(String[] args) throws Exception
    {
        BearerAccessToken accessToken = new BearerAccessToken(OidcConfig.ACCESS_TOKEN_LIFETIME, new Scope("openid"));
        RefreshToken refreshToken = new RefreshToken();

        JSONObject js = new JSONObject();
        {
            String jsStr = accessToken.toJSONString();
            com.alibaba.fastjson.JSONObject kv = com.alibaba.fastjson.JSONObject.parseObject(jsStr);
            for (Map.Entry<String, Object> item : kv.entrySet()) {
                js.put(item.getKey(), item.getValue());
            }
        }

        BearerAccessToken a1 = BearerAccessToken.parse(js);
        boolean eq = accessToken.equals(a1);
        System.out.println(eq);
    }

}
