package com.sys.controller;


import com.sys.service.CollectionHasAudioService;
import com.sys.vo.collection.CollectionHasAudioVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


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
