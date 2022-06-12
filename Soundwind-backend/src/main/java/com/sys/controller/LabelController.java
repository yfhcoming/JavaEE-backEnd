package com.sys.controller;


import com.sys.service.AudioHasLabelService;
import com.sys.service.LabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("/v1/labels")
@Api(tags = "Label")
public class LabelController {

    @Autowired
    LabelService labelService;

    @Autowired
    AudioHasLabelService audioHasLabelService;

    @GetMapping()
    @ApiOperation(value = "find all labels")
    public List findAllLabels()
    {
        // TODO 标签热度怎么做？待办
        return labelService.findAllLabels();
    }

    @GetMapping("/{labelId}/audios")
    @ApiOperation(value = "find all audios of the label by LabelId")
    public List findAllAudiosByLabelId(@PathVariable("labelId") @Valid @NotNull Integer labelId) {
        return audioHasLabelService.findAllAudiosByLabelId(labelId);
    }

}
