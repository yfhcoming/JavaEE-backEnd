package com.javaee.sys.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddCommentDto {
    @NotNull(message = "音频Id不允许为空")
    private Integer audioId;

    @NotNull(message = "用户Id不允许为空")
    private Integer userId;

    @NotBlank(message = "评论内容不允许为空")
    private String content;


}
