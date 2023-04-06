package com.cloud.core.spring.proxy;

/**
 * spring 自身注入自身方法 , 解决内部方法调用不走代理的问题.
 *
 * @author Luo
 * @date 2021-9-23 10:59:37
 */
public interface BeanSelfAware {

    /**
     * 注入自身对象.
     *
     * @param proxyBean 代理bean
     */
    void setSelf(Object proxyBean);
}
