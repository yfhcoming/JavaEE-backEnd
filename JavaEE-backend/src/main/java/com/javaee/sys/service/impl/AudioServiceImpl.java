package com.javaee.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.javaee.framework.enums.AppCode;
import com.javaee.framework.exception.APIException;
import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.framework.utils.QiNiuUtils;
import com.javaee.sys.entity.Audio;
import com.javaee.sys.entity.User;
import com.javaee.sys.entity.UserHasAudio;
import com.javaee.sys.mapper.AudioMapper;
import com.javaee.sys.mapper.UserHasAudioMapper;
import com.javaee.sys.po.AudioPo;
import com.javaee.sys.po.CommentPo;
import com.javaee.sys.service.AudioService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaee.sys.vo.audio.AddAudioVo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
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

    public AudioPo findById(Integer audioId){
        AudioPo audioPo = audioMapper.findById(audioId);
        if(audioPo == null){
            Audio auto = this.getById(audioId);
            AudioPo audioPoTMP = BeanConvertUtils.convertTo(auto, AudioPo::new);
            return audioPoTMP;

        }
        return audioPo;
    }

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
            String key=audioMapper.selectById(id).getQiniuLocation().substring(36);
            if(audioMapper.deleteById(id)>0&&deleteFromQN(key)) return true;
            else return false;
        }
        else throw new APIException(AppCode.AUDIO_NOT_EXIST);
    }

    @Override
    public boolean uploadAudio(AddAudioVo addAudioVo)
    {
        String url;
        try {
            InputStream fileInputStream= addAudioVo.getMultipartFile().getInputStream();
            url= QiNiuUtils.upLoad(fileInputStream, addAudioVo.getName());
        } catch (IOException e) {
            throw new APIException(AppCode.FILE_UPLOAD_FAIL);
        }
        Audio audio=new Audio();
        audio.setAudioName(addAudioVo.getName());
        audio.setQiniuLocation(url);
        audio.setUserId(addAudioVo.getId());
        try
        {
            audioMapper.insert(audio);
            return true;
        }catch (Exception e){
            throw new APIException(AppCode.AUDIO_INSERT_FAIL);
        }
    }

    @Override
    public List findByUserId(Integer userId){
        LambdaQueryWrapper<Audio> wrapper=new LambdaQueryWrapper<>();
        List<Audio> audioList=audioMapper.selectList(wrapper);
        return audioList;
    }
}
