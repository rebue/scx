package rebue.scx.orp.to;

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
public class OrpNacosModifyTo implements Serializable {
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
     * 旧的appKey
     */
    @NotNull(message = "旧的appKey不能为空")
    private String            oldAppKey;
    /**
     * 旧的name
     */
    @NotNull(message = "旧的name不能为空")
    private String            oldName;
    /**
     * 旧的appSecret
     */
    private String            oldAppSecret;
    /**
     * 新的appKey
     */
    @NotNull(message = "新的appKey不能为空")
    private String            newAppKey;
    /**
     * 新的name
     */
    @NotNull(message = "新的name不能为空")
    private String            newName;
    /**
     * 新的appSecret
     */
    private String            newAppSecret;

}
