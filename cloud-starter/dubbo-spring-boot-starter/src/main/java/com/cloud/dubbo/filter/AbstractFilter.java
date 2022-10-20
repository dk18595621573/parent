package com.cloud.dubbo.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.RpcContext;

/**
 * 抽象过滤器.
 *
 * @author zenghao
 * @date 2022/8/15
 */
@Slf4j
public abstract class AbstractFilter implements Filter {

    protected boolean isConsumerSide() {
//        URL consumerUrl = ObjectUtils.defaultIfNull(RpcContext.getServiceContext().getConsumerUrl(), RpcContext.getServiceContext().getUrl());
//        if (Objects.isNull(consumerUrl)) {
//            log.warn("Filter get isConsumerSide error: url is null");
//            throw new RuntimeException("获取当前服务信息失败");
//        }
//        return consumerUrl.getSide(CommonConstants.PROVIDER_SIDE).equals(CommonConstants.CONSUMER_SIDE);
        return RpcContext.getContext().isConsumerSide();
    }

    protected boolean isProviderSide() {
        return !isConsumerSide();
    }
}
