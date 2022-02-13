package rebue.scx.etl.to;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 同步策略
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class EtlSyncStrategyAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 策略名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "策略名称不能为空")
    @Length(max = 32, message = "策略名称的长度不能大于32")
    private String            name;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "是否启用不能为空")
    private Boolean           isEnabled;

    /**
     * 来源的连接器ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "来源的连接器ID不能为空")
    @PositiveOrZero(message = "来源的连接器ID不能为负数")
    private Long              srcConnId;

    /**
     * 来源名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "来源名称不能为空")
    @Length(max = 32, message = "来源名称的长度不能大于32")
    private String            srcName;

    /**
     * 目的的连接器ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "目的的连接器ID不能为空")
    @PositiveOrZero(message = "目的的连接器ID不能为负数")
    private Long              dstConnId;

    /**
     * 目的名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "目的名称不能为空")
    @Length(max = 32, message = "目的名称的长度不能大于32")
    private String            dstName;

    /**
     * 策略备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "策略备注的长度不能大于32")
    private String            remark;

    /**
     * 来源表名称(Map的josn字符串，key为表名，value为源表字段list集合)
     */
    private String            srcTableNames;

    /**
     * 目的表名称(Map的josn字符串，key为表名，value为目的表字段list集合)
     */
    private String            dstTableNames;

    /**
     * 确保源表一一对应目的表(Map的josn字符串，key为源表名，value为目的表名)
     */
    private String            srcDstMap;
}
