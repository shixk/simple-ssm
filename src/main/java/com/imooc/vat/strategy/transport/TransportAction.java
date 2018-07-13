package com.imooc.vat.strategy.transport;

/**
 * @Author shixuekai
 * @CreateDate 2018/7/13
 * @Description
 **/
public interface TransportAction {

    /**
     * 执行具体策略方法
     */
    void doAction();

    /**
     * 获取当前策略的名称
     * @return
     */
    String getStrategyName();
}
