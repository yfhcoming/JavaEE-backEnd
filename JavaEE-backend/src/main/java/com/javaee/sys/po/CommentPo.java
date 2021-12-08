package com.javaee.sys.po;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentPo {

    private Integer commentId;

    // 外键
    private Integer userId;

    private String content;

    private LocalDateTime createTime;
}
