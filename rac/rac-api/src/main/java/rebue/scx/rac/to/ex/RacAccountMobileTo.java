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
public class RacAccountMobileTo extends RacAccountBindTo implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    /**
     * 手机号，绑定时必须传参
     */
    @NotNull(message = "手机号不能为空")
    private String            mobile;

}
