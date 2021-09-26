package rebue.scx.etl.to;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 数据库连接器
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class EtlConnModifyTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 连接器ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "连接器ID不能为空")
    @PositiveOrZero(message = "连接器ID不能为负数")
    private Long              id;

    /**
     * 数据库连接器名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "数据库连接器名称的长度不能大于32")
    private String            name;

    /**
     * 数据库类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "数据库类型不能为负数")
    private Byte              dbType;

    /**
     * 数据库名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "数据库名称的长度不能大于32")
    private String            dbName;

    /**
     * 主机名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "主机名称的长度不能大于32")
    private String            host;

    /**
     * 端口号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "端口号不能为负数")
    private Short             port;

    /**
     * 用户名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "用户名称的长度不能大于32")
    private String            userName;

    /**
     * 用户密码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "用户密码的长度不能大于32")
    private String            userPswd;

    /**
     * 源备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "源备注的长度不能大于32")
    private String            remark;
}
