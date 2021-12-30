package rebue.scx.rac.to.nacos;

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
public class NacosAddTo implements Serializable {
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
     * 新的appKey
     */
    @NotNull(message = "新的appKey不能为空")
    private String            newAppKey;
    /**
     * 新的appSecret
     */
    @NotNull(message = "新的appSecret不能为空")
    private String            newAppSecret;
    /**
     * 新的name
     */
    @NotNull(message = "新的name不能为空")
    private String            newName;

}
