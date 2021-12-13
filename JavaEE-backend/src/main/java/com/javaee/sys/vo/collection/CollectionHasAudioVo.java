package com.javaee.sys.vo.collection;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CollectionHasAudioVo {

    @NotNull(message = "收藏夹Id不允许为空")
    private Integer collectionId;

    @NotNull(message = "音频Id不允许为空")
    private Integer audioId;

    public CollectionHasAudioVo(Integer collectionId, Integer audioId) {
        this.collectionId = collectionId;
        this.audioId = audioId;
    }
}
