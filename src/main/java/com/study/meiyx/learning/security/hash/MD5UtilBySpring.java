package com.study.meiyx.learning.security.hash;

import org.springframework.util.DigestUtils;

public class MD5UtilBySpring {
    /**
     * 使用MD5作信息摘要，并以十六进制表示
     */
    public static String md5(byte[] bytes) {
        return DigestUtils.md5DigestAsHex(bytes);
    }

    /**
     * 使用MD5作信息摘要，并以十六进制表示
     */
    public static String md5(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        return MD5UtilBySpring.md5(s.getBytes());
    }
}
