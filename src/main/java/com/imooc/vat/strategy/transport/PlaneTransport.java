package com.imooc.vat.strategy.transport;

import com.imooc.vat.annotation.Strategy;
import com.imooc.vat.constants.StrategyNameConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author shixuekai
 * @CreateDate 2018/7/13
 * @Description
 **/
@Strategy
@Component
public class PlaneTransport implements TransportAction {

    private static Logger LOG= LoggerFactory.getLogger(CarTransport.class);

    @Override
    public void doAction() {
        LOG.info("飞机运输执行策略");
    }

    @Override
    public String getStrategyName() {
        return StrategyNameConstants.Strategy_Name_Plane;
    }
}
