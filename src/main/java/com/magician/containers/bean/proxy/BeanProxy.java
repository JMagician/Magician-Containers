package com.magician.containers.bean.proxy;

import java.lang.reflect.Method;

import com.magician.containers.bean.aop.model.AopModel;
import com.magician.containers.commons.annotation.MagicianAop;
import com.magician.containers.bean.aop.exec.ExecAop;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * proxy class
 * @author yuye
 *
 */
public class BeanProxy implements MethodInterceptor {

    private Enhancer enhancer;

    /**
     * get proxy object
     *
     * @param clazz
     * @return
     */
    public Object getProxy(Class<?> clazz) {
        enhancer = new Enhancer();
        // Set the class that needs to be subclassed
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        // Dynamic creation of subclass instances through bytecode technology
        return enhancer.create();
    }

    /**
     * bind proxy
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        AopModel aopModel = null;

        try {

            MagicianAop marsAop = method.getAnnotation(MagicianAop.class);

            aopModel = ExecAop.getAopModel(marsAop);

            // Execute the start method of aop
            ExecAop.startMethod(args, aopModel);

            // Execute the method in the proxied class
            Object o1 = methodProxy.invokeSuper(o, args);

            // Execute the end method of aop
            ExecAop.endMethod(args, o1, aopModel);

            return o1;
        } catch (Throwable e) {
            // AOP handles exceptions
            ExecAop.exp(aopModel, e);
            throw e;
        }
    }
}
