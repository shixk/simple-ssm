package com.imooc.vat.exceptions;

import lombok.Data;

/**
 * @Author shixuekai
 * @CreateDate 2018/5/22
 * @Description 调用远程服务异常
 **/
@Data
public class GatewayException extends Exception{

    // 也可以是远程服务的枚举
    private int serviceCode;

    private String apiName;

    private String message;

    public GatewayException(String message,int serviceCode,String apiName){
        super(message);
        this.serviceCode=serviceCode;
        this.apiName=apiName;
        this.message=message;
    }

    public GatewayException(String message,int serviceCode,String apiName,Throwable e){
        super(message,e);
        this.serviceCode=serviceCode;
        this.apiName=apiName;
        this.message=message;
    }

}
