package com.example.onaffair.utils;

import java.util.Base64;

public class EncryptionUtil {

    /**
     * Base64 编码
     *
     * @param input 原始字符串
     * @return 编码后的字符串
     */
    public static String encodeBase64(String input) {
        // 将字符串转换为字节数组
        byte[] encodedBytes = Base64.getEncoder().encode(input.getBytes());
        // 返回 Base64 编码后的字符串
        return new String(encodedBytes);
    }

    /**
     * Base64 解码
     *
     * @param input Base64 编码后的字符串
     * @return 解码后的字符串
     */
    public static String decodeBase64(String input) {
        // 将 Base64 编码的字符串转换为字节数组
        byte[] decodedBytes = Base64.getDecoder().decode(input);
        // 返回解码后的字符串
        return new String(decodedBytes);
    }
}
