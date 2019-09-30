package com.study.meiyx.learning.security;

import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class TestBase64 {
    private static String src="Hello Base64";
    /**
     * @description:JDk实现主要使用用BASE64Encoder和BASE64Decoder类的方法
     * （注意：在Eclipse中使用JDK的Base64可能会出现找不到的问题是因为Base64Encoder并不属于JDK标准库范畴，
     * 但是又包含在了JDK中，需要我们手动导入\jre\lib目录下的rt.jar包即可）：
     * @author meiyx
     * @param
     * @return
     * @date 2019-06-13
     */
    public static void jdkBase64(){
        try {

            BASE64Encoder encoder=new BASE64Encoder();
            String encode = encoder.encode(src.getBytes());
            System.out.println("jdk encode: "+encode);

            BASE64Decoder decoder=new BASE64Decoder();
            String decode=new String(decoder.decodeBuffer(encode));
            System.out.println("jdk decode: "+decode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description:bc实现主要是用Base64类的方法：
     * @author meiyx
     * @param
     * @return
     * @date 2019-06-13
     */
    public static void bouncybastleBase64(){
        byte[] encode = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
        System.out.println("bc encode: "+new String(encode));

        byte[] decode = org.bouncycastle.util.encoders.Base64.decode(encode);
        System.out.println("bc decode: "+new String(decode));
    }
    
    /**
     * @description:cc实现也是用Base64类，不过与bc的是不一样的，不同包中的类，只是名字一样：
     * @author meiyx
     * @param
     * @return 
     * @date 2019-06-13
     */
    public static void commonscodecBase64(){
        byte[] encode= Base64.encodeBase64(src.getBytes());
        System.out.println("cc encode: "+new String(encode));  //需要转化为String

        byte[] decode = Base64.decodeBase64(encode);
        System.out.println("cc decode: "+new String(decode));
    }

    public static void main(String[] args) {
        jdkBase64();
        bouncybastleBase64();
        commonscodecBase64();
    }
}
