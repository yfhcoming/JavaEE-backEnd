package com.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.enums.AppCode;
import com.framework.exception.APIException;
import com.framework.utils.BeanConvertUtils;
import com.sys.entity.AudioHasLabel;
import com.sys.mapper.AudioHasLabelMapper;
import com.sys.po.AudioPo;
import com.sys.po.LabelPo;
import com.sys.service.AudioHasLabelService;
import com.sys.service.AudioService;
import com.sys.service.LabelService;
import com.sys.vo.audio.AudioHasLabelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AudioHasLabelServiceImpl extends ServiceImpl<AudioHasLabelMapper, AudioHasLabel> implements AudioHasLabelService {

    @Autowired
    AudioHasLabelMapper audioHasLabelMapper;

    @Autowired
    AudioService audioService;

    @Autowired
    LabelService labelService;

    public boolean isAudioHasLabelIn(AudioHasLabelVo dto)
    {
        LambdaQueryWrapper<AudioHasLabel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AudioHasLabel::getAudioId, dto.getAudioId())
                .eq(AudioHasLabel::getLabelId,dto.getLabelId());
        Integer integer = audioHasLabelMapper.selectCount(wrapper);
        boolean result = (integer == 0)?false:true;
        return result;
    }

    public boolean addLabel(AudioHasLabelVo dto){

        if(!audioService.isAudioIn(dto.getAudioId())){
            throw new APIException(AppCode.AUDIO_NOT_EXIST, "音频不存在：audioId - " + dto.getAudioId());
        }
        if(!labelService.isLabelIn(dto.getLabelId())){
            throw new APIException(AppCode.LABEL_NOT_EXIST, "标签不存在：labelId - " + dto.getLabelId());
        }

        if(this.isAudioHasLabelIn(dto)){
            throw new APIException(AppCode.AUDIO_HAS_LABEL_HAS_IN, "音频已有该标签：audioId - " + dto.getAudioId()
                    +", labelId - " + dto.getLabelId());
        }

        this.save(BeanConvertUtils.convertTo(dto, AudioHasLabel::new));

        return true;
    }

    public List findAllLabelsById(Integer audioId) {
        if(!audioService.isAudioIn(audioId)){
            throw new APIException(AppCode.AUDIO_NOT_EXIST, "音频不存在：audioId - " + audioId);
        }
        List<LabelPo> labels = audioHasLabelMapper.findAllLabelsById(audioId);
//        if(labels == null || labels.isEmpty()){
//            throw new APIException(AppCode.AUDIO_HAS_LABEL_NOT_IN, "音频无标签：audioId - " + audioId);
//        }
        return labels;
    }

    public List findAllAudiosByLabelId(Integer LabelId){
        if(!labelService.isLabelIn(LabelId)){
            throw new APIException(AppCode.LABEL_NOT_EXIST, "标签不存在：LabelId - " + LabelId);
        }
        List<AudioPo> allAudiosByLabelId = audioHasLabelMapper.findAllAudiosByLabelId(LabelId);
        return allAudiosByLabelId;
    }
}
