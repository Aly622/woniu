package com.woniu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName RegExUtils
 * @Desc TODO
 * @Author Oliver.Liu
 * @Date 2019/9/19 16:16
 * @Version 1.0
 **/
public class RegExUtils {
    public static String onlyDigit(String source) {
        // 只允数字  
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(source);
        //把非数字的字符替换为"" 
        return m.replaceAll("").trim();
    }
}
