package rebue.scx.etl.to;

import java.io.Serializable;

import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rebue.robotech.to.PageTo;

/**
 * 同步策略详情
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class EtlSyncStrategyDetailPageTo extends PageTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 策略ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "策略ID不能为负数")
    private Long              strategyId;

    /**
     * 来源表名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "来源表名称的长度不能大于32")
    private String            srcTableName;

    /**
     * 来源字段名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "来源字段名称的长度不能大于32")
    private String            srcFieldName;

    /**
     * 来源字段类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "来源字段类型的长度不能大于32")
    private String            srcFieldType;

    /**
     * 来源字段长度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "来源字段长度不能为负数")
    private Byte              srcFieldLength;

    /**
     * 来源字段精度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "来源字段精度不能为负数")
    private Byte              srcFieldPrecision;

    /**
     * 目的表名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "目的表名称的长度不能大于32")
    private String            dstTableName;

    /**
     * 目的字段名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "目的字段名称的长度不能大于32")
    private String            dstFieldName;

    /**
     * 目的字段类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "目的字段类型的长度不能大于32")
    private String            dstFieldType;

    /**
     * 目的字段长度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "目的字段长度不能为负数")
    private Byte              dstFieldLength;

    /**
     * 目的字段精度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "目的字段精度不能为负数")
    private Byte              dstFieldPrecision;
}
