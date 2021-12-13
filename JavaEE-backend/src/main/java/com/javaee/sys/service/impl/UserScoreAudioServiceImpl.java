package com.javaee.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.javaee.framework.enums.AppCode;
import com.javaee.framework.exception.APIException;
import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.sys.entity.Audio;
import com.javaee.sys.entity.AudioHasLabel;
import com.javaee.sys.entity.UserScoreAudio;
import com.javaee.sys.mapper.UserScoreAudioMapper;
import com.javaee.sys.po.AudioPo;
import com.javaee.sys.service.AudioService;
import com.javaee.sys.service.UserScoreAudioService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaee.sys.service.UserService;
import com.javaee.sys.vo.audio.AudioHasLabelVo;
import com.javaee.sys.vo.user.UserScoreAudioVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author T12
 * @since 2021-12-08
 */
@Service
public class UserScoreAudioServiceImpl extends ServiceImpl<UserScoreAudioMapper, UserScoreAudio> implements UserScoreAudioService {

    @Autowired
    UserScoreAudioMapper userScoreAudioMapper;

    @Autowired
    UserService userService;

    @Autowired
    AudioService audioService;

    public boolean isUserScoreAudioIn(UserScoreAudioVo dto)
    {
        LambdaQueryWrapper<UserScoreAudio> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserScoreAudio::getAudioId, dto.getAudioId())
                .eq(UserScoreAudio::getUserId,dto.getUserId());
        Integer integer = userScoreAudioMapper.selectCount(wrapper);
        boolean result = (integer == 0)?false:true;
        return result;
    }

    public boolean scoreAudio(UserScoreAudioVo dto){


        if(!userService.isUserIn(dto.getUserId())){
            throw new APIException(AppCode.USER_NOT_EXIST, "用户不存在：userId - " + dto.getUserId());
        }
        if(!audioService.isAudioIn(dto.getAudioId())){
            throw new APIException(AppCode.AUDIO_NOT_EXIST, "音频不存在：audioId - " + dto.getAudioId());
        }
        if(this.isUserScoreAudioIn(dto)){
            throw new APIException(AppCode.USER_SCORE_AUDIO_HAS_IN, "用户已为该音频打分：userId - " + dto.getUserId()
                    +", audioId - " + dto.getAudioId());
        }

        this.save(BeanConvertUtils.convertTo(dto, UserScoreAudio::new));

        BigDecimal scoreById = audioService.findScoreById(dto.getAudioId());
        Audio byId = audioService.getById(dto.getAudioId());
        byId.setScore(scoreById);
        audioService.updateById(byId);

        return true;
    }
}
