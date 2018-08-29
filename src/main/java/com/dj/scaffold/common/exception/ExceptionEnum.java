package com.dj.scaffold.common.exception;

public enum ExceptionEnum {
    INTERNAL_ERROR(10001, "系统出了点小问题，正在抢修中"),
    RES_NOT_FOUND(10002, "资源不存在"),
    UNAUTHORIZED(10003, "登录信息过期，请重新登录"),
    PARAM_INVALID(10004, "参数非法"),
    WRONG_REQ(10005, "请求参数错误"),
    ENCRYPTEDDATA_ERR(10006, "请求类型错误");

    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
