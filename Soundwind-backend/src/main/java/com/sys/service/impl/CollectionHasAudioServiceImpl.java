package com.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.framework.enums.AppCode;
import com.framework.exception.APIException;
import com.framework.utils.BeanConvertUtils;
import com.sys.entity.CollectionHasAudio;
import com.sys.mapper.CollectionHasAudioMapper;
import com.sys.po.AudioPo;
import com.sys.service.AudioService;
import com.sys.service.CollectionHasAudioService;
import com.sys.service.CollectionService;
import com.sys.vo.collection.CollectionHasAudioVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
