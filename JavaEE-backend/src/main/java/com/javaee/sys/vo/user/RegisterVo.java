package com.javaee.sys.vo.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterVo {
    //注册时的手机号
    @NotEmpty(message = "手机号不能为空！")
    private String telephone;
    //注册时的邮箱
    @NotEmpty(message = "邮箱不能为空！")
    private String email;
    //注册密码
    @NotEmpty(message = "密码不能为空！")
    private String password;
    //验证码
    @NotEmpty(message = "验证码不能为空！")
    private String code;
}
