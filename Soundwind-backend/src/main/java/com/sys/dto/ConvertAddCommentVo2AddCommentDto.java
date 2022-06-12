package com.sys.dto;


import com.framework.utils.BeanConvertUtils;
import com.sys.vo.audio.AddCommentVo;

public class ConvertAddCommentVo2AddCommentDto {

    public static AddCommentDto convert(Integer audioId, AddCommentVo addCommentVo) {
        AddCommentDto addCommentDto = BeanConvertUtils.convertTo(addCommentVo, AddCommentDto::new);
        addCommentDto.setAudioId(audioId);

        return addCommentDto;
    }
}
