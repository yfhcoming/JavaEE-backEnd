package com.javaee.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.javaee.framework.enums.AppCode;
import com.javaee.framework.exception.APIException;
import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.framework.utils.QiNiuUtils;
import com.javaee.sys.dto.AddCommentDto;
import com.javaee.sys.entity.*;
import com.javaee.sys.mapper.AudioMapper;
import com.javaee.sys.po.AudioPo;
import com.javaee.sys.po.CommentPo;
import com.javaee.sys.service.AudioService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaee.sys.service.CommentService;
import com.javaee.sys.service.UserService;
import com.javaee.sys.vo.audio.AddAudioVo;
import com.javaee.sys.vo.audio.AudioLocateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import static com.javaee.framework.utils.QiNiuUtils.*;

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
            throw new APIException(AppCode.AUDIO_NOT_EXIST, "音频不存在：audioId - " + audioId);
        }
        AudioPo audioPo = audioMapper.findById(audioId);
        return audioPo;
    }

    public BigDecimal findScoreById(Integer audioId){
        // TODO 验证音频是否有评分
        if(!isAudioIn(audioId)){
            throw new APIException(AppCode.AUDIO_NOT_EXIST, "音频不存在：audioId - " + audioId);
        }
        List<BigDecimal> allScores = audioMapper.findAllScoresById(audioId);
        if(allScores.isEmpty() || allScores == null){
            throw new APIException(AppCode.AUDIO_HAS_NO_SCORE, "该音频没有评分：audioId - " + audioId);
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
        // TODO 先判断评论 用户
        // 先创建评论 (userId )
        // 在创建audio 拥有评论 表
        if(!isAudioIn(dto.getAudioId())){
            throw new APIException(AppCode.AUDIO_NOT_EXIST, "音频不存在：audioId - " + dto.getAudioId());
        }
        if(!userService.isUserIn(dto.getUserId())){
            throw new APIException(AppCode.USER_NOT_EXIST, "用户不存在：userId - " + dto.getUserId());
        }

        // 测试之后发现 BeanConvertUtils 的功能就是把名称相同的字段进行复制，没有名称相同的字段设置为null
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
            download(audio.getAudioFile(), res);
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
//        audio.setAudioName(addAudioVo.getName());
//        audio.setAudioFile(url1);
//        audio.setCover(url2);
//        audio.setUserId(addAudioVo.getId());
//        audio.setDes(addAudioVo.getDescription());
//        audio.setLng(addAudioVo.getLng());
//        audio.setLat(addAudioVo.getLat());
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
        return BeanConvertUtils.convertListTo(audioList,AudioLocateVo::new);
    }
}
