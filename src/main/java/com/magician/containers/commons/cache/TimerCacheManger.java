package com.magician.containers.commons.cache;

import com.magician.containers.timer.model.TimerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 需要定时任务的bean以及方法缓存管理
 */
public class TimerCacheManger {

    /**
     * 需要定时任务的bean以及方法缓存
     */
    private static List<TimerModel> timerModelMap = new ArrayList<>();

    public static List<TimerModel> getTimerModelMap() {
        return timerModelMap;
    }

    public static void add(TimerModel beanModel){
        timerModelMap.add(beanModel);
    }
}
