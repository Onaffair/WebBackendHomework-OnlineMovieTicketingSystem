package com.example.onaffair.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int code;
    private String msg;
    private T data;


    public static <T> Result<T> success() {
        return new Result<>(200, "Success", null);
    }

    // 静态方法：成功返回，带数据
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "Success", data);
    }

    // 静态方法：成功返回，自定义消息和数据
    public static <T> Result<T> success(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

    // 静态方法：失败返回，带状态码和消息
    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null);
    }

    // 静态方法：失败返回，带状态码、消息和数据
    public static <T> Result<T> error(int code, String message, T data) {
        return new Result<>(code, message, data);
    }
}
