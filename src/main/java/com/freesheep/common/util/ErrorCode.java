package com.freesheep.common.util;


public enum ErrorCode {
    VALUE_ERROR("100010", "REQUEST_JSON_VALUE_ERROR"),
    SYSTEM_ERROR("100020", "SYSTEM_ERROR"),
    PERMISSION_ERROR("100030", "PERMISSION_ERROR"),
    ILLEGAL_OPERATION("100040", "ILLEGAL_OPERATION"),
    ILLEGAL_ACCOUNT_OPERATION("100041", "ACCOUNT_SETTLEMENT_TRY_LATER"),
    RECHARGE_FAILURE("100050", "RECHARGE_FAILURE"),
    IMG_SIZE_ERROR("100060", "IMAGE_SIZE_ERROR"),
    NOT_LOGIN("100070", "NOT_LOGIN"),
    NOT_USER("100071", "NOT_USER"),
    LOGIN_TIME_OUT("100072", "LOGIN_TIME_OUT");

    private final String code;
    private final String message;//给用户提示

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "{" +
                "\"errorCode\":\"" + code + "\"" +
                ", \"message\":\"" + message + '"' +
                '}';
    }
}
