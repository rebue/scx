package rebue.scx.sgn.mo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import rebue.robotech.mo.Mo;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;

/**
 * 签名密钥
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class SgnSecretMo implements Serializable, Mo<String> {

    /**
     * ID 一般会设置为OrgID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = ModifyGroup.class, message = "ID不能为空")
    @Length(max = 128, message = "ID的长度不能大于128")
    private String            id;

    /**
     * 密钥
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "密钥不能为空")
    @Length(max = 128, message = "密钥的长度不能大于128")
    private String            secret;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID 一般会设置为OrgID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getId() {
        return id;
    }

    /**
     * 密钥
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSecret() {
        return secret;
    }

    /**
     * 密钥
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSecret(String secret) {
        this.secret = secret;
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
        sb.append(", secret=").append(secret);
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
        SgnSecretMo other = (SgnSecretMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
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

    /**
     * ID 一般会设置为OrgID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(String id) {
        this.id = id;
    }
}
