package com.javaee.sys.vo.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class UserInfoVo {
    //用户邮箱
    @NotEmpty(message = "邮箱不能为空！")
    private String email;
    //用户手机
    @NotEmpty(message = "手机不能为空！")
    private String telephone;

    @NotEmpty(message = "用户名称不能为空！")
    private String userName;

    @NotEmpty(message = "信息创建时间不能为空！")
    private LocalDateTime createTime;

    private String photoUrl;
}
