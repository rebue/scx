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
public class RacAccountEmailTo extends RacAccountBindTo implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    /**
     * 邮箱，绑定时必须传参
     */
    @NotNull(message = "邮箱不能为空")
    private String            email;

}
