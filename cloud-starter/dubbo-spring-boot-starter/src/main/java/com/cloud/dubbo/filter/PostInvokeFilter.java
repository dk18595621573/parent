package com.cloud.dubbo.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cloud.common.utils.json.JsonUtil;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.ConcurrentHashSet;
import org.apache.dubbo.common.utils.NamedThreadFactory;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.MDC;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * RPC调用响应结果日志.
 */
@Activate(group = CommonConstants.PROVIDER)
public class PostInvokeFilter extends AbstractFilter implements Filter, Filter.Listener  {

    private static final int LOG_MAX_BUFFER = 5000;

    private static final long LOG_OUTPUT_INTERVAL = 5000;

    private static final Set<LogData> LOG_ENTRIES = new ConcurrentHashSet<>();

    private static final ScheduledExecutorService SCHEDULED = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("PostInvoke-Log", true));

    public PostInvokeFilter() {
        SCHEDULED.scheduleWithFixedDelay(this::writeLog, LOG_OUTPUT_INTERVAL, LOG_OUTPUT_INTERVAL, TimeUnit.MILLISECONDS);
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        try {
            String methodInfo = methodInfo(invoker, invocation);
            // 处理后的响应结果
            String info = processResult(appResponse.getValue());
            log(new LogData(String.format("(%s) %s", methodInfo, info), MDC.getCopyOfContextMap()));
        } catch (Exception e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Exception in PostInvokeFilter (" + invoker + " -> " + invocation + ")", e);
            }
        }
    }

    @Override
    public void onError(final Throwable t, final Invoker<?> invoker, final Invocation invocation) {

    }

    /**
     * 记录日志.
     *
     * @param logData logdata
     */
    private void log(final LogData logData) {
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
            for (Iterator<LogData> it = LOG_ENTRIES.iterator(); it.hasNext(); it.remove()) {
                if (LOGGER.isInfoEnabled()) {
                    LogData logData = it.next();
                    MDC.clear();
                    if (CollUtil.isNotEmpty(logData.getMdc())) {
                        MDC.setContextMap(logData.getMdc());
                    }
                    LOGGER.info(logData.getData());
                }
            }
        }
    }

    /**
     * 集合、数组，Map 长度超过二十则不打印内容
     *
     * @param arg 请求参数
     * @return 长度超过二十则打印长度，否则打印数据内容
     */
    private String processResult(Object arg) {
        if (Objects.isNull(arg)) {
            return null;
        }
        if (ClassUtil.isBasicType(arg.getClass()) || ClassUtil.isSimpleValueType(arg.getClass())) {
            return String.valueOf(arg);
        }
        if (arg instanceof Collection || arg instanceof Map || arg.getClass().isArray()) {
            int len = ObjectUtil.length(arg);
            if (len > 20) {
                return String.format("%s@%s", arg.getClass(), len);
            }
            return JsonUtil.toJson(arg);
        }
        Map<String, Object> map = new HashMap<>();
        ReflectionUtils.doWithFields(arg.getClass(), f -> {
            f.setAccessible(true);
            if (arg.getClass() == f.getDeclaringClass()) {
                map.put(f.getName(), JsonUtil.toJson(f.get(arg)));
            } else {
                map.put(f.getName(), processResult(f.get(arg)));
            }
        }, f -> !Modifier.isStatic(f.getModifiers()) || !Modifier.isFinal(f.getModifiers()));
        return JsonUtil.toJson(map);
    }

    static class LogData {
        private final String data;
        private final Map<String, String> mdc;

        LogData(String data, Map<String, String> mdc) {
            this.data = data;
            this.mdc = mdc;
        }
        public String getData() {
            return data;
        }

        public Map<String, String> getMdc() {
            return mdc;
        }
    }
}
