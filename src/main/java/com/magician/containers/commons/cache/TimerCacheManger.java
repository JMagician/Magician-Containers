package com.magician.containers.commons.cache;

import com.magician.containers.timer.model.TimerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Beans and method cache management that require scheduled tasks
 */
public class TimerCacheManger {

    /**
     * Beans and method caches that require scheduled tasks
     */
    private static List<TimerModel> timerModelMap = new ArrayList<>();

    public static List<TimerModel> getTimerModelMap() {
        return timerModelMap;
    }

    public static void add(TimerModel beanModel){
        timerModelMap.add(beanModel);
    }
}
