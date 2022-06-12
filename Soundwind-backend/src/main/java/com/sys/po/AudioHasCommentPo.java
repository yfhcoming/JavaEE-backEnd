package com.sys.po;

import lombok.Data;

@Data
public class AudioHasCommentPo {

    private Integer audioId;

    private Integer commentId;

    public AudioHasCommentPo(Integer audioId, Integer commentId) {
        this.audioId = audioId;
        this.commentId = commentId;
    }
}
