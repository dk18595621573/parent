package com.cloud.core.manager;

import com.cloud.common.utils.Threads;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务管理器
 *
 * @author author
 */
public class AsyncManager {

    /**
     * 核心线程池大小.
     */
    private static final int CORE_POOL_SIZE = 6;
    /**
     * 最大线程池大小
     */
    private static final int MAX_SIZE = 10;
    /**
     * 任务队列大小
     */
    private static final int QUEUE_CAPACITY = 1000;

    /**
     * 异步操作任务调度线程池
     */
    private static final ThreadPoolExecutor executor;

    static {
        executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_SIZE, 2, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(QUEUE_CAPACITY),
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
        executor.submit(() -> {
            try {
                if (contextMap != null) {
                    MDC.setContextMap(contextMap);
                }
                exec.run();
            } finally {
                MDC.clear();
            }
        });
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        Threads.shutdownAndAwaitTermination(executor);
    }

}
