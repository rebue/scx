package rebue.wxx.fro;

import lombok.Data;

@Data
public class WxxGetAccessTokenFro {
    /**
     *
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
