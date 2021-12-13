package com.javaee.sys.vo.user;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class UserScoreAudioVo {

    @NotNull(message = "用户Id不允许为空")
    private Integer userId;

    @NotNull(message = "音频Id不允许为空")
    private Integer audioId;

    @NotNull(message = "评分不允许为空")
    @Max(value = 5,message = "评分最大为5.0")
    private BigDecimal score;

    public UserScoreAudioVo(Integer userId, Integer audioId, BigDecimal score) {
        this.userId = userId;
        this.audioId = audioId;
        this.score = score;
    }
}
