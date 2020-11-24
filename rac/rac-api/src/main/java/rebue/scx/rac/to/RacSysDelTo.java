package rebue.scx.rac.to;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 系统
 * 
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacSysDelTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 系统名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "系统名称的长度不能大于20")
    private String            name;

    /**
     * 系统备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "系统备注的长度不能大于50")
    private String            remark;

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String            domainId;

    /**
     * 首页路径
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 70, message = "首页路径的长度不能大于70")
    private String            homePath;
}
