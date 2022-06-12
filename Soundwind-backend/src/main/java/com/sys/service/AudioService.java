package com.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.dto.AddCommentDto;
import com.sys.entity.Audio;
import com.sys.po.AudioPo;
import com.sys.vo.audio.AddAudioVo;


import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;


public interface AudioService extends IService<Audio> {
    List findAllAudios();

    AudioPo findById(Integer audioId);

    boolean isAudioIn(Integer audioId);

    BigDecimal findScoreById(Integer audioId);

    List searchByName(String name);

    Audio getRandomAudio();

    List findAllCommentsById(Integer audioId);

    boolean addCommentByUser(AddCommentDto dto);

    String audioDisplay(Integer id);

    void audioDownload(Integer id,HttpServletResponse res);

    boolean deleteAudio(Integer id);

    Integer uploadAudio(AddAudioVo addAudioVo);

    List findByUserId(Integer userId);

    List getAudioLocation();
}
