package com.javaee.sys.controller;


import com.javaee.sys.entity.Audio;
import com.javaee.sys.mapper.AudioMapper;
import com.javaee.sys.po.AudioPo;
import com.javaee.sys.service.AudioService;
import com.javaee.sys.vo.audio.AddAudioVo;
import com.javaee.sys.vo.audio.addCommentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

import static com.javaee.framework.utils.QiNiuUtils.download2;

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
@Api(tags = "Audio")
public class AudioController {

    @Autowired
    AudioService audioService;

    @Autowired
    AudioMapper audioMapper;

    @GetMapping("/findAll")
    @ApiOperation(value = "find all audios")
    public List findAllAudios() {
        return audioService.findAllAudios();
    }

    @GetMapping("/find/{audioId}")
    @ApiOperation(value = "find the audio by id")
    public AudioPo findById(@PathVariable("audioId") Integer audioId) {
        return audioService.findById(audioId);
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

    @GetMapping("/findAllCommentsById")
    @ApiOperation(value = "find all labels of the audio by audioId")
    public List findAllCommentsById(@RequestParam("audioId") @Valid @NotNull Integer audioId){
        return audioService.findAllCommentsById(audioId);
    }

    @PostMapping("/addCommentByUser")
    @ApiOperation(value = "add a comment with a userId")
    public boolean addCommentByUser(@RequestBody @Validated addCommentVo vo){
        return audioService.addCommentByUser(vo);
    }

    @GetMapping("/display")
    @ApiOperation(value = "get display audio url by id")
    public String audioDisplay(Integer id){
        return audioService.audioDisplay(id);
    }

    @GetMapping("/download")
    @ApiOperation(value = "get download url by id")
    public MultipartFile audioDownload(Integer id){
        return audioService.audioDownload(id);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "delete audio by id")
    public boolean deleteAudio(Integer id){
        return audioService.deleteAudio(id);
    }

    @PostMapping("/upload")
    @ApiOperation(value = "upload audio file")
    public boolean audioUpload(@Validated AddAudioVo addAudioVo)
    {
        return audioService.uploadAudio(addAudioVo);
    }

    @GetMapping("/get/{userId}")
    @ApiOperation(value = "get all audio by user id")
    public List findByUserId(@PathVariable("userId") Integer userId){
        return audioService.findByUserId(userId);
    }

    @GetMapping("get/test")
    public MultipartFile test(String url)
    {
        return download2(url);
    }
}
