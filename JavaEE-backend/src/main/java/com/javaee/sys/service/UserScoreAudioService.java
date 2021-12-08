package com.javaee.sys.service;

import com.javaee.sys.entity.UserScoreAudio;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaee.sys.vo.user.UserScoreAudioVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author T12
 * @since 2021-12-08
 */
public interface UserScoreAudioService extends IService<UserScoreAudio> {
    boolean scoreAudio(UserScoreAudioVo dto);
}
