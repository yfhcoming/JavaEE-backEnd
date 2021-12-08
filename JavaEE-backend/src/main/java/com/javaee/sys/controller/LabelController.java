package com.javaee.sys.controller;


import com.javaee.sys.service.AudioHasLabelService;
import com.javaee.sys.service.LabelService;
//import com.sun.xml.internal.bind.v2.TODO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    LabelService labelService;

    @Autowired
    AudioHasLabelService audioHasLabelService;

    @GetMapping("/findAll")
    @ApiOperation(value = "find all labels")
    public List findAllLabels()
    {
        // TODO 标签热度怎么做？待办
        return labelService.findAllLabels();
    }

    @GetMapping("/findAudiosByLabelId")
    @ApiOperation(value = "find all audios of the label by LabelId")
    public List findAudiosByLabelId(@RequestParam("LabelId") @Valid @NotNull Integer LabelId){
        return audioHasLabelService.findAudiosByLabelId(LabelId);
    }

}
