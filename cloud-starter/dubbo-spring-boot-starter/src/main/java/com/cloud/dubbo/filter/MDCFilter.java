package com.cloud.dubbo.filter;

import com.cloud.common.constant.Constants;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;


/**
 * MDC参数设置filter.
 *
 * @author breggor
 */
@Activate(group = {CommonConstants.CONSUMER, CommonConstants.PROVIDER})
public class MDCFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Result invoke(final Invoker<?> invoker, final Invocation invocation) throws RpcException {
        try {
            if (RpcContext.getContext().isConsumerSide() && StringUtils.isNotEmpty(MDC.get(Constants.MDC_TRACE_ID))) {
                RpcContext.getContext().setAttachment(Constants.MDC_TRACE_ID, MDC.get(Constants.MDC_TRACE_ID));
            } else if (RpcContext.getContext().isProviderSide() && StringUtils.isNotEmpty(RpcContext.getContext().getAttachment(Constants.MDC_TRACE_ID))) {
                MDC.put(Constants.MDC_TRACE_ID, RpcContext.getContext().getAttachment(Constants.MDC_TRACE_ID));
            }
        } catch (Exception e) {
            logger.error("Exception in TraceIdFilter (" + invoker + " -> " + invocation + ")", e);
        }
        return invoker.invoke(invocation);
    }
}
