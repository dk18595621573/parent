package com.cloud.dubbo.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.RpcContext;

import java.util.Objects;

/**
 * 抽象过滤器.
 *
 * @author zenghao
 * @date 2022/8/15
 */
@Slf4j
public abstract class AbstractFilter implements Filter {

    protected boolean isConsumerSide() {
        URL consumerUrl = ObjectUtils.defaultIfNull(RpcContext.getServiceContext().getConsumerUrl(), RpcContext.getServiceContext().getUrl());
        if (Objects.isNull(consumerUrl)) {
            log.warn("Filter get isConsumerSide error: url is null");
            throw new RuntimeException("获取当前服务信息失败");
        }
        return consumerUrl.getSide(CommonConstants.PROVIDER_SIDE).equals(CommonConstants.CONSUMER_SIDE);
    }

    protected boolean isProviderSide() {
        return !isConsumerSide();
    }
}
