package com.github.rebue.scx.oidc;

import java.util.Map;

import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;

import net.minidev.json.JSONObject;

public class TokenHelper {

    public static BearerAccessToken strToAccessToken(String tokenJsStr) throws ParseException
    {
        JSONObject js = new JSONObject();
        {
            com.alibaba.fastjson.JSONObject kv = com.alibaba.fastjson.JSONObject.parseObject(tokenJsStr);
            for (Map.Entry<String, Object> item : kv.entrySet()) {
                js.put(item.getKey(), item.getValue());
            }
        }
        return BearerAccessToken.parse(js);
    }

}
