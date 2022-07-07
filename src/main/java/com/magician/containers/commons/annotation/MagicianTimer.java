package com.magician.containers.commons.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MagicianTimer {

    /**
     * 循环间隔
     * @return 间隔
     */
    int loop() default 60000;
}
