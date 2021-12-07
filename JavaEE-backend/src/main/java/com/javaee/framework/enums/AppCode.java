package com.javaee.framework.enums;

import lombok.Getter;

/**
 * 业务异常
 */
@Getter
public enum AppCode implements StatusCode {

    APP_ERROR(2000, "业务异常"),
    PRICE_ERROR(2001, "价格异常"),
    PRODUCT_NOT_EXIST(2002, "商品不存在"),
    ORDER_NOT_EXIST(2003, "订单不存在"),


    LABEL_NOT_EXIST(2004,"标签不存在"),
    AUDIO_NOT_EXIST(2005,"音频不存在"),
    COLLECTION_NOT_EXIST(2006,"收藏夹不存在"),
    COLLECTION_HAS_AUDIO_HAS_IN(2007,"音频已在收藏夹中"),
    COLLECTION_HAS_AUDIO_NOT_IN(2008,"音频不在收藏夹中"),
    AUDIO_HAS_LABEL_HAS_IN(2009,"音频已有该标签"),
    ;

    private int code;
    private String msg;

    AppCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
