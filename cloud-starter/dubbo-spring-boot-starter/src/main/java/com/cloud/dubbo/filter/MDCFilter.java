package com.cloud.dubbo.filter;

import com.cloud.common.constant.Constants;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.metadata.MetadataService;
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
public class MDCFilter extends AbstractFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Result invoke(final Invoker<?> invoker, final Invocation invocation) throws RpcException {
        try {
            // 如果是内置元数据服务则跳过filter，否则走下面的会报错，导致元数据拉取失败，最终导致No provider错误
            if (MetadataService.isMetadataServiceURL(invoker.getUrl())) {
                return invoker.invoke(invocation);
            }
            if (isConsumerSide() && StringUtils.isNotEmpty(MDC.get(Constants.MDC_TRACE_ID))) {
                RpcContext.getClientAttachment().setAttachment(Constants.MDC_TRACE_ID, MDC.get(Constants.MDC_TRACE_ID));
            } else if (isProviderSide() &&
                StringUtils.isNotEmpty(RpcContext.getServerContext().getAttachment(Constants.MDC_TRACE_ID))) {
                MDC.put(Constants.MDC_TRACE_ID, RpcContext.getServerContext().getAttachment(Constants.MDC_TRACE_ID));
            }
        } catch (Exception e) {
            logger.error("Exception in MDCFilter (" + invoker + " -> " + invocation + ")", e);
        }
        return invoker.invoke(invocation);
    }
}
