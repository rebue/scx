
package rebue.scx.msg.util;

import java.util.Base64;

import com.alibaba.fastjson.JSONObject;

public class EmailUtil {

    public static String sendCustom(final String urlParam, final String title, final String text1, final String dates[])
            throws Throwable {

        // 创建httpClient实例对象
        // final HttpClient httpClient = new HttpClient();
        // // 设置httpClient连接主机服务器超时时间：15000毫秒
        // httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // // 创建post请求方法实例对象
        // final PostMethod postMethod = new PostMethod(urlParam);
        // // 设置post请求超时时间
        // postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        // postMethod.addRequestHeader("Content-Type", "application/json");
        // // getMethod.addRequestHeader("Basic",
        // // "10001275A644497:c3a12648939a4cde9ca2e4a5");
        // final Base64.Decoder decoder = Base64.getDecoder();
        // final Base64.Encoder encoder = Base64.getEncoder();
        // final String text = "10001275A644497:c3a12648939a4cde9ca2e4a5";
        // final byte[] textByte = text.getBytes("UTF-8");
        // // 编码
        // final String encodedText = encoder.encodeToString(textByte);
        // System.out.println(encodedText);
        // // 解码
        // System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
        //
        // postMethod.addRequestHeader("Authorization", "Basic " + encodedText);
        // // 业务
        // final String[] strings = dates;
        // final Map<String, Object> map = new HashMap();
        // map.put("instance", "email");
        // map.put("data", strings);
        // final Map<String, Object> map1 = new HashMap();
        // map1.put("subject", title);
        // map1.put("text", text1);
        // final List<Object> list = new ArrayList<Object>();
        // final List<Object> list2 = new ArrayList<Object>();
        // list.add(map);
        // list2.add(map1);
        // final Map<String, Object> maps = new HashedMap();
        // maps.put("aud_email", list);
        // maps.put("msg_email", list2);
        // final String json = JSONObject.toJSONString(maps);
        //
        // postMethod.setRequestEntity(new StringRequestEntity(json, "text/html", "UTF-8"));
        //
        // httpClient.executeMethod(postMethod);
        //
        // final String result = postMethod.getResponseBodyAsString();
        // postMethod.releaseConnection();
        // return result;
        return null;

    }

    //
    public String sendTemplet(final String urlParam, final String dates[], final String var) {
        // 创建httpClient实例对象
        // final HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        // httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        // final PostMethod postMethod = new PostMethod(urlParam);
        String result = "";
        try {

            // 设置post请求超时时间
            // postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
            // postMethod.addRequestHeader("Content-Type", "application/json");
            // getMethod.addRequestHeader("Basic",
            // "10001275A644497:c3a12648939a4cde9ca2e4a5");
            final Base64.Decoder decoder     = Base64.getDecoder();
            final Base64.Encoder encoder     = Base64.getEncoder();
            final String         text        = "10001275A644497:c3a12648939a4cde9ca2e4a5";
            final byte[]         textByte    = text.getBytes("UTF-8");
            // 编码
            final String         encodedText = encoder.encodeToString(textByte);
            System.out.println(encodedText);
            // 解码
            System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
            // postMethod.addRequestHeader("Authorization", "Basic " + encodedText);
            final String     daString       = JSONObject.toJSONString(dates);
            final String     var1           = JSONObject.toJSONString(var);

            final String     templateString = "{\"aud_email\": [{\"instance\": \"email\",\"data\": " + daString
                    + "}],\"template_id\":10522,\"template_para\":{\"var\":" + var1 + "}}";
            final JSONObject jo             = JSONObject.parseObject(new String(templateString));
            final String     jsonString     = JSONObject.toJSONString(jo);
            System.out.println(jsonString);

            // postMethod.setRequestEntity(new StringRequestEntity(jsonString, "text/html", "UTF-8"));

            // httpClient.executeMethod(postMethod);
            // result = postMethod.getResponseBodyAsString();
            // postMethod.releaseConnection();

        } catch (final Exception e) {
            // TODO: handle exception
            e.printStackTrace();// TODO
        }
        return result;
        // return null;

    }

    // public static void main(final String[] args) throws Throwable {
    //
    // final String urlParam = "https://api.ums.jiguang.cn/v1/sent";
    // final String templateUrl = "https://api.ums.jiguang.cn/v1/template/sent";
    // final String datas[] = { "2530364891@qq.com"
    // };
    // // System.out.println(sendA(urlParam, "测试信息", "测试", datas));
    // // System.out.println(sendB(urlParam));
    // // System.out.println(sendCustom(urlParam, "标题", "内容", datas));
    // // System.out.println(sendTemplet(templateUrl, datas, "556655"));
    //
    // }
}
