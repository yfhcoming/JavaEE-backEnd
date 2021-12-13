package com.javaee.sys.controller;


import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.sys.entity.Collection;
import com.javaee.sys.entity.CollectionHasAudio;
import com.javaee.sys.service.CollectionHasAudioService;
import com.javaee.sys.vo.collection.CollectionHasAudioVo;
import io.swagger.annotations.Api;
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
@RequestMapping("/v1/collections")
@Api(tags = "Collection")
public class CollectionHasAudioController {

    @Autowired
    CollectionHasAudioService collectionHasAudioService;

    @PostMapping("/{collectionId}/audios/{audioId}")
    @ApiOperation(value = "add an audio into the collection")
    public boolean addAudio(@PathVariable("collectionId") Integer collectionId,
                            @PathVariable("audioId") Integer audioId)
    {
        CollectionHasAudioVo collectionHasAudioVo = new CollectionHasAudioVo(collectionId, audioId);
        return collectionHasAudioService.addAudio(collectionHasAudioVo);
    }

    @DeleteMapping("/{collectionId}/audios/{audioId}")
    @ApiOperation(value = "delete an audio in the collection")
    public boolean deleteAudio(@PathVariable("collectionId") Integer collectionId,
                            @PathVariable("audioId") Integer audioId)
    {
        CollectionHasAudioVo collectionHasAudioVo = new CollectionHasAudioVo(collectionId, audioId);
        return collectionHasAudioService.deleteAudio(collectionHasAudioVo);
    }

    @GetMapping("/{collectionId}/audios")
    @ApiOperation(value = "find all audios of the collection by collectionId")
    public List findAllAudiosById(@PathVariable("collectionId") @Valid @NotNull Integer collectionId){
        return collectionHasAudioService.findAllAudiosById(collectionId);
    }
}
