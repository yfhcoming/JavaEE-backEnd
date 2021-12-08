package com.javaee.sys.controller;


import com.javaee.sys.entity.Audio;
import com.javaee.sys.mapper.AudioMapper;
import com.javaee.sys.service.AudioService;
import com.javaee.sys.vo.audio.getOneVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
    public List findAllAudios() {
        return audioService.findAllAudios();
    }

    @GetMapping("/find/{audioId}")
    @ApiOperation(value = "find the audio by id")
    public Audio findById(@PathVariable("audioId") Integer audioId) {
        return audioService.getById(audioId);
    }

    @GetMapping("/findScoreById")
    @ApiOperation(value = "find the score of the audio by audioId")
    public BigDecimal findScoreById(@RequestParam("audioId") @Valid @NotNull Integer audioId) {
        return audioService.findScoreById(audioId);
    }

    @GetMapping("/searchByName")
    @ApiOperation(value = "search the audio by audio's name")
    public List searchByName(@RequestParam("name") @Valid @NotNull String name) {
        return audioService.searchByName(name);
    }

    @GetMapping("/getRandomAudio")
    @ApiOperation(value = "get an audio in random")
    public Audio getRandomAudio() {
        return audioService.getRandomAudio();
    }

}
