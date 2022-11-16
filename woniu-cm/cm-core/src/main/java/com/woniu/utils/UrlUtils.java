package com.woniu.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

public class UrlUtils {
    private final static String ENCODE = "GBK";
    /**
     * URL 解码
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午04:09:51
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * URL 转码
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午04:10:28
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }



    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * 生成短链接
     *
     * @param url		长链接
     * @return String	短地址
     */
    public String shortUrl(String url) {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        String key = "";
        // 要使用生成 URL 的字符
        String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
                "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };

        // 对传入网址进行 MD5 加密
        String hex = DigestUtils.md5Hex(key + url);

        String resUrl = "";
        int n = (int) (Math.random() % 3) + 1;

        // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
        String sTempSubString = hex.substring(n * 8, n * 8 + 8);
        // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用long
        // ，则会越界
        long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
        String outChars = "";
        for (int j = 0; j < 6; j++) {
            // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
            long index = 0x0000003D & lHexLong;
            // 把取得的字符相加
            outChars += chars[(int) index];
            // 每次循环按位右移 5 位
            lHexLong = lHexLong >> 5;
            // 把字符串存入对应索引的输出数组
            resUrl = outChars;
        }
        return resUrl;
    }
}
