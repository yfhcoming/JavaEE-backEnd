package com.javaee.sys.vo.collection;


import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class CollectionAddVo {
    // 主键
    private Integer collectionId;

    @NotBlank(message = "收藏夹名称不允许为空")
    private String name;

}
