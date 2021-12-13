package com.javaee.sys.vo.audio;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddCommentVo {

    @NotNull(message = "用户Id不允许为空")
    private Integer userId;

    @NotBlank(message = "评论内容不允许为空")
    private String content;

}
