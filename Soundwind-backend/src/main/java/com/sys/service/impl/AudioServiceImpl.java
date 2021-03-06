package com.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.enums.AppCode;
import com.framework.exception.APIException;
import com.framework.utils.BeanConvertUtils;
import com.framework.utils.QiNiuUtils;
import com.sys.dto.AddCommentDto;
import com.sys.entity.Audio;
import com.sys.entity.Comment;
import com.sys.mapper.AudioMapper;
import com.sys.po.AudioPo;
import com.sys.po.CommentPo;
import com.sys.service.AudioService;
import com.sys.service.CommentService;
import com.sys.service.UserService;
import com.sys.vo.audio.AddAudioVo;
import com.sys.vo.audio.AudioLocateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import static com.framework.utils.QiNiuUtils.deleteFromQN;
import static com.framework.utils.QiNiuUtils.download;


@Service
public class AudioServiceImpl extends ServiceImpl<AudioMapper, Audio> implements AudioService {

    @Autowired
    AudioMapper audioMapper;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    public boolean isAudioIn(Integer audioId){
        LambdaQueryWrapper<Audio> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Audio::getAudioId, audioId);
        Integer integer = audioMapper.selectCount(wrapper);
        boolean result = (integer == 0)?false:true;
        return result;
    }

    public List findAllAudios()
    {
        List<AudioPo> allAudios = audioMapper.findAllAudios();
        return allAudios;
    };

    public AudioPo findById(Integer audioId){
        if(!isAudioIn(audioId)){
            throw new APIException(AppCode.AUDIO_NOT_EXIST, "??????????????????audioId - " + audioId);
        }
        AudioPo audioPo = audioMapper.findById(audioId);
        return audioPo;
    }

    public BigDecimal findScoreById(Integer audioId){
        // TODO ???????????????????????????
        if(!isAudioIn(audioId)){
            throw new APIException(AppCode.AUDIO_NOT_EXIST, "??????????????????audioId - " + audioId);
        }
        List<BigDecimal> allScores = audioMapper.findAllScoresById(audioId);
        if(allScores.isEmpty() || allScores == null){
            throw new APIException(AppCode.AUDIO_HAS_NO_SCORE, "????????????????????????audioId - " + audioId);
        }
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

    public List findAllCommentsById(Integer audioId){
        List<CommentPo> comments = audioMapper.findAllCommentsById(audioId);
        return comments;
    }

    public boolean addCommentByUser(AddCommentDto dto){
        // TODO ??????????????? ??????
        // ??????????????? (userId )
        // ?????????audio ???????????? ???
        if(!isAudioIn(dto.getAudioId())){
            throw new APIException(AppCode.AUDIO_NOT_EXIST, "??????????????????audioId - " + dto.getAudioId());
        }
        if(!userService.isUserIn(dto.getUserId())){
            throw new APIException(AppCode.USER_NOT_EXIST, "??????????????????userId - " + dto.getUserId());
        }

        // ?????????????????? BeanConvertUtils ??????????????????????????????????????????????????????????????????????????????????????????null
        Comment comment = BeanConvertUtils.convertTo(dto, Comment::new);
        commentService.save(comment);
        return true;
    }

    @Override
    public String audioDisplay(Integer id){
        if(isAudioIn(id))
        {
            Audio audio=audioMapper.selectById(id);
            return audio.getAudioFile();
        }
        else throw new APIException(AppCode.AUDIO_NOT_EXIST);
    }

    @Override
    public void audioDownload(Integer id,HttpServletResponse res){
        if(isAudioIn(id))
        {
            Audio audio=audioMapper.selectById(id);
            if(audio.getAudioFile()!=null) download(audio.getAudioFile(), res);
            else throw new APIException(AppCode.AUDIO_FILE_NOT_EXIST);
        }
        else throw new APIException(AppCode.AUDIO_NOT_EXIST);
    }

    @Override
    public boolean deleteAudio(Integer id){
        if(isAudioIn(id))
        {
            String key=audioMapper.selectById(id).getAudioFile().substring(36);
            if(audioMapper.deleteById(id)>0&&deleteFromQN(key)) return true;
            else return false;
        }
        else throw new APIException(AppCode.AUDIO_NOT_EXIST);
    }

    @Override
    public Integer uploadAudio(AddAudioVo addAudioVo)
    {
        String url1,url2;
        try {
            InputStream fileInputStream= addAudioVo.getAudio().getInputStream();
            url1= QiNiuUtils.upLoad(fileInputStream, addAudioVo.getAudioName());
        } catch (IOException e) {
            throw new APIException(AppCode.FILE_UPLOAD_FAIL);
        }
        try {
            InputStream fileInputStream= addAudioVo.getCover().getInputStream();
            url2= QiNiuUtils.upLoad(fileInputStream, addAudioVo.getAudioName());
        } catch (IOException e) {
            throw new APIException(AppCode.FILE_UPLOAD_FAIL);
        }
        Audio audio=BeanConvertUtils.convertTo(addAudioVo,Audio::new);
        audio.setAudioFile(url1);
        audio.setCover(url2);
        try
        {
            audioMapper.insert(audio);
            return audioMapper.findIdByAudioFile(url1);
        }catch (Exception e){
            throw new APIException(AppCode.AUDIO_INSERT_FAIL);
        }
    }

    @Override
    public List findByUserId(Integer userId){
        if(userService.isUserIn(userId))
        {
                LambdaQueryWrapper<Audio> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(Audio::getUserId,userId);
            List<Audio> audioList=audioMapper.selectList(wrapper);
            if(audioList==null) throw new APIException(AppCode.USER_HAS_NO_AUDIO);
            else return audioList;
        }
        else throw new APIException(AppCode.USER_NOT_EXIST);
    }

    @Override
    public List getAudioLocation(){
        List<Audio> audioList=audioMapper.selectList(null);
        return BeanConvertUtils.convertListTo(audioList, AudioLocateVo::new);
    }
}
