package com.javaee.sys.vo.audio;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AudioLocateVo {

    private Integer audioId;

    private String audioName;

    private BigDecimal lng;

    private BigDecimal lat;
}
