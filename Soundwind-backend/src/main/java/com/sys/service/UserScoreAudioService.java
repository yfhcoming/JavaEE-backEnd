package com.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.entity.UserScoreAudio;
import com.sys.vo.user.UserScoreAudioVo;


public interface UserScoreAudioService extends IService<UserScoreAudio> {
    boolean scoreAudio(UserScoreAudioVo dto);
}
