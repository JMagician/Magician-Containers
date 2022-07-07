package com.magician.containers.bean.aop.model;

/**
 * Aop实体
 */
public class AopModel {

    /**
     * Aop执行的类
     */
    private Class cls;

    /**
     * Aop执行的对象
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
