package com.javaee.sys.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Audio implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "audio_id", type = IdType.AUTO)
    private Integer audioId;


    private String audioName;

    private BigDecimal score;

    private String des;

    private String audioFile;

    private Integer userId;

    private String cover;

    private BigDecimal lng;

    private BigDecimal lat;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
