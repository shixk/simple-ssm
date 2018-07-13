package com.imooc.vat.strategy;

import com.google.common.collect.Maps;
import com.imooc.vat.annotation.Strategy;
import com.imooc.vat.strategy.transport.TransportAction;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author shixuekai
 * @CreateDate 2018/7/13
 * @Description
 **/
@Component
public class StrategyResourcePool implements ApplicationContextAware{

    private Map<String,TransportAction> mapStrategy= Maps.newHashMap();

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
        reloadStrategyClass();
    }

    /**
     * 加载所有@Strategy注解的类
     */
    private void reloadStrategyClass(){
        Map<String, Object> map=applicationContext.getBeansWithAnnotation(Strategy.class);
        for(Map.Entry<String,Object> entry:map.entrySet()){
            TransportAction transportAction=(TransportAction)entry.getValue();
            mapStrategy.put(transportAction.getStrategyName(),transportAction);
        }
    }

    /**
     * 获取对应的策略类对象
     * @param strategyName
     * @return
     */
    public TransportAction getTransportAction(String strategyName){
        return mapStrategy.get(strategyName);
    }
}
