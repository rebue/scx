package com.github.rebue.third.party.demo.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import com.nimbusds.oauth2.sdk.util.StringUtils;

public class UrlUtil {
    /**
     * 编码
     * 
     * @param str
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * URL 解码
     *
     * @return str
     */
    private static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将map转换成url参数("a=111&amp;b=222&amp;c=333")
     * 所有参数的值都进行URLEncoder的UTF-8编码
     * 
     */
    public static String map2UrlParams(final Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        final StringJoiner sj = new StringJoiner("&");
        map.forEach((key, value) -> {
            if (value == null) return;
            if (value instanceof List) {
                ((List<?>) value).forEach(item -> {
                    String valueStr = valueToString(item);
                    if (valueStr == null) return;
                    sj.add(key + "=" + valueStr);
                });
            }
            else {
                String valueStr = valueToString(value);
                if (valueStr == null) return;
                sj.add(key + "=" + valueStr);
            }
        });
        return sj.toString();

    }

    /**
     * 将参数的值转换为字符串
     */
    private static String valueToString(Object value) {
        if (value == null) return null;
        String valueStr = null;
        if (value instanceof LocalDate) {
            valueStr = LocalDateTimeUtils.formatDate((LocalDate) value);
        }
        else if (value instanceof LocalDateTime) {
            valueStr = LocalDateTimeUtils.formatDateTime((LocalDateTime) value);
        }
        else {
            valueStr = value.toString();
        }
        if (StringUtils.isBlank(valueStr)) return null;
        try {
            return URLEncoder.encode(valueStr, "utf-8");
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException("不支持utf-8编码(不可能的)");
        }
    }
}
