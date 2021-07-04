package rebue.wxx.util;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import rebue.wheel.turing.DigestUtils;
import rebue.wxx.to.ex.WxxRespAuthorizeTo;

@Slf4j
public class WxxSignUtils {
    /**
     * 验证微信确认本服务器身份接口的参数的签名是否正确
     * 
     * @return false为校验没有通过，不排除有人在试图模仿微信官方服务器发来信息
     */
    public static boolean verify(final WxxRespAuthorizeTo to, final String appToken) {
        log.info("校验微信确认本服务器身份接口的参数是否正确");
        final String[] array = { appToken, to.getTimestamp(), to.getNonce()
        };
        Arrays.sort(array);
        final String tempSignature = DigestUtils.sha1AsHexStr((array[0] + array[1] + array[2]).getBytes());
        return tempSignature.equals(to.getSignature());
    }
}
