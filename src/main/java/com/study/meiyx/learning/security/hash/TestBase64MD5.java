package com.study.meiyx.learning.security.hash;

import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class TestBase64MD5 {
    @Test
    public void test() throws Exception {
        String source = "123456";
        String md5Hex = MD5UtilBySpring.md5(source);
        System.out.println(source + "'s hex hash -> " + md5Hex);
    }

    @Test
    public void test2() throws Exception {
        File file = new File("d:/poi-bin-3.13-20150929.tar.gz");

        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream();

            byte[] bytes = new byte[1024];
            int size = 0;
            while ((size = fis.read(bytes)) != -1) {
                if (size == 1024) {
                    baos.write(bytes);
                } else {
                    baos.write(bytes, 0, size);
                }
            }

            byte[] resultBytes = baos.toByteArray();
            System.out.println("size -> " + resultBytes.length);
            String md5Hex = MD5UtilBySpring.md5(resultBytes);
            System.out.println("Hex hash -> " + md5Hex);
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (baos != null) {
                baos.close();
            }
        }
    }

    @Test
    public void test3() throws Exception {
        String source = "123456";
        String saltSource = Salter.salt(source);
        String md5 = MD5UtilBySpring.md5(saltSource);
        System.out.println(source + " salt -> " + saltSource + " MD5 -> " + md5);

        source = "12345";
        saltSource = Salter.salt(source);
        md5 = MD5UtilBySpring.md5(saltSource);
        System.out.println(source + " salt -> " + saltSource + " MD5 -> " + md5);

        source = "1234";
        saltSource = Salter.salt(source);
        md5 = MD5UtilBySpring.md5(saltSource);
        System.out.println(source + " salt -> " + saltSource + " MD5 -> " + md5);
    }

    @Test
    public void test4(){
        String source = "123456";

       String md5Hex= DigestUtils.md5DigestAsHex(source.getBytes());
        System.out.println(md5Hex);
    }
}
