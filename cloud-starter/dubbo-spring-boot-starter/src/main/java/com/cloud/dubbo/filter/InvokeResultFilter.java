package com.cloud.dubbo.filter;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cloud.common.utils.json.JsonUtil;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.filter.ContextFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Modifier;
import java.util.*;

/**
 * RPC调用响应结果日志.
 */
@Activate(group = CommonConstants.PROVIDER)
public class InvokeResultFilter extends ContextFilter {

    Logger LOGGER = LoggerFactory.getLogger(InvokeResultFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        String methodName = invocation.getMethodName(); // 调用的方法名
        Object[] arguments = invocation.getArguments(); // 请求参数
        Object value = appResponse.getValue(); // 响应结果
        String info = processArg(value); // 处理后的响应结果
        LOGGER.debug("==> {} arguments：{}",methodName,arguments);
        LOGGER.debug("==> {} response：{}",methodName,info);
    }

    /**
     * 集合、数组，Map 长度超过二十则不打印内容
     * @param arg 请求参数
     * @return 长度超过二十则打印长度，否则打印数据内容
     */
    private String processArg(Object arg) {
        if (Objects.isNull(arg)) {
            return null;
        }
        if (ClassUtil.isBasicType(arg.getClass())  || ClassUtil.isSimpleValueType(arg.getClass())) {
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
            if (arg.getClass() == f.getDeclaringClass()){
                map.put(f.getName(), JsonUtil.toJson(f.get(arg)));
            }else{
                map.put(f.getName(), processArg(f.get(arg)));
            }
        }, f -> !Modifier.isStatic(f.getModifiers()) || !Modifier.isFinal(f.getModifiers()));
        return JsonUtil.toJson(map);
    }
}
