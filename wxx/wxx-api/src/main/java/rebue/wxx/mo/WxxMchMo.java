package rebue.wxx.mo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import rebue.robotech.mo.Mo;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;

/**
 * 商户信息(微信支付账户信息)
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class WxxMchMo implements Serializable, Mo<String> {

    /**
     * 商户号(MCH_ID)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = ModifyGroup.class, message = "商户号不能为空")
    @Length(max = 32, message = "商户号的长度不能大于32")
    private String            id;

    /**
     * 商户名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "商户名称不能为空")
    @Length(max = 30, message = "商户名称的长度不能大于30")
    private String            name;

    /**
     * API密钥，签名用的key，在商户平台设置（微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "API密钥，签名用的key，在商户平台设置不能为空")
    @Length(max = 50, message = "API密钥，签名用的key，在商户平台设置的长度不能大于50")
    private String            apiKey;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商户号(MCH_ID)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getId() {
        return id;
    }

    /**
     * 商户号(MCH_ID)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 商户名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     * 商户名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * API密钥，签名用的key，在商户平台设置（微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * API密钥，签名用的key，在商户平台设置（微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
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
        sb.append(", name=").append(name);
        sb.append(", apiKey=").append(apiKey);
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
        WxxMchMo other = (WxxMchMo) that;
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
}
