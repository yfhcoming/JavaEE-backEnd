package com.javaee.sys.service;

import com.javaee.sys.entity.CollectionHasAudio;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaee.sys.vo.collection.CollectionHasAudioVo;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author T12
 * @since 2021-12-07
 */
public interface CollectionHasAudioService extends IService<CollectionHasAudio> {
    boolean addAudio(CollectionHasAudioVo dto);

    boolean isCollectionHasAudioIn(CollectionHasAudioVo dto);

    boolean deleteAudio(CollectionHasAudioVo dto);

    List findAllAudiosById(Integer collectionId);
}
