package com.javaee.sys.controller;


import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.sys.entity.Collection;
import com.javaee.sys.entity.CollectionHasAudio;
import com.javaee.sys.service.CollectionHasAudioService;
import com.javaee.sys.vo.collection.CollectionHasAudioVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author T12
 * @since 2021-12-07
 */
@RestController
@RequestMapping("/collection")
public class CollectionHasAudioController {

    @Autowired
    CollectionHasAudioService collectionHasAudioService;

    @PostMapping("/addAudio")
    @ApiOperation(value = "add an audio into the collection")
    public boolean addAudio(@Validated CollectionHasAudioVo vo)
    {
        return collectionHasAudioService.addAudio(vo);
    }

    @PostMapping("/deleteAudio")
    @ApiOperation(value = "delete an audio in the collection")
    public boolean deleteAudio(@Validated CollectionHasAudioVo vo){
        return collectionHasAudioService.deleteAudio(vo);
    }
}
