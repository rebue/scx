/**
 * 重写JwtUserInfoTo 类方便测试模拟前端，如果是Long的实际传的是字符串
 */
package rebue.jwt.test.to;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 签名中储存的用户信息
 */
@Schema(description = "签名中储存的用户信息")
@Data
@JsonInclude(Include.NON_NULL)
public class JwtUserInfoTo {
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private String              userId;
    /**
     * 系统ID
     */
    @Schema(description = "系统ID")
    private String              sysId;
    /**
     * 用户的附加信息
     */
    @Schema(description = "用户的附加信息")
    private Map<String, Object> addition;

}
