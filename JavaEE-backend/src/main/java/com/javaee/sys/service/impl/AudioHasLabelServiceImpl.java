package com.javaee.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.javaee.framework.enums.AppCode;
import com.javaee.framework.exception.APIException;
import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.sys.entity.AudioHasLabel;
import com.javaee.sys.entity.CollectionHasAudio;
import com.javaee.sys.entity.Label;
import com.javaee.sys.mapper.AudioHasLabelMapper;
import com.javaee.sys.po.AudioPo;
import com.javaee.sys.po.LabelPo;
import com.javaee.sys.service.AudioHasLabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaee.sys.service.AudioService;
import com.javaee.sys.service.LabelService;
import com.javaee.sys.vo.audio.AudioHasLabelVo;
import com.javaee.sys.vo.collection.CollectionHasAudioVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author T12
 * @since 2021-12-07
 */
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
