package com.magician.containers.timer;

import com.magician.containers.commons.annotation.MagicianTimer;
import com.magician.containers.bean.model.BeanModel;
import com.magician.containers.commons.cache.BeanCacheManager;
import com.magician.containers.commons.cache.TimerCacheManger;
import com.magician.containers.timer.model.TimerModel;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Load scheduled tasks
 */
public class LoadTimers {

    /**
     * Load all objects annotated with MarsTimer
     *
     * @throws Exception
     */
    public static void loadMarsTimers() throws Exception {
        /* Get all bean data */
        Map<String, BeanModel> beanModelMap = BeanCacheManager.getBeanModelMap();

        for (String key : beanModelMap.keySet()) {
            BeanModel beanModel = beanModelMap.get(key);

            Class<?> cls = beanModel.getCls();
            Method[] methods = cls.getDeclaredMethods();

            for (Method method : methods) {
                MagicianTimer magicianTimer = method.getAnnotation(MagicianTimer.class);
                if (magicianTimer == null) {
                    continue;
                }

                if (method.getParameterCount() > 0) {
                    throw new Exception("Methods with parameters cannot add timed tasks, method name: " + cls.getName() + "." + method.getName());
                }

                TimerModel TimerModel = new TimerModel();
                TimerModel.setCls(cls);
                TimerModel.setMethodName(method.getName());
                TimerModel.setObj(beanModel.getObj());
                TimerModel.setMethod(method);
                TimerModel.setMagicianTimer(magicianTimer);

                TimerCacheManger.add(TimerModel);
            }
        }
    }
}
