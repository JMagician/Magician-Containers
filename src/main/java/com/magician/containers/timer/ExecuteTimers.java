package com.magician.containers.timer;

import com.magician.containers.commons.cache.TimerCacheManger;
import com.magician.containers.timer.model.TimerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 执行定时任务
 */
public class ExecuteTimers {

    private static Logger logger = LoggerFactory.getLogger(ExecuteTimers.class);

    /**
     * 执行
     */
    public static void execute() {
        try {
            LoadTimers.loadMarsTimers();

            List<TimerModel> timerModelList = TimerCacheManger.getTimerModelMap();
            for (TimerModel timerModel : timerModelList) {
                loopTimer(timerModel);
            }
        } catch (Exception e) {
            logger.error("加载定时任务出错", e);
        }
    }

    /**
     * 定时轮询
     *
     * @param timerModel 对象
     */
    private static void loopTimer(TimerModel timerModel) {
        int fixedRate = timerModel.getMagicianTimer().loop();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                executeTimer(timerModel);
            }
        }, new Date(), fixedRate);
    }

    /**
     * 开始执行
     *
     * @param timerMode 对象
     */
    private static void executeTimer(TimerModel timerMode) {
        try {
            Object beanObject = timerMode.getObj();
            Method method = timerMode.getMethod();
            method.invoke(beanObject);
        } catch (Exception e) {
            logger.error("执行定时任务出错,方法名:{}.{}", timerMode.getCls().getName(), timerMode.getMethod().getName(), e);
        }
    }
}
