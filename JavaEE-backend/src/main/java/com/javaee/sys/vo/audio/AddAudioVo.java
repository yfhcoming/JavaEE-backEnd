package com.javaee.sys.vo.audio;

import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddAudioVo {

    @NotNull(message = "用户ID不允许为空！")
    private Integer id;

    @NotEmpty(message = "音频名称不允许为空！")
    private String name;

    @NotNull(message = "音频不允许为空！")
    private MultipartFile multipartFile;

}
