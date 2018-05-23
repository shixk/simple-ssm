package com.imooc.vat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * @Author shixuekai
 * @CreateDate 2018/5/23
 * @Description 由于单纯的Consumer无法捕捉异常，
 *              因此需要新写一个包装类，catch住异常
 *              抛出去即可。
 **/
@FunctionalInterface
public interface ThrowingConsumer<T> extends Consumer<T>{
    static final Logger logger = LoggerFactory.getLogger(ThrowingConsumer.class);
    @Override
    default void accept(final T elem) {
        try {
            acceptThrows(elem);
        } catch (final Exception e) {
            logger.error(" Reflect handling an exception...", e);
            //业务是否需要 throw new RuntimeException(e);
        }
    }

    void acceptThrows(T elem) throws Exception;

}
