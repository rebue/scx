package com.github.rebue.scx.to;

import java.io.Serializable;
import javax.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * 三方应用账户信息
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class OapGrantOneTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * rac_account主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "rac_account主键不能为负数")
    private Long              accountId;

    /**
     * oidc access token
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "oidc的长度不能大于50")
    private String            accessToken;

    /**
     * oidc refresh token
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "oidc的长度不能大于50")
    private String            refreshToken;

    /**
     * 过期时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "过期时间不能为负数")
    private Long              expireTimestamp;

    /**
     * 创建时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "创建时间不能为负数")
    private Long              createTimestamp;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 255, message = "的长度不能大于255")
    private String            accessTokenJson;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 255, message = "的长度不能大于255")
    private String            refreshTokenJson;
}
