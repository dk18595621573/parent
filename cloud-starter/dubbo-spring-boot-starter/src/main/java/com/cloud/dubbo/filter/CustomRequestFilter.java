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

import java.util.Map;
import java.util.Objects;

/**
 * 多租户参数设置filter.
 *
 * @author zenghao
 * @date 2021/1/24
 */
@Activate(group = {CommonConstants.CONSUMER, CommonConstants.PROVIDER}, order = -10000)
public class CustomRequestFilter extends AbstractFilter {

    private static final String REQUEST_DATA = "REQUEST-DATA";

    @Override
    public Result invoke(final Invoker<?> invoker, final Invocation invocation) throws RpcException {
        try {
            if (isConsumerSide()) {
                Map<String, Object> data = RequestThread.getData();
                LOGGER.debug("CustomRequestFilter[{}] Consumer：[{}] ", methodInfo(invoker, invocation), data);

                //将线程数据写写入context中传递给提供者
                RpcContext.getContext().setAttachment(REQUEST_DATA, data);
                RpcContext.getContext().setAttachment(Constants.MDC_TRACE_ID, StringUtils.defaultString(MDC.get(Constants.MDC_TRACE_ID)));
            } else if (isProviderSide()) {
                Object data = RpcContext.getContext().getObjectAttachment(REQUEST_DATA);
                LOGGER.debug("CustomRequestFilter[{}] Provider：[{}]", methodInfo(invoker, invocation), data);

                //给提供者设置线程数据，并写入MDC打印日志
                MDC.put(Constants.MDC_TRACE_ID, StringUtils.defaultString(RpcContext.getContext().getAttachment(Constants.MDC_TRACE_ID)));
                if (Objects.nonNull(data) && data instanceof Map) {
                    RequestThread.setData((Map<String, Object>) data);

                    RequestUser user = RequestThread.getUser();
                    if (Objects.nonNull(user)) {
                        MDC.put(Constants.MDC_USER_ID, String.valueOf(user.getUserId()));
                        MDC.put(Constants.MDC_COMPANY_ID, String.valueOf(user.getDeptId()));
                    }
                }
            }
            return invoker.invoke(invocation);
        } catch (Exception e) {
            LOGGER.error("Exception in CustomRequestFilter[{}]", methodInfo(invoker, invocation), e);
        } finally {
            //服务器提供者清理线程中的请求信息
            if (isProviderSide()) {
                RequestThread.clear();
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
//            if (isConsumerSide()) {
//                Map<String, Object> data = RequestThread.getData();
//                logger.debug("CustomRequestFilter Consumer：[{}]", data);
//                RpcContext.getClientAttachment().setAttachment(REQUEST_DATA, data);
//            } else if (isProviderSide()) {
//                Object data = RpcContext.getServerContext().getObjectAttachment(REQUEST_DATA);
//                logger.debug("CustomRequestFilter Provider：[{}]", data);
//                if (Objects.nonNull(data)) {
//                    RequestThread.setData((Map<String, Object>) data);
//                }
//            }
//            return invoker.invoke(invocation);
//        } catch (Exception e) {
//            logger.error("Exception in CustomRequestFilter ({} -> {})", invoker, invocation, e);
//            return invoker.invoke(invocation);
//        } finally {
//            //服务器提供者清理线程中的请求信息
//            if (isProviderSide()) {
//                RequestThread.clear();
//            }
//        }
//    }
}
