package com.imooc.vat.exceptions;

import lombok.Data;

/**
 * @Author shixuekai
 * @CreateDate 2018/5/22
 * @Description 参数异常
 **/
@Data
public class ParamException extends Exception {

    private String message;

    public ParamException(String message){
        super(message);
        this.message=message;
    }

    public ParamException(String message,Throwable e){
        super(message,e);
        this.message=message;
    }
}
