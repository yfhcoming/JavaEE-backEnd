package com.javaee.sys.vo.collection;


import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CollectionAddVo {
    // 外键
    @NotNull
    private Integer userId;

    @NotBlank(message = "收藏夹名称不允许为空")
    private String collectionName;

    private MultipartFile multipartFile;

}
