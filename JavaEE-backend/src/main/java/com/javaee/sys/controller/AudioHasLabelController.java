package com.javaee.sys.controller;


import com.javaee.sys.service.AudioHasLabelService;
import com.javaee.sys.vo.audio.AudioHasLabelVo;
import com.javaee.sys.vo.collection.CollectionHasAudioVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/audio")
public class AudioHasLabelController {

    @Autowired
    AudioHasLabelService audioHasLabelService;

    @PostMapping("/addLabel")
    @ApiOperation(value = "add an label to the audio")
    public boolean addLabel(AudioHasLabelVo vo)
    {
        return audioHasLabelService.addLabel(vo);
    }

    @GetMapping("/findAllLabelsById")
    @ApiOperation(value = "find all labels of the audio by audioId")
    public List findAllLabelsById(@RequestParam("audioId") @Valid @NotNull Integer audioId){
        return audioHasLabelService.findAllLabelsById(audioId);
    }





}
