package com.magician.containers.bean;

import com.magician.containers.commons.cache.BeanCacheManager;

/**
 * bean工具类
 */
public class BeanUtil {

    /**
     * 获取bean对象
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T get(Class<T> tClass){
        return (T)BeanCacheManager.get(tClass.getName()).getObj();
    }

    /**
     * 根据bean类名获取beanName
     * @param tClass
     * @return
     */
    public static String getBeanName(Class<?> tClass){
        return tClass.getName();
    }
}
