package com.magician.containers;

import com.magician.containers.bean.LoadBeans;
import com.magician.containers.timer.ExecuteTimers;

/**
 * 主入口
 */
public class MagicianContainers {

    /**
     * 加载并创建bean，以及开启定时任务等
     * @throws Exception
     */
    public static void load() throws Exception {
        LoadBeans.loadBean();
        ExecuteTimers.execute();
    }
}
