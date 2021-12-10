package rebue.scx.msg.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.model.SendMessageV3Request;
import com.baidubce.services.sms.model.SendMessageV3Response;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;
import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.msg.config.SmsConfig;

/**
 * 
 * @author yuanman
 *
 */
@Slf4j
public class SmsUtil {

    /**
     * 发送模板短信-验证码
     * 
     * @param phoneNumber
     * @param code
     * @param smsConfig
     * 
     * @return
     */
    public static Ro<?> sendSMSCode(String phoneNumber, String code, SmsConfig smsConfig) {
        log.info("短信配置详情：" + smsConfig.toString());
        // 是否模拟短信
        if (smsConfig.getSimulation()) {
            log.info("发送模拟短信");
            return new Ro<>(ResultDic.SUCCESS, "发送成功", code);
        }
        switch (smsConfig.getSmsPlatform()) {
        case "jiguang":
            return sendJgSMS(phoneNumber, code, smsConfig);
        case "baidu":
            return sendBaiduSMS(phoneNumber, code, smsConfig);
        default:
            return new Ro<>(ResultDic.FAIL, "发送失败,没有该短信平台类型，请联系管理员");
        }
    }

    private static Ro<?> sendBaiduSMS(String phoneNumber, String code, SmsConfig smsConfig) {
        // 短信模板ID
        String               tempId  = smsConfig.getTempId();
        // 签名id
        String               signId  = smsConfig.getSignId();
        SmsClient            client  = smsConfig.getBaiduSMSClient();
        SendMessageV3Request request = new SendMessageV3Request();
        request.setMobile(phoneNumber);
        request.setSignatureId(signId);
        request.setTemplate(tempId);
        Map<String, String> contentVar = new HashMap<>();
        contentVar.put("code", code);
        request.setContentVar(contentVar);
        request.setClientToken(UUID.randomUUID().toString());
        SendMessageV3Response response = client.sendMessage(request);
        // 解析请求响应 response.isSuccess()为true 表示成功
        if (response != null && response.isSuccess()) {
            // submit success
            return new Ro<>(ResultDic.SUCCESS, "发送成功,请注意接收手机短信");
        }
        else {
            // fail
            return new Ro<>(ResultDic.FAIL, "发送失败");
        }
    }

    /**
     * 发送极光模板短信
     * 
     */
    private static Ro<?> sendJgSMS(String phoneNumber, String code, SmsConfig smsConfig) {
        // 短信模板ID
        int       tempId    = Integer.parseInt(smsConfig.getTempId());
        // 签名id
        int       signId    = Integer.parseInt(smsConfig.getSignId());
        SMSClient smsClient = smsConfig.getJgSMSClient();
        try {
            // 构建发送短信
            SMSPayload    payload = SMSPayload.newBuilder()
                    .setMobileNumber(phoneNumber) // 手机号码
                    .setTempId(tempId) // 短信模板ID
                    .addTempPara("code", code) // key模板参数value
                    .setSignId(signId)// 签名id
                    .build();
            // 发送短信 会返回msg_id
            SendSMSResult res     = null;
            res = smsClient.sendTemplateSMS(payload);
            // 指向保存短信发送记录业务逻辑 可以直接扔到MQ
            /**
             * 第一个参数极光返回的消息id
             * 第二个发送的手机号
             * 第三个发送内容
             * 第四个发送时间
             * 保存到DB
             */
            if (res != null && res.getMessageId() != null) {
                // 执行业务/
                System.out.println(res);
                return new Ro<>(ResultDic.SUCCESS, "发送成功,请注意接收手机短信");
            }
            else {
                return new Ro<>(ResultDic.FAIL, "发送失败");
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return new Ro<>(ResultDic.FAIL, "发送失败");
    }

    /**
     * 发送模板短信-取快递
     *
     * @param phoneNumber 手机号
     * @param name        名字
     * @param address     地址
     */
    public void sendSMSOther(String name, String address, String phoneNumber) {
        try {
            SMSPayload payload = SMSPayload.newBuilder()
                    .setMobileNumber(phoneNumber) // 手机号码
                    .setTempId(1) // 短信模板ID 需要自行申请
                    .addTempPara("name", name) // key模板参数value：参数值 尊敬的{{name}}，您的快递到{{address}}，请速来取一下。
                    .addTempPara("address", address) // key模板参数value：参数值 尊敬的{{name}}，您的快递到{{address}}，请速来取一下。
                    .setSignId(1)// 签名id 需要自行申请审核。个人也可以申请
                    .build();

            // 发送短信
            SendSMSResult res = new SMSClient(null, null).sendTemplateSMS(payload);
            // 执行业务/
            // 指向保存短信发送记录业务逻辑 可以直接扔到MQ
            /**
             * 第一个参数极光返回的消息id
             * 第二个发送的手机号
             * 第三个发送内容
             * 第四个发送时间
             * 保存到DB
             */
            // insertSendSmsLog(res.getMessageId(),phoneNumber,code,0,System.currentTimeMillis()/1000);
            // 执行业务/
            System.out.println(res);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * SHA1加密
     *
     * @param strSrc 明文
     *
     * @return 加密之后的密文
     */
    public static String encrypt(String strSrc) {
        MessageDigest md     = null;
        String        strDes = null;
        byte[]        bt     = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-1");// 将此换成SHA-1、SHA-512、SHA-384等参数
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    /**
     * byte数组转换为16进制字符串
     *
     * @param bts 数据源
     *
     * @return 16进制字符串
     */
    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * 延签判断是否是极光回调
     *
     * @param signature
     * @param nonce
     * @param timestamp
     */
    public Boolean checkSign(String signature, String nonce, String timestamp) {
        // 加密进行比对
        String str           = String.format("appKey=%s&appMasterSecret=%s&nonce=%s×tamp=%s",
                "appKey", "masterSecret", nonce, timestamp);
        String new_signature = encrypt(str);
        if (signature.equals(new_signature)) {
            return true;
        }
        return false;
    }
}