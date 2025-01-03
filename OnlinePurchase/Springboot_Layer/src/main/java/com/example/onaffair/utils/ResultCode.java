package com.example.onaffair.utils;

import lombok.Getter;

/**
 * 通用状态码枚举类
 */
@Getter
public enum ResultCode {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad Request"),
    ERROR(500, "Internal Server Error"),
    VALIDATION_ERROR(400, "Validation Failed"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
