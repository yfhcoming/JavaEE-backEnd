package com.javaee.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.javaee.framework.enums.AppCode;
import com.javaee.framework.exception.APIException;
import com.javaee.sys.entity.Audio;
import com.javaee.sys.entity.UserHasAudio;
import com.javaee.sys.mapper.AudioMapper;
import com.javaee.sys.mapper.UserHasAudioMapper;
import com.javaee.sys.service.AudioService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.javaee.framework.utils.QiNiuUtils.deleteFromQN;
import static com.javaee.framework.utils.QiNiuUtils.downLoad;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
@Service
public class AudioServiceImpl extends ServiceImpl<AudioMapper, Audio> implements AudioService {

    @Autowired
    AudioMapper audioMapper;

    @Autowired
    UserHasAudioMapper userHasAudioMapper;

    public boolean isAudioIn(Integer audioId){
        LambdaQueryWrapper<Audio> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Audio::getAudioId, audioId);
        Integer integer = audioMapper.selectCount(wrapper);
        boolean result = (integer == 0)?false:true;
        return result;
    }

    public List findAllAudios()
    {
        LambdaQueryWrapper<Audio> wrapper = new LambdaQueryWrapper<>();
        List<Audio> audioList = audioMapper.selectList(null);
        return audioList;
    };

    public BigDecimal findScoreById(Integer audioId){
        // TODO 验证音频是否有评分
        System.out.println(isAudioIn(audioId));
        if(!isAudioIn(audioId)){
            throw new APIException(AppCode.AUDIO_NOT_EXIST, "音频不存在：audioId - " + audioId);
        }
        List<BigDecimal> allScores = audioMapper.findAllScoresById(audioId);
        BigDecimal average = allScores.stream().map(vo -> ObjectUtils.isEmpty(vo) ? new BigDecimal(0):vo)
                .reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(allScores.size()), 2, BigDecimal.ROUND_HALF_UP);
        return average;
    }

    public List<Audio> searchByName(String name){
        List<Audio> audios = new LambdaQueryChainWrapper<>(audioMapper)
                .like(Audio::getAudioName, name)
                .list();
        return audios;

    }

    public Audio getRandomAudio(){
        Audio randomAudio = audioMapper.getRandomAudio();
        return randomAudio;
    }

    @Override
    public String audioDisplay(Integer id){
        if(isAudioIn(id))
        {
            Audio audio=audioMapper.selectById(id);
            return audio.getQiniuLocation();
        }
        else throw new APIException(AppCode.AUDIO_NOT_EXIST);
    }

    @Override
    public String audioDownload(Integer id){
        if(isAudioIn(id))
        {
            Audio audio=audioMapper.selectById(id);
            String url=downLoad(audio.getQiniuLocation());
            return url;
        }
        else throw new APIException(AppCode.AUDIO_NOT_EXIST);
    }

    @Override
    public boolean deleteAudio(Integer id){
        if(isAudioIn(id))
        {
            String key=audioMapper.selectById(id).getQiniuLocation();
            if(deleteByAudioId(id)&&audioMapper.deleteById(id)>0&&deleteFromQN(key)) return true;
            else return false;

        }
        else throw new APIException(AppCode.AUDIO_NOT_EXIST);
    }

    @Override
    public boolean deleteByAudioId(Integer id){
        LambdaQueryWrapper<UserHasAudio> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(UserHasAudio::getAudioId,id);
        if(userHasAudioMapper.delete(wrapper)>0) return true;
        else return false;
    }

}
