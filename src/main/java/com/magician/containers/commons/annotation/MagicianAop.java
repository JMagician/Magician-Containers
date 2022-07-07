package com.magician.containers.commons.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MagicianAop {

	Class<?> className();
}
