package com.imooc.vat.gateway;

import com.imooc.vat.entity.Orders;
import com.imooc.vat.service.ThrowingConsumer;
import com.imooc.vat.serviceImpl.tool.RunnableExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author shixuekai
 * @CreateDate 2018/5/23
 * @Description 假设这是一个外部的第三方接口
 **/
@Component
public class ThirdPartyPayService {

    @Resource
    private RunnableExecutorService runnableExecutorService;

    static final Logger LOGGER= LoggerFactory.getLogger(ThirdPartyPayService.class);

    public int addOrder(Orders orders){
        try{
            // 假设这里是外部的操作
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            LOGGER.error("ThirdPart exception..",e);
            // 这里其实是应该执行外部接口提供的补偿方法
            runnableExecutorService.executeRetry((ThrowingConsumer)p->orders.setCustName("失败"));
        }

        return 1;
    }
}
