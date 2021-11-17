package rebue.scx.rac.to.ex;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * nacos配置接收参数**
 * 
 * @author yuanman
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class NacosModifyTo implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    /**
     * 修改的配置key
     */
    @NotNull(message = "修改的配置key不能为空")
    private String            name;
    /**
     * 修改的配置值
     */
    @NotNull(message = "修改的配置值不能为空")
    private String            value;

}
