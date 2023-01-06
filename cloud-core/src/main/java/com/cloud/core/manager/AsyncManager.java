package com.cloud.core.manager;

import com.cloud.common.utils.Threads;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.MDC;

import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务管理器
 *
 * @author author
 */
public class AsyncManager {

    // 核心线程池大小
    private static final int CORE_POOL_SIZE = 10;
    /**
     * 操作延迟10毫秒
     */
    private static final int OPERATE_DELAY_TIME = 10;

    /**
     * 异步操作任务调度线程池
     */
    private static final ScheduledExecutorService executor;

    static {
        executor = new ScheduledThreadPoolExecutor(CORE_POOL_SIZE,
            new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build(),
            new ThreadPoolExecutor.CallerRunsPolicy()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                Threads.printException(r, t);
            }
        };
    }
    /**
     * 单例模式
     */
    private AsyncManager() {
    }

    private static final AsyncManager me = new AsyncManager();

    public static AsyncManager me() {
        return me;
    }

    /**
     * 执行任务
     *
     */
    public void execute(AsyncExecute exec) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        executor.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (contextMap != null) {
                        MDC.setContextMap(contextMap);
                    }
                    exec.run();
                } finally {
                    MDC.clear();
                }
            }
        }, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        Threads.shutdownAndAwaitTermination(executor);
    }

}
