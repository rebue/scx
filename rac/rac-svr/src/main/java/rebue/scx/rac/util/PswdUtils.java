package rebue.scx.rac.util;

import rebue.wheel.RandomEx;
import rebue.wheel.turing.DigestUtils;

public class PswdUtils {
    /**
     * 随机生成盐值
     */
    public static String randomSalt() {
        return RandomEx.random1(6);
    }

    /**
     * 加盐摘要密码
     *
     * @param pswd 密码(不是明文，而是将明文MD5传过来)
     * @param salt 盐值
     */
    public static String saltPswd(final String pswd, final String salt) {
        return DigestUtils.md5AsHexStr((pswd + salt).toLowerCase().getBytes());
    }
}
