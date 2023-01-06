package com.cloud.dubbo.filter;

import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象过滤器.
 *
 * @author zenghao
 * @date 2022/8/15
 */
public abstract class AbstractFilter implements Filter {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractFilter.class.getPackageName());

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
