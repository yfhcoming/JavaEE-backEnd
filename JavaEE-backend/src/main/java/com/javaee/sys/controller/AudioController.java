package com.javaee.sys.controller;


import com.javaee.framework.configuration.RedisConfig;
import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.sys.dto.AddCommentDto;
import com.javaee.sys.dto.ConvertAddCommentVo2AddCommentDto;
import com.javaee.sys.entity.Audio;
import com.javaee.sys.entity.Comment;
import com.javaee.sys.mapper.AudioMapper;
import com.javaee.sys.po.AudioPo;
import com.javaee.sys.service.AudioService;
import com.javaee.sys.vo.audio.AddAudioVo;
import com.javaee.sys.vo.audio.AddCommentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.Cacheable;

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
@RequestMapping("/v1/audios")
@Api(tags = "Audio")
public class AudioController {

    @Autowired
    AudioService audioService;

    @Autowired
    AudioMapper audioMapper;

    @GetMapping()
//    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE)
    @ApiOperation(value = "find all audios")
    public List findAllAudios() {
        return audioService.findAllAudios();
    }

    @GetMapping("/{audioId}")
    @ApiOperation(value = "find the audio by id")
    public AudioPo findById(@PathVariable("audioId") Integer audioId) {
        return audioService.findById(audioId);
    }

    @GetMapping("/{audioId}/score")
    @ApiOperation(value = "find the score of the audio by audioId")
    public BigDecimal findScoreById(@PathVariable("audioId") @Valid @NotNull Integer audioId) {
        return audioService.findScoreById(audioId);
    }

    @GetMapping("/query/by-name")
    @ApiOperation(value = "search the audio by audio's name")
    public List searchByName(@RequestParam("name") @Valid @NotNull String name) {
        return audioService.searchByName(name);
    }

    @GetMapping("/query/by-random")
    @ApiOperation(value = "get an audio in random")
    public Audio getRandomAudio() {
        return audioService.getRandomAudio();
    }

    @GetMapping("/{audioId}/comments")
    @ApiOperation(value = "find all labels of the audio by audioId")
    public List findAllCommentsById(@PathVariable("audioId") @Valid @NotNull Integer audioId){
        return audioService.findAllCommentsById(audioId);
    }

    @PostMapping("/{audioId}/comments/comment")
    @ApiOperation(value = "add a comment with a userId")
    public boolean addCommentByUser(@PathVariable("audioId") Integer audioId,@RequestBody @Validated AddCommentVo vo){
        AddCommentDto addCommentDto = ConvertAddCommentVo2AddCommentDto.convert(audioId, vo);
        return audioService.addCommentByUser(addCommentDto);
    }

    @GetMapping("/{audioId}/actions/display")
    @ApiOperation(value = "get display audio url by id")
    public String audioDisplay(@PathVariable("audioId") Integer id){
        return audioService.audioDisplay(id);
    }

    @GetMapping("/{audioId}/actions/download")
    @ApiOperation(value = "download by id")
    public MultipartFile audioDownload(@PathVariable("audioId") Integer id){
        return audioService.audioDownload(id);
    }

    @DeleteMapping("/{audioId}")
    @ApiOperation(value = "delete audio by id")
    public boolean deleteAudio(@PathVariable("audioId") Integer id){
        return audioService.deleteAudio(id);
    }

    @PostMapping("/audio")
    @ApiOperation(value = "upload audio file")
    public Integer audioUpload(@Validated AddAudioVo addAudioVo)
    {
        return audioService.uploadAudio(addAudioVo);
    }

}
