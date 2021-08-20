package com.github.rebue.scx.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "password")
public class LoginDto {

    private String loginName;

    private String password;

    private String verificationCode;

    private String state;

    private String redirectUri;

}
