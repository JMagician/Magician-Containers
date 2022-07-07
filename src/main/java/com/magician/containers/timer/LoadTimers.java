package com.magician.containers.timer;

import com.magician.containers.commons.annotation.MagicianTimer;
import com.magician.containers.bean.model.BeanModel;
import com.magician.containers.commons.cache.BeanCacheManager;
import com.magician.containers.commons.cache.TimerCacheManger;
import com.magician.containers.timer.model.TimerModel;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 加载定时任务
 */
public class LoadTimers {

    /**
     * 加载所有加了MarsTimer注解的对象
     * @throws Exception 异常
     */
    public static void loadMarsTimers() throws Exception {
        /* 获取所有的bean数据 */
        Map<String, BeanModel> beanModelMap = BeanCacheManager.getBeanModelMap();

        for(String key : beanModelMap.keySet()) {
            BeanModel beanModel = beanModelMap.get(key);

            Class<?> cls = beanModel.getCls();
            Method[] methods = cls.getDeclaredMethods();

            for(Method method : methods){
                MagicianTimer magicianTimer = method.getAnnotation(MagicianTimer.class);
                if(magicianTimer == null){
                    continue;
                }

                if(method.getParameterCount() > 0){
                    throw new Exception("有参数的方法不可以添加定时任务，方法名:"+cls.getName()+"."+ method.getName());
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
