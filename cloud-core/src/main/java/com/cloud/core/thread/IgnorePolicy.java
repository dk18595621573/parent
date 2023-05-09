package com.cloud.core.thread;

import com.cloud.common.utils.StringUtils;
import com.cloud.core.manager.AsyncManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 忽略超出的任务，并打印日志.
 *
 * @author zenghao
 * @date 2023/5/9
 */
@Slf4j
public class IgnorePolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(final Runnable r, final ThreadPoolExecutor e) {
        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
        StackTraceElement stackTrace = null;
        for (int i = 0, len = stackTraces.length; i < len; i++) {
            StackTraceElement element = stackTraces[i];
            if (StringUtils.equals(element.getClassName(), AbstractExecutorService.class.getName()) && "submit".equals(element.getMethodName())) {
                stackTrace = stackTraces[i + 1];
                if (Objects.nonNull(stackTrace) && StringUtils.equals(stackTrace.getClassName(), AsyncManager.class.getName()) && "execute".equals(stackTrace.getMethodName())) {
                    stackTrace = stackTraces[i + 2];
                }
                break;
            }
        }
        log.warn("当前任务已丢弃：{}", stackTrace);
    }
}
