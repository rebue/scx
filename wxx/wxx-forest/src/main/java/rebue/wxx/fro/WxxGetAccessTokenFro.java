package rebue.wxx.fro;

import lombok.Data;

/**
 * 获取Access Token的返回结果
 *
 * @author zbz
 *
 */
@Data
public class WxxGetAccessTokenFro {
    /**
     * Access Token
     */
    private String  access_token;
    /**
     * 多少秒后失效
     */
    private Integer expires_in;
    /**
     * 错误代码
     */
    private Integer errcode;
    /**
     * 错误的提示消息
     */
    private String  errmsg;

}
