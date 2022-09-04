package com.magician.containers;

import com.magician.containers.bean.LoadBeans;
import com.magician.containers.commons.cache.BeanCacheManager;
import com.magician.containers.timer.ExecuteTimers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * bean utility class
 */
public class BeanUtil {

    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    private static boolean isLoad = false;

    static {
        init();
    }

    /**
     * get bean object
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T get(Class<T> tClass){
        init();

        return (T)BeanCacheManager.get(tClass.getName()).getObj();
    }

    /**
     * Get beanName based on bean class name
     * @param tClass
     * @return
     */
    public static String getBeanName(Class<?> tClass){
        return tClass.getName();
    }

    /**
     * initialization
     */
    private static void init(){
        try {
            if(isLoad == false){
                LoadBeans.loadBean();
                ExecuteTimers.execute();
                isLoad = true;
            }
        } catch (Exception e){
            logger.error("exception loading bean", e);
        }
    }
}
