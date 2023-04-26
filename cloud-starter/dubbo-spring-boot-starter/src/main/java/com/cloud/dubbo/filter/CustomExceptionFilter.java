package com.cloud.dubbo.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.service.GenericService;

import java.lang.reflect.Method;

/**
 * 排除项目异常.
 *
 * @author breggor
 */
@Activate(group = CommonConstants.PROVIDER)
public class CustomExceptionFilter extends AbstractFilter implements Filter, Filter.Listener {

    private static final String PROJECT_PACKAGE = "com.cloud";

    @Override
    public Result invoke(final Invoker<?> invoker, final Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    @Override
    public void onResponse(final Result appResponse, final Invoker<?> invoker, final Invocation invocation) {
        if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {
            try {
                Throwable exception = appResponse.getException();

                // 非运行时异常，并且为 checked 异常，直接抛出
                if (!(exception instanceof RuntimeException) && (exception instanceof Exception)) {
                    return;
                }
                // 方法上声名的异常直接抛出
                try {
                    Method method = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
                    Class<?>[] exceptionClassses = method.getExceptionTypes();
                    for (Class<?> exceptionClass : exceptionClassses) {
                        if (exception.getClass().equals(exceptionClass)) {
                            return;
                        }
                    }
                } catch (NoSuchMethodException e) {
                    return;
                }
                // 校验异常类和接口类是否在同一个jar包.
                String serviceFile = ReflectUtils.getCodeBase(invoker.getInterface());
                String exceptionFile = ReflectUtils.getCodeBase(exception.getClass());
                if (serviceFile == null || exceptionFile == null || serviceFile.equals(exceptionFile)) {
                    return;
                }

                String className = exception.getClass().getName();
                // 排除项目已定义异常类
                if (className.startsWith(PROJECT_PACKAGE)) {
                    LOGGER.warn("dubbo业务抛出异常【{}】{}: {}", methodInfo(invoker, invocation),
                        exception.getClass().getName(),exception.getMessage());
                    return;
                }
                // 打印异常日志.
                LOGGER.error("dubbo业务异常【{}】异常: [{}:{}]", methodInfo(invoker, invocation),
                    exception.getClass().getName(), exception.getMessage(), exception);

                // 如果是JDK异常，直接抛出
                if (className.startsWith("java.") || className.startsWith("javax.")) {
                    return;
                }

                // 是否为dubbo内部的异常
                if (exception instanceof RpcException) {
                    return;
                }
//                if (exception instanceof DataAccessException) {
//                    appResponse.setException(new RpcException(RpcException.BIZ_EXCEPTION, "系统数据异常", exception));
//                    return;
//                }

                // 以上都不是，包装成 RuntimeException 抛出
                appResponse.setException(new RuntimeException(StringUtils.toString(exception)));
            } catch (Throwable e) {
                LOGGER.warn("dubbo异常过滤器执行失败【{}】, 失败原因:[{}:{}] ", methodInfo(invoker, invocation)
                    , e.getClass().getName(), e.getMessage(), e);
            }
        }
    }

    @Override
    public void onError(final Throwable e, final Invoker<?> invoker, final Invocation invocation) {
        LOGGER.error("dubbo业务异常【{}】exception: [{}:{}]", methodInfo(invoker, invocation),
            e.getClass().getName(), e.getMessage(), e);
    }

    @Override
    protected String methodInfo(final Invoker<?> invoker, final Invocation invocation) {
        return RpcContext.getContext().getRemoteHost() + ": " + super.methodInfo(invoker, invocation);
    }
}

