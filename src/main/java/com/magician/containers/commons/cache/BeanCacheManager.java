package com.magician.containers.commons.cache;

import com.magician.containers.bean.model.BeanModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean cache management
 */
public class BeanCacheManager {

    /**
     * bean cache
     */
    private static Map<String, BeanModel> beanModelMap = new ConcurrentHashMap<>();

    public static Map<String, BeanModel> getBeanModelMap() {
        return beanModelMap;
    }

    public static void add(String beanName, BeanModel beanModel){
        beanModelMap.put(beanName, beanModel);
    }

    public static BeanModel get(String beanName){
        return beanModelMap.get(beanName);
    }
}
