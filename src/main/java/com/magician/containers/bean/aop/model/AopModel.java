package com.magician.containers.bean.aop.model;

/**
 * Aop entity
 */
public class AopModel {

    /**
     * The class that Aop executes
     */
    private Class cls;

    /**
     * Object executed by Aop
     */
    private Object obj;

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
