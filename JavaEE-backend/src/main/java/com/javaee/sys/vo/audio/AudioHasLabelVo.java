package com.javaee.sys.vo.audio;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AudioHasLabelVo {

    @NotNull(message = "音频Id不允许为空")
    private Integer audioId;

    @NotNull(message = "标签Id不允许为空")
    private Integer labelId;

    public AudioHasLabelVo(Integer audioId, Integer labelId) {
        this.audioId = audioId;
        this.labelId = labelId;
    }
}
