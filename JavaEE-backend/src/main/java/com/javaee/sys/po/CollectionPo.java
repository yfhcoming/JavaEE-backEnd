package com.javaee.sys.po;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CollectionPo {

    private Integer collectionId;

    //外键
    private String createUserName;

    private String collectionName;

    private String collectionCover;

    private LocalDateTime createTime;
}
