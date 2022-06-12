package com.sys.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.entity.AudioHasLabel;
import com.sys.vo.audio.AudioHasLabelVo;

import java.util.List;

public interface AudioHasLabelService extends IService<AudioHasLabel> {
    boolean addLabel(AudioHasLabelVo dto);

    List findAllLabelsById(Integer audioId);

    List findAllAudiosByLabelId(Integer LabelId);
}
