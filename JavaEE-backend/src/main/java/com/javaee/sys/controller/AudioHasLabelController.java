package com.javaee.sys.controller;


import com.javaee.sys.service.AudioHasLabelService;
import com.javaee.sys.vo.audio.AudioHasLabelVo;
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
@RequestMapping("/v1/audios")
@Api(tags = "AudioHasLabel")
public class AudioHasLabelController {

    @Autowired
    AudioHasLabelService audioHasLabelService;

    @PostMapping("/{audioId}/labels/{labelId}")
    @ApiOperation(value = "add an label to the audio")
    public boolean addLabel(@PathVariable("audioId") Integer audioId,
                            @PathVariable("labelId") Integer labelId)
    {
        AudioHasLabelVo audioHasLabelVo = new AudioHasLabelVo(audioId,labelId);
        return audioHasLabelService.addLabel(audioHasLabelVo);
    }

    @GetMapping("/{audioId}/labels/")
    @ApiOperation(value = "find all labels of the audio by audioId")
    public List findAllLabelsById(@PathVariable("audioId") @Valid @NotNull Integer audioId){
        return audioHasLabelService.findAllLabelsById(audioId);
    }





}
