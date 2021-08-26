package rebue.scx.cap.ra;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;
import rebue.scx.cap.mo.CaptchaVO;

/**
 * 带有CaptchaVO内容的Ra
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CaptchaVORa implements Serializable {
    private static final long serialVersionUID = 1L;

    private CaptchaVO         dataVo;

}