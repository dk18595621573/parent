package com.cloud.core.log.decorator;

import com.cloud.common.utils.Threads;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

/**
 * mdc日志数据处理
 */
public class MdcTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(final Runnable runnable) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return () -> {
            try {
                if (contextMap != null) {
                    MDC.setContextMap(contextMap);
                }
                runnable.run();
            } catch (Throwable e) {
                //执行出现异常，打印异常信息
                Threads.printException(runnable, e);
            } finally {
                MDC.clear();
            }
        };
    }
}