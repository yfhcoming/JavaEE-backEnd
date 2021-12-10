package com.javaee.sys.vo.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class InfoUpdateVo {
    //用户ID
    @NotNull(message = "ID不能为空！")
    private Integer id;
    //新的email
    @NotEmpty(message = "名称不能为空！")
    private String userName;
    //新的telephone
    @NotEmpty(message = "手机不能为空！")
    private String telephone;
}
