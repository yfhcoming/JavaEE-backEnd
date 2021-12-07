package com.javaee.sys.vo.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginVo {
    //登陆时的手机号或邮箱
    @NotEmpty(message = "手机或邮箱不能为空！")
    private String loginKey;
    //密码
    @NotEmpty(message = "密码不能为空！")
    private String password;
}
