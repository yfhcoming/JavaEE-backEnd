package com.javaee.sys.controller;


import com.javaee.sys.entity.UserHasAudio;
import com.javaee.sys.service.UserHasAudioService;
import com.javaee.sys.vo.audio.AddAudioVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author T12
 * @since 2021-12-09
 */
@RestController
@RequestMapping("/audio")
public class UserHasAudioController {

    @Autowired
    UserHasAudioService userHasAudioService;

    @PostMapping("/upload")
    public boolean audioUpload(AddAudioVo addAudioVo)
    {
        return userHasAudioService.uploadAudio(addAudioVo);
    }
}
