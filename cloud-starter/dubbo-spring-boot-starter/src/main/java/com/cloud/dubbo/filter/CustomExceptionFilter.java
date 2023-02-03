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

                // directly throw if it's checked exception
                if (!(exception instanceof RuntimeException) && (exception instanceof Exception)) {
                    return;
                }
                // directly throw if the exception appears in the signature
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

                // for the exception not found in method's signature, print ERROR message in server's log.
                LOGGER.error("dubbo业务异常【" + methodInfo(invoker, invocation) + "】异常: "
                    + exception.getClass().getName() + ": " + exception.getMessage(), exception);

                // directly throw if exception class and interface class are in the same jar file.
                String serviceFile = ReflectUtils.getCodeBase(invoker.getInterface());
                String exceptionFile = ReflectUtils.getCodeBase(exception.getClass());
                if (serviceFile == null || exceptionFile == null || serviceFile.equals(exceptionFile)) {
                    return;
                }
                // directly throw if it's JDK exception
                String className = exception.getClass().getName();
                if (className.startsWith("java.") || className.startsWith("javax.")) {
                    return;
                }
                // 排除项目已定义异常类
                if (className.startsWith(PROJECT_PACKAGE)) {
                    return;
                }
                // directly throw if it's dubbo exception
                if (exception instanceof RpcException) {
                    return;
                }
//                if (exception instanceof DataAccessException) {
//                    appResponse.setException(new RpcException(RpcException.BIZ_EXCEPTION, "系统数据异常", exception));
//                    return;
//                }

                // otherwise, wrap with RuntimeException and throw back to the client
                appResponse.setException(new RuntimeException(StringUtils.toString(exception)));
            } catch (Throwable e) {
                LOGGER.warn("dubbo异常过滤器执行失败【" + methodInfo(invoker, invocation) + ", 失败原因: "
                    + e.getClass().getName() + ": " + e.getMessage(), e);
            }
        }
    }

    @Override
    public void onError(final Throwable e, final Invoker<?> invoker, final Invocation invocation) {
        LOGGER.error("dubbo业务异常【" + methodInfo(invoker, invocation) + "】 exception: "
            + e.getClass().getName() + ": " + e.getMessage(), e);
    }

    @Override
    protected String methodInfo(final Invoker<?> invoker, final Invocation invocation) {
        return RpcContext.getContext().getRemoteHost() + ": " + super.methodInfo(invoker, invocation);
    }
}

