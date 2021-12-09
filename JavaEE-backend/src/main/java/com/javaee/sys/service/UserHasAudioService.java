package com.javaee.sys.service;

import com.javaee.sys.entity.UserHasAudio;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaee.sys.vo.audio.AddAudioVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author T12
 * @since 2021-12-09
 */
public interface UserHasAudioService extends IService<UserHasAudio> {

    boolean uploadAudio(AddAudioVo addAudioVo);
}
