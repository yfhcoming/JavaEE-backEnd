package com.javaee.sys.vo.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PasswordUpdateVo {
    //用户ID
    @NotNull(message = "ID不能为空！")
    private Integer id;
    //旧密码
    @NotEmpty(message = "旧密码不能为空！")
    private String oldPassword;
    //新密码
    @NotEmpty(message = "新密码不能为空！")
    private String newPassword;
}
