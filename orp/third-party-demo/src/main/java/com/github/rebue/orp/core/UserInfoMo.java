package com.github.rebue.orp.core;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 返回的用户信息
 */
@Data
@JsonInclude(Include.NON_NULL)
public class UserInfoMo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     */
    private String            id;

    /**
     * OpenId
     */
    private String            openId;

    /**
     * AccessToken
     */
    private String            accessToken;

    /**
     * IdToken
     */
    private String            idToken;

    /**
     * 授权范围
     */
    private String[]          scopes;

    /**
     * 用户昵称
     */
    private String            nickname;

    /**
     * 用户登录名称
     *
     */
    private String            signInName;
    /**
     * 用户头像
     */
    private String            avatar;
    /**
     * 用户手机
     */
    private String            mobile;

    /**
     * 用户邮箱
     */
    private String            email;

    /**
     * 位置
     */
    private String            location;

    /**
     * 备注
     *
     */
    private String            remark;

    /**
     * 账户编码
     *
     */
    private String            code;

}
