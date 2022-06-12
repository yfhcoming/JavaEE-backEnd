package com.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.entity.CollectionHasAudio;
import com.sys.vo.collection.CollectionHasAudioVo;

import java.util.List;


public interface CollectionHasAudioService extends IService<CollectionHasAudio> {
    boolean addAudio(CollectionHasAudioVo dto);

    boolean isCollectionHasAudioIn(CollectionHasAudioVo dto);

    boolean deleteAudio(CollectionHasAudioVo dto);

    List findAllAudiosById(Integer collectionId);
}
