package rebue.scx.cap.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 
 * @author yuanman
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class CapBaseTo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 图形验证码
     */
    private String            captchaVerification;
}
