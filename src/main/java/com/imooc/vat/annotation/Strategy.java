package com.imooc.vat.annotation;

import java.lang.annotation.*;

/**
 * @Author shixuekai
 * @CreateDate 2018/7/13
 * @Description
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Strategy {
    String value() default "";
}
