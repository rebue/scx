package rebue.wxx.fro;

import lombok.Data;

/**
 * 发送模板类的消息的返回结果
 *
 * @author zbz
 *
 */
@Data
public class WxxMessageTemplateSendFro {

    /**
     * 错误代码
     */
    private Integer errcode;
    /**
     * 错误的提示消息
     */
    private String  errmsg;
    /**
     * 消息ID
     */
    private String  msgid;
}