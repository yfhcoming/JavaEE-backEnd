package com.javaee.sys.controller;


import com.javaee.sys.entity.Audio;
import com.javaee.sys.service.AudioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/audio")
public class AudioController {

    @Autowired
    AudioService audioService;

    @GetMapping("/findAll")
    @ApiOperation(value = "find all audios")
    public List findAllAudios()
    {
        return audioService.findAllAudios();
    }

    @GetMapping("/find/{audioId}")
    @ApiOperation(value = "find the audio by id")
    public Audio findById(@PathVariable("audioId")Integer audioId)
    {
        return audioService.getById(audioId);
    }

}
