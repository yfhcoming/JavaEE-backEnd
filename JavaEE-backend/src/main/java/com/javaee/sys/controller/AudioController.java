package com.javaee.sys.controller;


import com.javaee.sys.entity.Audio;
import com.javaee.sys.mapper.AudioMapper;
import com.javaee.sys.service.AudioService;
import com.javaee.sys.vo.audio.getOneVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    AudioMapper audioMapper;

//    @GetMapping("/getOne")
//    public void getOne(@RequestParam("audioId") Integer audioId)
//    {
//        getOneVo one = audioMapper.getOne(audioId);
//        System.out.println(one);
//    }

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
