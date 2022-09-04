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
 * Load beans
 */
public class LoadBeans {

    private static Set<String> classList = MagicianCacheManager.getScanClassList();

    /**
     * Create bean object
     *
     * @throws Exception
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
                    throw new Exception("A bean with name [" + beanName + "] already exists");
                }
            }

            /* 初始化MarsBean */
            initBean(BeanCacheManager.getBeanModelMap());
        } catch (Exception e) {
            throw new Exception("Error loading and injecting MarsBean", e);
        }
    }

    /**
     * create bean
     *
     * @param className
     * @return
     * @throws Exception
     */
    private static Object createBean(Class<?> className) throws Exception {
        try {
            Method[] methods = className.getDeclaredMethods();
            if (methods != null && methods.length > 0) {
                for (Method method : methods) {
                    MagicianAop marsAop = method.getAnnotation(MagicianAop.class);
                    if (marsAop != null) {
                        /* If AOP is used in the bean, the object is created from the dynamic proxy */
                        BeanProxy marsBeanProxy = new BeanProxy();
                        return marsBeanProxy.getProxy(className);
                    }
                }
            }
            /* If AOP is not used in the bean, create the object directly */
            return className.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new Exception("Error creating bean object of type [" + className.getName() + "]", e);
        }
    }

    /**
     * Initialize beans
     *
     * @param marsBeanObjects
     * @throws Exception
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
            throw new Exception("Error while initializing MarsBean", e);
        }
    }
}
