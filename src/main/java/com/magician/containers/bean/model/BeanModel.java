package com.magician.containers.bean.model;

/**
 * Bean entity
 */
public class BeanModel {

    /**
     * bean name
     */
    private String name;

    /**
     * bean object
     */
    private Object obj;

    /**
     * bean class
     */
    private Class<?> cls;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }
}
