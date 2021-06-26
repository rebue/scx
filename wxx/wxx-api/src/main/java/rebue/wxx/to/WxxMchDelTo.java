package rebue.wxx.to;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 商户信息(微信支付账户信息)
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class WxxMchDelTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商户名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 30, message = "商户名称的长度不能大于30")
    private String            name;

    /**
     * API密钥，签名用的key，在商户平台设置（微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "API密钥，签名用的key，在商户平台设置的长度不能大于50")
    private String            apiKey;
}
