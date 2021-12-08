package com.javaee.sys.service;

import com.javaee.sys.entity.AudioHasLabel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaee.sys.vo.audio.AudioHasLabelVo;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author T12
 * @since 2021-12-07
 */
public interface AudioHasLabelService extends IService<AudioHasLabel> {
    boolean addLabel(AudioHasLabelVo dto);

    List findAllLabelsById(Integer audioId);

    List findAllAudiosByLabelId(Integer LabelId);
}
