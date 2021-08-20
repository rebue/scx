package rebue.scx.rac.to.ex;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 权限菜单
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacPermMenusAddTo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 应用ID
     *
     */
    @Length(max = 32, message = "应用ID的长度不能大于32")
    private String            appId;

    /**
     * 权限ID
     *
     */
    @NotNull(message = "权限ID不能为空")
    @PositiveOrZero(message = "权限ID不能为负数")
    private Long              permId;

    /**
     * 菜单URN
     *
     */
    @NotBlank(message = "菜单URN不能为空")
    @Length(max = 100, message = "菜单URN的长度不能大于100")
    private List<String>            menuUrns;
}
