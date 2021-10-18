package rebue.scx.rac.to.ex;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接受Post请求只有一个参数的实体
 * 
 * @author yuanman
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PostParameterTo implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long              id;

}
