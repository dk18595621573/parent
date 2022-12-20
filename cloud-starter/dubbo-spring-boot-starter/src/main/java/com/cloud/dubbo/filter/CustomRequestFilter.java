package com.cloud.dubbo.filter;

import com.cloud.common.threads.RequestThread;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
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
public class CustomRequestFilter extends AbstractFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String REQUEST_DATA = "REQUEST-DATA";

    @Override
    public Result invoke(final Invoker<?> invoker, final Invocation invocation) throws RpcException {
        try {
            if (isConsumerSide()) {
                Map<String, Object> data = RequestThread.getData();
                logger.debug("CustomRequestFilter MethodName： [{}.{}]， Consumer：[{}] ",
                    invoker.getInterface().getName(), invocation.getMethodName(), data);
                RpcContext.getContext().setAttachment(REQUEST_DATA, data);
            } else if (isProviderSide()) {
                Object data = RpcContext.getContext().getObjectAttachment(REQUEST_DATA);
                logger.debug("CustomRequestFilter Provider：[{}]", data);
                if (Objects.nonNull(data) && data instanceof Map) {
                    RequestThread.setData((Map<String, Object>) data);
                }
            }
            return invoker.invoke(invocation);
        } catch (Exception e) {
            logger.error("Exception in CustomRequestFilter ({} -> {})", invoker, invocation, e);
            return invoker.invoke(invocation);
        } finally {
            //服务器提供者清理线程中的请求信息
            if (isProviderSide()) {
                RequestThread.clear();
            }
        }
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
