package com.study.meiyx.learning.security;

import com.study.meiyx.learning.security.symmetric.DESUtils;
import org.junit.Test;

import java.util.Base64;

public class TestBase64Des {
    @Test
    public void encryptTest() {
        System.out.println(Base64.getEncoder().encodeToString(DESUtils.encrypt("hello".getBytes(), "12345678".getBytes())));
    }

    @Test
    public void decryptTest() {
        System.out.println(new String(DESUtils.decrypt(Base64.getDecoder().decode("uhbGoCVxJa8="), "12345678".getBytes())));
    }
}
