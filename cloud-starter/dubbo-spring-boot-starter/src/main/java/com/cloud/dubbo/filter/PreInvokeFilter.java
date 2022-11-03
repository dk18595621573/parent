package com.cloud.dubbo.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.ConcurrentHashSet;
import org.apache.dubbo.common.utils.NamedThreadFactory;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.support.AccessLogData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * RPC调用前记录日志.
 *
 * @author breggor
 */
@Activate(group = CommonConstants.CONSUMER)
public class PreInvokeFilter extends AbstractFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreInvokeFilter.class);

    private static final int LOG_MAX_BUFFER = 5000;

    private static final long LOG_OUTPUT_INTERVAL = 5000;

    private static final Set<AccessLogData> LOG_ENTRIES = new ConcurrentHashSet<>();

    private static final ScheduledExecutorService SCHEDULED = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("Preinvoke-Log", true));

    public PreInvokeFilter() {
        SCHEDULED.scheduleWithFixedDelay(this::writeLog, LOG_OUTPUT_INTERVAL, LOG_OUTPUT_INTERVAL, TimeUnit.MILLISECONDS);
    }

    @Override
    public Result invoke(final Invoker<?> invoker, final Invocation invocation) throws RpcException {
        try {
            AccessLogData logData = buildAccessLogData(invoker, invocation);
            log(logData);
        } catch (Exception e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Exception in PreInvokeFilter (" + invoker + " -> " + invocation + ")", e);
            }
        }
        return invoker.invoke(invocation);
    }

    /**
     * 记录日志.
     *
     * @param logData logdata
     */
    private void log(final AccessLogData logData) {
        if (LOG_ENTRIES.size() < LOG_MAX_BUFFER) {
            LOG_ENTRIES.add(logData);
        } else {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Log buffer is full. Do a force writing to file to clear buffer.");
            }
            this.writeLog();
            LOG_ENTRIES.add(logData);
        }
    }

    /**
     * 写日志.
     */
    private void writeLog() {
        if (!LOG_ENTRIES.isEmpty()) {
            for (Iterator<AccessLogData> it = LOG_ENTRIES.iterator(); it.hasNext(); it.remove()) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info(it.next().getLogMessage());
                }
            }
        }
    }

    /**
     * 构建日志.
     *
     * @param invoker invoker
     * @param inv     invocation
     * @return AccessLogData
     */
    private AccessLogData buildAccessLogData(final Invoker<?> invoker, final Invocation inv) {
        AccessLogData logData = AccessLogData.newLogData();
        logData.buildAccessLogData(invoker, inv);
        return logData;
    }
}