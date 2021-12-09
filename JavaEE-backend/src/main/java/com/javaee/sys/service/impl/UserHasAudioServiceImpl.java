package com.javaee.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.javaee.framework.enums.AppCode;
import com.javaee.framework.exception.APIException;
import com.javaee.framework.utils.QiNiuUtils;
import com.javaee.sys.entity.Audio;
import com.javaee.sys.entity.UserHasAudio;
import com.javaee.sys.mapper.AudioMapper;
import com.javaee.sys.mapper.UserHasAudioMapper;
import com.javaee.sys.service.UserHasAudioService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaee.sys.vo.audio.AddAudioVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author T12
 * @since 2021-12-09
 */
@Service
public class UserHasAudioServiceImpl extends ServiceImpl<UserHasAudioMapper, UserHasAudio> implements UserHasAudioService {

    @Autowired
    UserHasAudioMapper userHasAudioMapper;

    @Autowired
    AudioMapper audioMapper;

    @Override
    public boolean uploadAudio(AddAudioVo addAudioVo)
    {
        String url;
        try {
            InputStream fileInputStream= addAudioVo.getMultipartFile().getInputStream();
            url=QiNiuUtils.upLoad(fileInputStream, addAudioVo.getName());
        } catch (IOException e) {
            throw new APIException(AppCode.FILE_UPLOAD_FAIL);
        }
        Audio audio=new Audio();
        audio.setName(addAudioVo.getName());
        audio.setQiniuLocation(url);
        try
        {
            audioMapper.insert(audio);
            LambdaQueryWrapper<Audio> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(Audio::getQiniuLocation,url);
            Integer id=audioMapper.selectOne(wrapper).getAudioId();
            UserHasAudio userHasAudio=new UserHasAudio();
            userHasAudio.setAudioId(id);
            userHasAudio.setUserId(addAudioVo.getId());
            userHasAudioMapper.insert(userHasAudio);
            return true;
        }catch (Exception e){
            throw new APIException("音频插入数据库失败");
        }
    }

}
