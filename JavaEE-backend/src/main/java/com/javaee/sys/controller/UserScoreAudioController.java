package com.javaee.sys.controller;


import com.javaee.sys.service.UserScoreAudioService;
import com.javaee.sys.vo.audio.AudioHasLabelVo;
import com.javaee.sys.vo.user.UserScoreAudioVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author T12
 * @since 2021-12-08
 */
@RestController
@RequestMapping("/v1/users")
@Api(tags = "UserScoreAudio")
public class UserScoreAudioController {

    @Autowired
    UserScoreAudioService userScoreAudioService;

    @PostMapping("/{userId}/audios/{audioId}/actions/score")
    @ApiOperation(value = "user scores an audio")
    public boolean scoreAudio(@PathVariable("userId") Integer userId,
                              @PathVariable("audioId") Integer audioId,
                              @RequestParam("score") BigDecimal score)
    {
        UserScoreAudioVo userScoreAudioVo = new UserScoreAudioVo(userId, audioId, score);
        return userScoreAudioService.scoreAudio(userScoreAudioVo);
    }

}
