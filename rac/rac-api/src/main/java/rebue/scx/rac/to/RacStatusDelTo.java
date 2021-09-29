package rebue.scx.rac.to;

import java.io.Serializable;

import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 身份
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacStatusDelTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
    * 领域ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String            realmId;
    /**
    * 身份名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Length(max = 20, message = "身份名称的长度不能大于20")
    private String            name;
    /**
    * 首页URL
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Length(max = 100, message = "首页URL的长度不能大于100")
    private String            homeUrl;
    /**
    * 是否启用
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    private Boolean           isEnabled;
    /**
    * 顺序号
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @PositiveOrZero(message = "顺序号不能为负数")
    private Byte              seqNo;
    /**
    * 身份备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Length(max = 50, message = "身份备注的长度不能大于50")
    private String            remark;

}