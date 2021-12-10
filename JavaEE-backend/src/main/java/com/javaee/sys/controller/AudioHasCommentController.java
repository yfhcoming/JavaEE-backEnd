package com.javaee.sys.controller;


import com.javaee.sys.service.AudioHasCommentService;
import com.javaee.sys.vo.audio.addCommentVo;
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
 * @since 2021-12-08
 */
@RestController
@RequestMapping("/audio")
public class AudioHasCommentController {
    @Autowired
    AudioHasCommentService audioHasCommentService;

    @GetMapping("/findAllCommentsById")
    @ApiOperation(value = "find all labels of the audio by audioId")
    public List findAllCommentsById(@RequestParam("audioId") @Valid @NotNull Integer audioId){
        return audioHasCommentService.findAllCommentsById(audioId);
    }

    @PostMapping("/addCommentByUser")
    @ApiOperation(value = "add a comment with a userId")
    public boolean addCommentByUser(@RequestBody @Validated addCommentVo vo){
        return audioHasCommentService.addCommentByUser(vo);
    }

}
