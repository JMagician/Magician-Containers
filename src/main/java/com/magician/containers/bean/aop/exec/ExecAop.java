package com.magician.containers.bean.aop.exec;

import com.magician.containers.bean.aop.model.AopModel;
import com.magician.containers.commons.annotation.MagicianAop;
import com.magician.containers.commons.base.BaseAop;


/**
 * 执行AOP
 */
public class ExecAop {

    /**
     * 方法开始前
     * @param args 参数
     * @param aopModel Aop实体
     */
    public static void startMethod(Object[] args, AopModel aopModel) {
        if (aopModel == null) {
            return;
        }

        BaseAop baseAop = (BaseAop)aopModel.getObj();
        baseAop.startMethod(args);
    }

    /**
     * 方法开始后
     * @param args 参数
     * @param result 参数
     * @param aopModel 对象
     */
    public static void endMethod(Object[] args, Object result, AopModel aopModel) {
        if (aopModel == null) {
            return;
        }
        BaseAop baseAop = (BaseAop)aopModel.getObj();
        baseAop.endMethod(args, result);
    }

    /**
     * 异常
     * @param aopModel 对象
     * @param e 异常对象
     */
    public static void exp(AopModel aopModel, Throwable e) {
        if (aopModel == null) {
            return;
        }

        BaseAop baseAop = (BaseAop)aopModel.getObj();
        baseAop.exp(e);
    }

    /**
     * 获取aop实体
     * @param magicianAop aop注解
     * @return aop实体
     *
     * @throws Exception 异常
     */
    public static AopModel getAopModel(MagicianAop magicianAop) throws Exception {
        if(magicianAop == null) {
            return null;
        }
        AopModel aopModel = new AopModel();
        aopModel.setCls(magicianAop.className());
        aopModel.setObj(magicianAop.className().getDeclaredConstructor().newInstance());
        return aopModel;
    }
}
