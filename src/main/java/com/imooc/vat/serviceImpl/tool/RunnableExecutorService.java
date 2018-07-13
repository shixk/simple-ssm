package com.imooc.vat.serviceImpl.tool;

import org.codehaus.groovy.runtime.typehandling.BigDecimalMath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @Author shixuekai
 * @CreateDate 2018/5/23
 * @Description 一个尝试去重试业务的工具service
 **/
@Component
public class RunnableExecutorService {
    private static final Logger LOG = LoggerFactory.getLogger(RunnableExecutorService.class);

    private ExecutorService threadPoolService;

    private ScheduledExecutorService scheduleService;

    private int poolSize = 2;

    @PostConstruct
    public void init() {
        threadPoolService = Executors.newFixedThreadPool(poolSize);
        scheduleService = Executors.newScheduledThreadPool(poolSize);
        LOG.info(("inited RunnableExecutorService!"));
    }

    @PreDestroy
    public void destroy() {
        if (threadPoolService != null) {
            threadPoolService.shutdown();
        }

        if(scheduleService!=null){
            scheduleService.shutdown();
        }
        LOG.info(("destory RunnableExecutorService!"));
    }

    //执行callable
    public <T> Future<T> submit(Callable<T> task) {

        return threadPoolService.submit(task);
    }

    //执行runnable
    public void execute(Runnable task) {

        threadPoolService.execute(task);
    }

    // 串行重试，当前方法抛出异常，将第一次new的Runnable 任务再次扔到线程池，知道执行到最大次数
    public void executeRetry(Consumer<?> consumer) {
        AtomicInteger retryTimes = new AtomicInteger(0);

        threadPoolService.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    LOG.info("retry job execute" + retryTimes.get() + "次数");
                    retryTimes.getAndIncrement();
                    consumer.accept(null);
                } catch (Exception e) {
                    LOG.error("retry filed", e);
                    if (retryTimes.get() < 20) {
                        threadPoolService.execute(this);
                    }
                }

            }
        });
    }

    /**
     * @Description: 支持对方法进行重试，可设最大重试次数（必传），指定多长时间重试一次。
     * 只是异常case 重试使用，考虑到线程池初始化为2个，服务容器发生异常任务将丢失，重要重试不要使用这个。
     * @Param startTime:任务提交后多久开始执行 ,
     * each:每隔多久执行一次 妙级
     * retryTimes:最大执行多少次
     */
    public void executeScheduleRetry(Consumer<?> consumer, long startTime, long each, final long retryTimes) {

        // 至少执行一次重试
        if (retryTimes < 1) {
            return;
        }
        long taskEndTime = BigDecimalMath.multiply(each, retryTimes).longValue();

        // 重试方法不管哪一次执行抛出异常则终止执行,不可恢复
        final ScheduledFuture<?> task = scheduleService.scheduleAtFixedRate(() -> consumer.accept(null),
                startTime, each, TimeUnit.SECONDS);

        //当到达预期最大时间时，开始尝试取消任务。
        scheduleService.schedule(() -> {
            LOG.info("retryTask work :" + retryTimes + "times  then cancel it");
            task.cancel(true);
        }, taskEndTime, TimeUnit.SECONDS);

        LOG.info("quit executeSchudleRetry");
    }


}
