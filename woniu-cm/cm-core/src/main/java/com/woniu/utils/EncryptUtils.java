package com.woniu.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Slf4j
public class EncryptUtils {

    // 默认加密算法
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String KEY_ALGORITHM = "AES";
    private static SecretKeySpec AES_PASSWORD;

    public static String SHA1(String inStr) {
        MessageDigest md = null;
        String outStr = null;
        try {
            // 选择SHA-1，也可以选择MD5
            md = MessageDigest.getInstance("SHA-1");
            // 返回的是byet[]，要转化为String存储比较方便
            byte[] digest = md.digest(inStr.getBytes());
            outStr = bytetoString(digest);
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return outStr;
    }

    public static String bytetoString(byte[] digest) {
        String str = "";
        String tempStr = "";

        for (int i = 0; i < digest.length; i++) {
            tempStr = (Integer.toHexString(digest[i] & 0xff));
            if (tempStr.length() == 1) {
                str = str + "0" + tempStr;
            } else {
                str = str + tempStr;
            }
        }
        return str.toLowerCase();
    }

    public static String SHA(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 瀛楄妭鏁扮粍杞¬鎹¢涓º 鍗佸叚杩涘埗 鏁
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String MD5(String input) {
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            mdInst.update(input.getBytes(Charset.forName("UTF-8")));

            byte[] md = mdInst.digest();

            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < md.length; i++) {
                String shaHex = Integer.toHexString(md[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * AES 加密操作
     * @param content 待加密内容
     * @param key
     * @return 返回Base64转码后的加密数据
     */
    public static String encryptAES(String content, String key) {
        if(StringUtils.isEmpty(content)) {
            return content;
        }
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
            byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
            return new BASE64Encoder().encode(encrypted);
        } catch (Exception ex) {
        }

        return content;
    }

    /**
     * AES 解密操作
     * @param content
     * @param key
     * @return
     */
    public static String decryptAES(String content, String key) {
        if(StringUtils.isEmpty(content)) {
            return content;
        }
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式 先用base64解密
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
            byte[] base64Data = new BASE64Decoder().decodeBuffer(content);
            byte[] original = cipher.doFinal(base64Data);
            String originalString = new String(original,"utf-8");
            return originalString;
        } catch (Exception e) {
            log.error("###### AES 解密异常 content:{} ######" , content, e);
        }
        return content;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) throws Exception {
        if(AES_PASSWORD !=null){
            return AES_PASSWORD;
        }
        try {
            //返回生成指定算法密钥生成器的 KeyGenerator 对象
            byte[] keyBytes = Arrays.copyOf(password.getBytes("ASCII"), 16);
            // 转换为AES专用密钥
            AES_PASSWORD =new SecretKeySpec(keyBytes, KEY_ALGORITHM);
            return AES_PASSWORD;
        } catch (Exception ex) {
            throw new Exception("加密秘钥失败");
        }
    }


    public static String urlEncode(String source, String encode) {
        String result = source;
        try {
            result = URLEncoder.encode(source, encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
