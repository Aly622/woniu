package com.woniu.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

/**
 * @ClassName ObjectUtils
 * @Desc TODO
 * @Author Oliver.Liu
 * @Date 2019/7/30 16:13
 * @Version 1.0
 **/
@Slf4j
public class ObjectUtils {
    /*
     * @Author Oliver.Liu
     * @Desc //TODO 如果为空则返回0
     * @Date 2019/7/30 16:15
     * @Param [sourceObj]
     * @return java.lang.Double
     **/
    public static Double null2Zero(Object source) {
        Double ret = 0.0;
        if (source == null) {
            ret = 0.0;
        } else {
            ret = Double.valueOf(source.toString());
        }
        return ret;
    }
    /**
     * 转换成两位小数
     */
    public static Double toTwoDecimal(Double source) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.valueOf(df.format(source));
    }

    /**
     * 通过反射写一个类，类中有个方法。
     * 该方法可以设置某个类中的某个属性（构造方法，成员变量，成员方法）为特定的值
     * @param obj
     * @param fieldName
     * @param value
     */
    public static void setFieldValueByName(Object obj, String fieldName, Object value) {
        try {
            // 获取obj类的字节文件对象
            Class c = obj.getClass();
            // 获取该类的成员变量
            Field f = c.getDeclaredField(fieldName);
            // 取消语言访问检查
            f.setAccessible(true);
            // 给变量赋值
            f.set(obj, value);
        } catch (Exception e) {
            log.error("类设置属性值发生异常：",e);
        }
    }
    /**
     * 根据属性名获取属性值
     * */
    private Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }
}
