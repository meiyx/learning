package com.study.meiyx.learning.security.asymmetric;

import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @description: ELGamal算法的和AES一样存在密钥长度的限制，解决方法和AES一致。不过ELGamal只支持公钥加密，私钥解密这种方式。只有bc的实现方式
 * @author: meiyx
 * @date: 2019-06-13
 */
public class TestELGamal {
    private static String src="Hello ELGamal";
    public static void bcELGamal(){
        try {
            //加载provider
            Security.addProvider(new BouncyCastleProvider());

            //初始化密钥
            AlgorithmParameterGenerator algorithmParameterGenerator=AlgorithmParameterGenerator.getInstance("ELGamal");
            algorithmParameterGenerator.init(256);
            AlgorithmParameters algorithmParameters=algorithmParameterGenerator.generateParameters();
            DHParameterSpec dhParameterSpec=(DHParameterSpec)algorithmParameters.getParameterSpec(DHParameterSpec.class);
            KeyPairGenerator keyPairGenerator= KeyPairGenerator.getInstance("ELGamal");
            keyPairGenerator.initialize(dhParameterSpec, new SecureRandom());
            KeyPair keyPair=keyPairGenerator.generateKeyPair();
            PublicKey elGamalPublicKey=keyPair.getPublic();        //公钥
            PrivateKey elGamalPrivateKey=keyPair.getPrivate();     //私钥
            System.out.println("public key:"+ Base64.encodeBase64String(elGamalPublicKey.getEncoded()));
            System.out.println("private key:"+Base64.encodeBase64String(elGamalPrivateKey.getEncoded()));

            //公钥加密，私钥解密--加密
            X509EncodedKeySpec x509EncodedKeySpec=new X509EncodedKeySpec(elGamalPublicKey.getEncoded());
            KeyFactory keyFactory=KeyFactory.getInstance("ELGamal");
            PublicKey publicKey=keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher=Cipher.getInstance("ELGamal");
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("ELGamal加密:"+Base64.encodeBase64String(result));

            //公钥加密，私钥解密--解密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec=new PKCS8EncodedKeySpec(elGamalPrivateKey.getEncoded());
            keyFactory=KeyFactory.getInstance("ELGamal");
            PrivateKey privateKey =keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher=Cipher.getInstance("ELGamal");
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            result=cipher.doFinal(result);
            System.out.println("ElGamal解密:"+new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
