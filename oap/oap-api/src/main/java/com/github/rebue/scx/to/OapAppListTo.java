package com.github.rebue.scx.to;

import java.io.Serializable;
import javax.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 第三方应用
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
public class OapAppListTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * rac_app主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "rac_app主键的长度不能大于32")
    private String            appId;

    /**
     * oidc client id
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 255, message = "oidc的长度不能大于255")
    private String            clientId;

    /**
     * oidc secret
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 255, message = "oidc的长度不能大于255")
    private String            secret;

    /**
     * 建立时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "建立时间戳不能为负数")
    private Long              createTimestamp;

    /**
     * 修改时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "修改时间戳不能为负数")
    private Long              updateTimestamp;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean           isEnabled;

    /**
     * 对象ID(文件ID)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long              objId;
}
