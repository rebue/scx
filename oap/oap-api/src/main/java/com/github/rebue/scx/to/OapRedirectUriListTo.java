package com.github.rebue.scx.to;

import java.io.Serializable;

import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 第三方应用URL
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
public class OapRedirectUriListTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * OAP_APP主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "OAP_APP主键不能为负数")
    private Long              appId;

    /**
     * 允许的重定向URI, 最后一个字符可以是通配符*
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 255, message = "允许的重定向URI,的长度不能大于255")
    private String            redirectUri;

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
}
