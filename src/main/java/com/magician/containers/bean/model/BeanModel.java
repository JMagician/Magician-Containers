package com.magician.containers.bean.model;

public class BeanModel {

    /**
     * bean名称
     */
    private String name;

    /**
     * bean对象
     */
    private Object obj;

    /**
     * class对象
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
