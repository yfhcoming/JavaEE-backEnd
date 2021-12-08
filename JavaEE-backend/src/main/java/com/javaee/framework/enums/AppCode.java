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

    //用户相关code
    PASSWORD_ERROR(2030,"密码错误"),
    USER_NOT_EXIST(2031,"用户不存在"),
    TELEPHONE_HAS_EXIST(2032,"手机已被注册"),
    EMAIL_HAS_EXIST(2033,"邮箱已被注册"),
    REGISTER_FAIL(2034,"用户注册失败"),
    PASSWORD_UPDATE_FAIL(2035,"密码更新失败"),
    USER_INFO_UPDATE_FAIL(2036,"用户信息更新失败"),
    USER_INFO_GET_FAIL(2037,"用户信息获取失败"),
    ;

    private int code;
    private String msg;

    AppCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
