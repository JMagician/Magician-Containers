package com.magician.containers.bean;

import com.magician.containers.commons.annotation.MagicianAop;
import com.magician.containers.bean.proxy.BeanProxy;
import com.magician.containers.commons.annotation.MagicianBean;
import com.magician.containers.commons.base.InitBean;
import com.magician.containers.bean.model.BeanModel;
import com.magician.containers.commons.cache.BeanCacheManager;
import io.magician.common.cache.MagicianCacheManager;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * 加载bean
 */
public class LoadBeans {

    private static Set<String> classList = MagicianCacheManager.getScanClassList();

    /**
     * 创建Bean对象
     *
     * @throws Exception 异常
     */
    public static void loadBean() throws Exception {
        try {
            for (String beanClassName : classList) {

                Class<?> cls = Class.forName(beanClassName);

                MagicianBean magicianBean = cls.getAnnotation(MagicianBean.class);
                if(magicianBean == null){
                    continue;
                }

                String beanName = beanClassName;
                if (BeanCacheManager.get(beanName) == null) {
                    BeanModel beanModel = new BeanModel();
                    beanModel.setName(beanName);
                    beanModel.setCls(cls);
                    beanModel.setObj(createBean(cls));
                    BeanCacheManager.add(beanName, beanModel);
                } else {
                    throw new Exception("已经存在name为[" + beanName + "]的bean了");
                }
            }

            /* 初始化MarsBean */
            initBean(BeanCacheManager.getBeanModelMap());
        } catch (Exception e) {
            throw new Exception("加载并注入MarsBean的时候出现错误", e);
        }
    }

    /**
     * 创建bean
     *
     * @param className lei
     * @return beanObject
     * @throws Exception 异常
     */
    private static Object createBean(Class<?> className) throws Exception {
        try {
            Method[] methods = className.getDeclaredMethods();
            if (methods != null && methods.length > 0) {
                for (Method method : methods) {
                    MagicianAop marsAop = method.getAnnotation(MagicianAop.class);
                    if (marsAop != null) {
                        /* 如果bean里面用到了AOP，就从动态代理创建对象 */
                        BeanProxy marsBeanProxy = new BeanProxy();
                        return marsBeanProxy.getProxy(className);
                    }
                }
            }
            /* 如果bean里面没有用到AOP，就直接创建对象 */
            return className.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new Exception("创建[" + className.getName() + "]类型的bean对象出现错误", e);
        }
    }

    /**
     * 初始化Bean
     *
     * @param marsBeanObjects 对象
     * @throws Exception 异常
     */
    private static void initBean(Map<String, BeanModel> marsBeanObjects) throws Exception {
        try {
            for (String key : marsBeanObjects.keySet()) {
                BeanModel marsBeanModel = marsBeanObjects.get(key);
                Object obj = marsBeanModel.getObj();
                if (obj instanceof InitBean) {
                    Class<?> cls = marsBeanModel.getCls();

                    Method method = cls.getMethod("init");
                    method.invoke(obj);
                }
            }
        } catch (Exception e) {
            throw new Exception("初始化MarsBean的时候出现错误", e);
        }
    }
}
