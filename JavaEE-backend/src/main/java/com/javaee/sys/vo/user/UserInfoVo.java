package com.javaee.sys.vo.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserInfoVo {
    //用户邮箱
    @NotEmpty(message = "邮箱不能为空！")
    private String email;
    //用户手机
    @NotEmpty(message = "手机不能为空！")
    private String telephone;
}
