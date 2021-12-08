package com.javaee.sys.controller;


import com.javaee.sys.service.UserScoreAudioService;
import com.javaee.sys.vo.audio.AudioHasLabelVo;
import com.javaee.sys.vo.user.UserScoreAudioVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author T12
 * @since 2021-12-08
 */
@RestController
@RequestMapping("/user")
public class UserScoreAudioController {

    @Autowired
    UserScoreAudioService userScoreAudioService;

    @PostMapping("/scoreAudio")
    @ApiOperation(value = "user scores an audio")
    public boolean scoreAudio(@Validated UserScoreAudioVo vo)
    {
        return userScoreAudioService.scoreAudio(vo);
    }

}
