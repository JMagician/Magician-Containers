package com.magician.containers.bean.aop.exec;

import com.magician.containers.bean.aop.model.AopModel;
import com.magician.containers.commons.annotation.MagicianAop;
import com.magician.containers.commons.base.BaseAop;


/**
 * Execute AOP
 */
public class ExecAop {

    /**
     * Before the method starts
     * @param args
     * @param aopModel
     */
    public static void startMethod(Object[] args, AopModel aopModel) {
        if (aopModel == null) {
            return;
        }

        BaseAop baseAop = (BaseAop)aopModel.getObj();
        baseAop.startMethod(args);
    }

    /**
     * After the method starts
     * @param args
     * @param result
     * @param aopModel
     */
    public static void endMethod(Object[] args, Object result, AopModel aopModel) {
        if (aopModel == null) {
            return;
        }
        BaseAop baseAop = (BaseAop)aopModel.getObj();
        baseAop.endMethod(args, result);
    }

    /**
     * method execution exception
     * @param aopModel
     * @param e
     */
    public static void exp(AopModel aopModel, Throwable e) {
        if (aopModel == null) {
            return;
        }

        BaseAop baseAop = (BaseAop)aopModel.getObj();
        baseAop.exp(e);
    }

    /**
     * get aop entity
     * @param magicianAop
     * @return
     *
     * @throws Exception
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
