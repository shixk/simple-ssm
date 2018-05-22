package com.imooc.vat.exceptions;

import lombok.Data;
import lombok.ToString;

/**
 * @Author shixuekai
 * @CreateDate 2018/5/22
 * @Description 业务异常
 **/
@Data
@ToString
public class BizException extends Exception{

    private int code;

    private String msg;

    public BizException(int code,String msg){
        super(msg);
        this.code=code;
        this.msg=msg;
    }

    public BizException(String msg, Throwable cause, int code) {
        super(msg, cause);
        this.code = code;
        this.msg=msg;
    }
}
