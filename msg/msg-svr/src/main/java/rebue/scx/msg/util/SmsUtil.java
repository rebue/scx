package rebue.scx.msg.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;

@Component // 交给spring管理
@RefreshScope
public class SmsUtil {
    // 是否模拟短信
    @Value("${msg.sms.simulation:true}")
    public Boolean    simulationSMS = true;
    // masterSecret
    @Value("${msg.sms.masterSecret:e92f7f27dbd27bf0f1157c61}")
    public String     masterSecret  = "e92f7f27dbd27bf0f1157c61";
    // appKey
    @Value("${msg.sms.appKey:823ebe289daa183f863eee73}")
    public String     appKey        = "823ebe289daa183f863eee73";
    // 短信模板ID
    @Value("${msg.sms.tempId:202223}")
    public int        tempId        = 202223;
    // 签名id
    @Value("${msg.sms.signId:20492}")
    public int        signId        = 20492;
    // 初始化发短信客户端
    private SMSClient smsClient     = new SMSClient(masterSecret, appKey);

    /**
     * 发送模板短信-验证码
     * 
     * @param phoneNumber
     * @param code
     * 
     * @return
     */
    public Ro<?> sendSMSCode(String phoneNumber, String code) {
        // 是否模拟短信
        if (simulationSMS) {
            return new Ro<>(ResultDic.SUCCESS, "发送成功", code);
        }
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
            SendSMSResult res = smsClient.sendTemplateSMS(payload);
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
                appKey, masterSecret, nonce, timestamp);
        String new_signature = encrypt(str);
        if (signature.equals(new_signature)) {
            return true;
        }
        return false;
    }
}