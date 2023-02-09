package com.cloud.dubbo.filter;

import com.cloud.common.constant.Constants;
import com.cloud.common.core.model.RequestUser;
import com.cloud.common.threads.RequestThread;
import com.cloud.common.utils.StringUtils;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.MDC;

import java.util.Objects;


/**
 * MDC参数设置filter.
 *
 * @author breggor
 */
@Activate(group = {CommonConstants.CONSUMER, CommonConstants.PROVIDER}, order = -9999)
public class MDCFilter extends AbstractFilter {

    @Override
    public Result invoke(final Invoker<?> invoker, final Invocation invocation) throws RpcException {
        try {
            if (isConsumerSide()) {
                RpcContext.getContext().setAttachment(Constants.MDC_TRACE_ID, StringUtils.defaultString(MDC.get(Constants.MDC_TRACE_ID)));
            } else if (isProviderSide()) {
                MDC.put(Constants.MDC_TRACE_ID, StringUtils.defaultString(RpcContext.getContext().getAttachment(Constants.MDC_TRACE_ID)));

                RequestUser user = RequestThread.getUser();
                if (Objects.nonNull(user)) {
                    MDC.put(Constants.MDC_USER_ID, String.valueOf(user.getUserId()));
                    MDC.put(Constants.MDC_USER_ID, String.valueOf(user.getDeptId()));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Exception in MDCFilter (" + invoker + " -> " + invocation + ")", e);
        } finally {
            if (isProviderSide()) {
                MDC.clear();
            }
        }
        return invoker.invoke(invocation);
    }

//    @Override
//    public Result invoke(final Invoker<?> invoker, final Invocation invocation) throws RpcException {
//        try {
//            // 如果是内置元数据服务则跳过filter，否则走下面的会报错，导致元数据拉取失败，最终导致No provider错误
//            if (MetadataService.isMetadataServiceURL(invoker.getUrl())) {
//                return invoker.invoke(invocation);
//            }
//            if (isConsumerSide() && StringUtils.isNotEmpty(MDC.get(Constants.MDC_TRACE_ID))) {
//                RpcContext.getClientAttachment().setAttachment(Constants.MDC_TRACE_ID, MDC.get(Constants.MDC_TRACE_ID));
//            } else if (isProviderSide() &&
//                StringUtils.isNotEmpty(RpcContext.getServerContext().getAttachment(Constants.MDC_TRACE_ID))) {
//                MDC.put(Constants.MDC_TRACE_ID, RpcContext.getServerContext().getAttachment(Constants.MDC_TRACE_ID));
//            }
//        } catch (Exception e) {
//            logger.error("Exception in MDCFilter (" + invoker + " -> " + invocation + ")", e);
//        }
//        return invoker.invoke(invocation);
//    }
}
