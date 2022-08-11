package com.cloud.dubbo.filter;

import com.cloud.common.threads.RequestThread;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;

/**
 * 多租户参数设置filter.
 *
 * @author zenghao
 * @date 2021/1/24
 */
@Activate(group = {CommonConstants.CONSUMER, CommonConstants.PROVIDER})
public class CustomRequestFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String REQUEST_DATA = "REQUEST-DATA";

    @Override
    public Result invoke(final Invoker<?> invoker, final Invocation invocation) throws RpcException {
        try {
            if (RpcContext.getContext().isConsumerSide()) {
                Map<String, Object> data = RequestThread.getData();
                logger.debug("CustomRequestFilter Consumer：[{}]", data);
                RpcContext.getContext().setAttachment(REQUEST_DATA, data);
            } else if (RpcContext.getContext().isProviderSide()) {
                Object data = RpcContext.getContext().getObjectAttachment(REQUEST_DATA);
                logger.debug("CustomRequestFilter Provider：[{}]", data);
                if (Objects.nonNull(data)) {
                    RequestThread.setData((Map<String, Object>) data);
                }
            }
            return invoker.invoke(invocation);
        } catch (Exception e) {
            logger.error("Exception in TenantFilter ({} -> {})", invoker, invocation, e);
            return invoker.invoke(invocation);
        } finally {
            //服务器提供者清理线程中的请求信息
            if (RpcContext.getContext().isProviderSide()) {
                RequestThread.clear();
            }
        }
    }
}
