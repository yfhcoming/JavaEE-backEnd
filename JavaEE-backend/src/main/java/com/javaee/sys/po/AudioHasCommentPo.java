package com.javaee.sys.po;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AudioHasCommentPo {

    private Integer audioId;

    private Integer commentId;

    public AudioHasCommentPo(Integer audioId, Integer commentId) {
        this.audioId = audioId;
        this.commentId = commentId;
    }
}
