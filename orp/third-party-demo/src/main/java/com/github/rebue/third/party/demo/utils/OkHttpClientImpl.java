package com.github.rebue.third.party.demo.utils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
@Service
public class OkHttpClientImpl {
    private static final MediaType JSON_MEDIA_TYPE = MediaType.get("application/json; charset=utf-8");
    private static final MediaType XML_MEDIA_TYPE  = MediaType.get("text/xml; charset=utf-8");

    // private static OkHttpClient _client = new OkHttpClient();
    private final OkHttpClient _client;

    // private final ObjectMapper _objejctMapper = JacksonUtils.getObjectMapper();
    public OkHttpClientImpl() {
        _client = new OkHttpClient().newBuilder().hostnameVerifier((hostname, session) -> {
            log.debug("强行返回true 即验证成功");
            return true;
        }).build();
    }

    /**
     * 设置读数据的超时时间（目前专门给微信沙箱测试用）
     *
     * @param minutes 超时时间(分钟)
     */
    public OkHttpClientImpl(final int minutes) {
        _client = new OkHttpClient.Builder().readTimeout(minutes, TimeUnit.MINUTES).build();
    }

    /**
     * 发出GET请求
     *
     * @param url 请求的地址
     */
    public String get(final String url) throws IOException {
        log.debug("发送只有URL的GET请求：{}", url);
        final Request  request  = new Request.Builder().url(url).build();
        final Response response = _client.newCall(request).execute();
        if (response.isSuccessful()) {
            final String msg = response.body().string();
            log.info("接收到response的信息：{}", msg);
            return msg;
        }
        else {
            log.error("服务器返回错误: " + response);
            throw new HttpClientErrorException(HttpStatus.valueOf(response.code()));
        }
    }

    /**
     * 发出POST请求
     *
     * @param url 请求的地址
     * 
     * @return 响应的字符串
     */
    public String post(final String url) throws IOException {
        log.info("发送只有URL的POST请求: {}", url);
        final FormBody.Builder formBodyBuilder = new FormBody.Builder();
        final Request          request         = new Request.Builder().url(url).post(formBodyBuilder.build()).build();
        final Response         response        = _client.newCall(request).execute();
        if (response.isSuccessful()) {
            final String msg = response.body().string();
            log.debug(msg);
            return msg;
        }
        else {
            log.error("服务器返回错误: " + response);
            throw new HttpClientErrorException(HttpStatus.valueOf(response.code()));
        }
    }

    /**
     * 发出POST请求(将Map对象转为请求的FORM参数)
     *
     * @param url           请求的地址
     * @param requestParams 请求的参数
     * 
     * @return 响应的字符串
     */
    public String postByFormParams(final String url, final Map<String, Object> requestParams) throws IOException {
        log.info("发送带FORM_BODY的POST请求: {}", url);
        final FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for (final Map.Entry<String, Object> item : requestParams.entrySet()) {
            formBodyBuilder.add(item.getKey(), item.getValue().toString());
        }
        final Request  request  = new Request.Builder().url(url).post(formBodyBuilder.build()).build();
        final Response response = _client.newCall(request).execute();
        if (response.isSuccessful()) {
            final String msg = response.body().string();
            log.debug(msg);
            return msg;
        }
        else {
            log.error("服务器返回错误: " + response);
            throw new HttpClientErrorException(HttpStatus.valueOf(response.code()));
        }
    }

    /**
     * 发出POST请求(参数为json形式的字符串)
     *
     * @param url        请求的地址
     * @param jsonParams 请求的参数
     * 
     * @return 响应的字符串
     */
    public String postByJsonParams(final String url, final String jsonParams) throws IOException {
        log.info("发送带JSON_BODY的POST请求: {} {}", url, jsonParams);
        final RequestBody body     = RequestBody.create(jsonParams, JSON_MEDIA_TYPE);
        final Request     request  = new Request.Builder().url(url).post(body).build();
        final Response    response = _client.newCall(request).execute();
        if (response.isSuccessful()) {
            final String msg = response.body().string();
            log.debug("接收到response的信息：{}", msg);
            return msg;
        }
        else {
            log.error("服务器返回错误: " + response);
            throw new HttpClientErrorException(HttpStatus.valueOf(response.code()));
        }
    }

    /**
     * 发出POST请求(参数为json形式的字符串)
     *
     * @param url           请求的地址
     * @param requestParams 请求的参数(一个Bean或Map&lt;String,Object$gt;)
     * 
     * @return 响应的字符串
     */
    public String postByJsonParams(final String url, final Object requestParams) throws IOException {
        return null;
        // return postByJsonParams(url, _objejctMapper.writeValueAsString(requestParams));
    }

}
