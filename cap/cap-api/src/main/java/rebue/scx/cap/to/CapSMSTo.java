package rebue.scx.cap.to;

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
public class CapSMSTo implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    private String            phoneNumber;

}
