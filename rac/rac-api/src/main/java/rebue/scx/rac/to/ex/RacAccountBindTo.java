package rebue.scx.rac.to.ex;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author yuanman
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class RacAccountBindTo implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    @NotNull(message = "账户ID不能为空")
    private Long              id;
    /**
     * 校验码
     */
    @NotNull(message = "验证码不能为空")
    private String            code;
    /**
     * 绑定类型（绑定：0，解绑：1）
     */
    @NotNull(message = "绑定类型不能为空")
    private String            bindType;

}
