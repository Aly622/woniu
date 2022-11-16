package com.woniu.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;

/************************************** 
 * @program: connexus-scrm
 * @author: mike.ma
 * @desc: Aes对称加密工具类  
 * @date: 2021-07-29 11:46  
 **************************************/
public class AesUtils {

    private static final String SECRET = "AES";
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * AES加密ECB模式PKCS7Padding填充方式
     * @param str 字符串
     * @param key 密钥
     * @return 加密字符串
     * @throws Exception 异常信息
     */
    public static String encrypt(String str, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, SECRET));
        byte[] doFinal = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(doFinal));
    }

    /**
     * AES解密ECB模式PKCS7Padding填充方式
     * @param str 字符串
     * @param key 密钥
     * @return 解密字符串
     * @throws Exception 异常信息
     */
    public static String decrypt(String str, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, SECRET));
        byte[] doFinal = cipher.doFinal(Base64.getDecoder().decode(str));
        return new String(doFinal);
    }
}
