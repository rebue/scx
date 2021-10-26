package rebue.scx.msg.to;

import java.io.Serializable;

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
public class MsgSendingMo implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;

}
