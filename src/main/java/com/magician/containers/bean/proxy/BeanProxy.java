package com.magician.containers.bean.proxy;

import java.lang.reflect.Method;

import com.magician.containers.bean.aop.model.AopModel;
import com.magician.containers.commons.annotation.MagicianAop;
import com.magician.containers.bean.aop.exec.ExecAop;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理类
 * @author yuye
 *
 */
public class BeanProxy implements MethodInterceptor {

    private Enhancer enhancer;

    /**
     * 获取代理对象
     *
     * @param clazz bean的class
     * @return 对象
     */
    public Object getProxy(Class<?> clazz) {
        enhancer = new Enhancer();
        // 设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        // 通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    /**
     * 绑定代理
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        AopModel aopModel = null;

        try {

            MagicianAop marsAop = method.getAnnotation(MagicianAop.class);

            aopModel = ExecAop.getAopModel(marsAop);

            // 执行aop的开始方法
            ExecAop.startMethod(args, aopModel);

            // 执行方法本体
            Object o1 = methodProxy.invokeSuper(o, args);

            // 执行aop的结束方法
            ExecAop.endMethod(args, o1, aopModel);

            return o1;
        } catch (Throwable e) {
            // AOP处理异常
            ExecAop.exp(aopModel, e);
            throw e;
        }
    }
}
