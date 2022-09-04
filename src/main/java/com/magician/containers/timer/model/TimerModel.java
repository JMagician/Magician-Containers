package com.magician.containers.timer.model;

import com.magician.containers.commons.annotation.MagicianTimer;

import java.lang.reflect.Method;

/**
 * Scheduled task entity class
 */
public class TimerModel {

    /**
     * method name
     */
    private String methodName;

    /**
     * method that needs to be executed
     */
    private Method method;

    /**
     * Scheduled Task annotation
     */
    private MagicianTimer magicianTimer;

    /**
     * bean object
     */
    private Object obj;

    /**
     * class
     */
    private Class<?> cls;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
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

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public MagicianTimer getMagicianTimer() {
        return magicianTimer;
    }

    public void setMagicianTimer(MagicianTimer magicianTimer) {
        this.magicianTimer = magicianTimer;
    }
}
