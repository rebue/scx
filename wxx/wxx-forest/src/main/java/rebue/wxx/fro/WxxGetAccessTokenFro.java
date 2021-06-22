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
}
