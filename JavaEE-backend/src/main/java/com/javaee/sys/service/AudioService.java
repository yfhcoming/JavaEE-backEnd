package com.javaee.sys.service;

import com.javaee.sys.dto.AddCommentDto;
import com.javaee.sys.entity.Audio;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaee.sys.po.AudioPo;
import com.javaee.sys.vo.audio.AddAudioVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
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
