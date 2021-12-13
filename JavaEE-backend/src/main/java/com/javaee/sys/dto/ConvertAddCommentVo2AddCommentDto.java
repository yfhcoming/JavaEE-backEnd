package com.javaee.sys.dto;

import com.alibaba.fastjson.JSON;
import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.sys.entity.Comment;
import com.javaee.sys.vo.audio.AddCommentVo;

import java.util.List;

public class ConvertAddCommentVo2AddCommentDto {

    public static AddCommentDto convert(Integer audioId,AddCommentVo addCommentVo) {
        AddCommentDto addCommentDto = BeanConvertUtils.convertTo(addCommentVo, AddCommentDto::new);
        addCommentDto.setAudioId(audioId);

        return addCommentDto;
    }
}
