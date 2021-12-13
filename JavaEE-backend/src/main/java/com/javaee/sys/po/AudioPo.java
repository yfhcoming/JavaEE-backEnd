package com.javaee.sys.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AudioPo {

    private Integer audioId;

    private String audioName;

    //Redis
    private BigDecimal score;

    private String des;

    private String uploadUserName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
