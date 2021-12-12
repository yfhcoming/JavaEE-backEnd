package com.javaee.sys.vo.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PhotoVo {
    @NotNull(message = "用户ID不能为空！")
    private Integer userId;

    @NotNull(message = "头像不能为空！")
    private MultipartFile file;
}
