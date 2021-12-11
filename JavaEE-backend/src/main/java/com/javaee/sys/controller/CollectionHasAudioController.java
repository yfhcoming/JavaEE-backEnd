package com.javaee.sys.controller;


import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.sys.entity.Collection;
import com.javaee.sys.entity.CollectionHasAudio;
import com.javaee.sys.service.CollectionHasAudioService;
import com.javaee.sys.vo.collection.CollectionHasAudioVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    public boolean addAudio(@RequestBody @Validated CollectionHasAudioVo vo)
    {
        return collectionHasAudioService.addAudio(vo);
    }

    @PostMapping("/deleteAudio")
    @ApiOperation(value = "delete an audio in the collection")
    public boolean deleteAudio(@RequestBody@Validated CollectionHasAudioVo vo){
        return collectionHasAudioService.deleteAudio(vo);
    }

    @GetMapping("/findAllAudiosById")
    @ApiOperation(value = "find all audios of the audio by audioId")
    public List findAllAudiosById(@RequestParam("collectionId") @Valid @NotNull Integer collectionId){
        return collectionHasAudioService.findAllAudiosById(collectionId);
    }
}
