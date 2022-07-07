package com.magician.containers;

import com.magician.containers.bean.LoadBeans;
import com.magician.containers.timer.ExecuteTimers;

public class MagicianContainers {

    public static MagicianContainers createContainers(){
        return new MagicianContainers();
    }

    public void load() throws Exception {
        LoadBeans.loadBean();
        ExecuteTimers.execute();
    }
}
