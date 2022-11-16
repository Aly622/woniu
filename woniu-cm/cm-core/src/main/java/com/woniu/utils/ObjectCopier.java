package com.woniu.utils;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/*
 * @Author Oliver.Liu
 * @Desc //entity/bean 转换类
 * @Date 2019/6/9 21:29
 * @Param
 * @return
 **/
@Slf4j
public class ObjectCopier {
    /*
     * @Author Oliver.Liu
     * @Desc //复制列表
     * @Date 2019/7/1 15:34
     * @Param [srcList, targetType]
     * @return java.utils.List<T>
     **/
    public static <T, E> List<T> copyList(List<E> srcList, Class<T> targetType) {
        List<T> list = null;
        if (CollectionUtils.isNotEmpty(srcList)) {
            list = new ArrayList<>();
            for (E srcObj : srcList) {
                T targetObj = copyObject(srcObj, targetType);
                list.add(targetObj);
            }
        }
        return list;
    }

    /*
     * @Author Oliver.Liu
     * @Desc //复制单个对象
     * @Date 2019/7/1 15:34
     * @Param
     * @return
     **/
    public static <T, E> T copyObject(E srcObj, Class<T> targetType, String... ignoreProperties) {
        if (srcObj != null) {
            try {
                T targetObj = targetType.newInstance();
                BeanUtils.copyProperties(srcObj, targetObj, ignoreProperties);
                return targetObj;
            } catch (InstantiationException e) {
                log.error("对象复制发生异常: InstantiationException occurred", e);
            } catch (IllegalAccessException e) {
                log.error("对象复制发生异常: IllegalAccessException occurred", e);
            }
        }
        return null;
    }

    /*
     * @Author Oliver.Liu
     * @Desc //复制单个对象
     * @Date 2019/7/1 15:34
     * @Param
     * @return
     **/
    public static <T, E> T copyObjectValue(E srcObj, T targetObj, String... ignoreProperties) {
        if (srcObj != null) {
            try {
                BeanUtils.copyProperties(srcObj, targetObj, ignoreProperties);
                return targetObj;
            } catch (Exception e) {
                log.error("对象值复制发生异常: InstantiationException occurred", e);
            }
        }
        return null;
    }


    /*
     * @Author hasson
     * @Desc //把一个对象中不为空的值赋值到另一个对象中
     * @Date 2022/3/21 11:34
     * @Param
     **/
    public static void copyNoNullProperties(Object source, Object target) throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            if (targetPd.getWriteMethod() != null) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null && sourcePd.getReadMethod() != null) {
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
                        if (value != null) {
                            Method writeMethod = targetPd.getWriteMethod();
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        }
                    } catch (Throwable ex) {
                        throw new FatalBeanException("Could not copy properties from source to target", ex);
                    }
                }
            }
        }
    }

}
