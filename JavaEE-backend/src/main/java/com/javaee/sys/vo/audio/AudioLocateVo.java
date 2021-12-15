package com.javaee.sys.vo.audio;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AudioLocateVo {

    private Integer audioId;

    private String audioName;

    private LocalDateTime createTime;

    private BigDecimal lng;

    private BigDecimal lat;
}
