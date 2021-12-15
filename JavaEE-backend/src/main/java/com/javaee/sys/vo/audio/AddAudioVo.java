package com.javaee.sys.vo.audio;

import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AddAudioVo {

    @NotNull(message = "用户ID不允许为空！")
    private Integer userId;

    @NotEmpty(message = "音频名称不允许为空！")
    private String audioName;

    @NotNull(message = "音频不允许为空！")
    private MultipartFile audio;

    @NotNull(message = "封面不允许为空！")
    private MultipartFile cover;

    private String description;

    private BigDecimal lng;

    private BigDecimal lat;
}
