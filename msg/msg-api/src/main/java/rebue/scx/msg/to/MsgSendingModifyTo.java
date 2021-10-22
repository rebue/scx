package rebue.scx.msg.to;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/***
 * 消息
 * 
 * @author yuanman
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class MsgSendingModifyTo implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    /**
     * 配置类型ding-talk/wechat-open
     */
    @NotNull(message = "配置类型不能为空")
    private String            configType;
    /**
     * 旧的appKey
     */
    @NotNull(message = "旧的appKey不能为空")
    private String            oldAppKey;
    /**
     * 旧的appSecret
     */
    @NotNull(message = "旧的appSecret不能为空")
    private String            oldAppSecret;
    /**
     * 新的appKey
     */
    @NotNull(message = "新的appKey不能为空")
    private String            newappKey;
    /**
     * 新的appSecret
     */
    @NotNull(message = "新的appSecret不能为空")
    private String            newAppSecret;

}
