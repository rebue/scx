package rebue.scx.rac.mo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import rebue.robotech.mo.Mo;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;

/**
* 字典项
*
* @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
*/
@JsonInclude(Include.NON_NULL)
public class RacDicItemMo implements Serializable, Mo<String> {
    /**
    * 字典项ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotBlank(groups = ModifyGroup.class, message = "字典项ID不能为空")
    @Length(max = 32, message = "字典项ID的长度不能大于32")
    private String id;

    /**
    * 字典ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotBlank(groups = AddGroup.class, message = "字典ID不能为空")
    @Length(max = 32, message = "字典ID的长度不能大于32")
    private String dicId;

    /**
    * 字典项名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotBlank(groups = AddGroup.class, message = "字典项名称不能为空")
    @Length(max = 32, message = "字典项名称的长度不能大于32")
    private String name;

    /**
    * 字典备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Length(max = 50, message = "字典备注的长度不能大于50")
    private String remark;

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
    *
    * 字典
    *
    * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
    */
    @Getter
    @Setter
    private RacDicMo dic;

    /**
    * 字典项ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public String getId() {
        return id;
    }

    /**
    * 字典项ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setId(String id) {
        this.id = id;
    }

    /**
    * 字典ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public String getDicId() {
        return dicId;
    }

    /**
    * 字典ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    /**
    * 字典项名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public String getName() {
        return name;
    }

    /**
    * 字典项名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
    * 字典备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public String getRemark() {
        return remark;
    }

    /**
    * 字典备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dicId=").append(dicId);
        sb.append(", name=").append(name);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RacDicItemMo other = (RacDicItemMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        ;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    /**
    * 获取ID的类型
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Override
    public String getIdType() {
        return "String";
    }
}