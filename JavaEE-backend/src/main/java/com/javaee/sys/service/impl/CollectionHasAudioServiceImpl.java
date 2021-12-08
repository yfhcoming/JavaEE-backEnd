package com.javaee.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.javaee.framework.enums.AppCode;
import com.javaee.framework.exception.APIException;
import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.sys.entity.Audio;
import com.javaee.sys.entity.Collection;
import com.javaee.sys.entity.CollectionHasAudio;
import com.javaee.sys.mapper.CollectionHasAudioMapper;
import com.javaee.sys.po.AudioPo;
import com.javaee.sys.po.LabelPo;
import com.javaee.sys.service.AudioService;
import com.javaee.sys.service.CollectionHasAudioService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaee.sys.service.CollectionService;
import com.javaee.sys.vo.collection.CollectionHasAudioVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author T12
 * @since 2021-12-07
 */
@Service
public class CollectionHasAudioServiceImpl extends ServiceImpl<CollectionHasAudioMapper, CollectionHasAudio> implements CollectionHasAudioService {

    @Autowired
    CollectionHasAudioMapper collectionHasAudioMapper;

    @Autowired
    AudioService audioService;

    @Autowired
    CollectionService collectionService;

    public boolean isCollectionHasAudioIn(CollectionHasAudioVo dto)
    {
        LambdaQueryWrapper<CollectionHasAudio> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CollectionHasAudio::getAudioId, dto.getAudioId())
                .eq(CollectionHasAudio::getCollectionId,dto.getCollectionId());
        Integer integer = collectionHasAudioMapper.selectCount(wrapper);
        boolean result = (integer == 0)?false:true;

        return result;
    }


    public boolean addAudio(CollectionHasAudioVo dto){

        if(!collectionService.isCollectionIn(dto.getCollectionId())){
            throw new APIException(AppCode.COLLECTION_NOT_EXIST, "收藏夹不存在：collectionId - " + dto.getCollectionId());
        }
        if(!audioService.isAudioIn(dto.getAudioId())){
            throw new APIException(AppCode.AUDIO_NOT_EXIST, "音频不存在：audioId - " + dto.getAudioId());
        }
        if(this.isCollectionHasAudioIn(dto)){
            throw new APIException(AppCode.COLLECTION_HAS_AUDIO_HAS_IN, "音频已在收藏夹中：collectionId - " + dto.getCollectionId()
                    +", audioId - " + dto.getAudioId());
        }

        this.save(BeanConvertUtils.convertTo(dto, CollectionHasAudio::new));

        return true;

    }

    public boolean deleteAudio(CollectionHasAudioVo dto){
        if(this.isCollectionHasAudioIn(dto)){
            LambdaQueryWrapper<CollectionHasAudio> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(CollectionHasAudio::getAudioId, dto.getAudioId())
                    .eq(CollectionHasAudio::getCollectionId,dto.getCollectionId());
            Integer integer = collectionHasAudioMapper.selectCount(wrapper);
            int deleteCount = collectionHasAudioMapper.delete(wrapper);
            System.out.println(deleteCount);
            return true;
        }
        else {
            throw new APIException(AppCode.COLLECTION_HAS_AUDIO_NOT_IN, "音频不在收藏夹中：collectionId - " + dto.getCollectionId()
                    +", audioId - " + dto.getAudioId());
        }
    }

    public List findAllAudiosById(Integer audioId) {
        List<AudioPo> audios = collectionHasAudioMapper.findAllAudiosById(audioId);
        return audios;
    }
}
