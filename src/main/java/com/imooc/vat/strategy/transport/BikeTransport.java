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
public class BikeTransport implements TransportAction {

    private static Logger LOG= LoggerFactory.getLogger(BikeTransport.class);

    @Override
    public void doAction() {

        LOG.info("执行了自行车运输动作");
    }

    @Override
    public String getStrategyName() {
        return StrategyNameConstants.Strategy_Name_Bike;
    }
}
