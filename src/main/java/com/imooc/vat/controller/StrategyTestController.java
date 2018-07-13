package com.imooc.vat.controller;

import com.imooc.vat.strategy.StrategyResourcePool;
import com.imooc.vat.strategy.transport.TransportAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author shixuekai
 * @CreateDate 2018/7/13
 * @Description
 **/
@Controller
@RequestMapping(value = "/strategy")
public class StrategyTestController {

    @Autowired
    private StrategyResourcePool strategyResourcePool;

    @ResponseBody
    @RequestMapping(value = "/transportAction")
    public String test(){
        //此处获取具体策略名称应从数据库里取
        //获取从redis中取得
        //获取通过上游数据的某个属性配置了强类型关联
        String strategyName="car";
        TransportAction transportAction=strategyResourcePool.getTransportAction(strategyName);
        transportAction.doAction();

        return "Success";
    }
}
