package com.cloud.webmvc.spring.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 注入自身proxy 对象.
 *
 * @author Luo
 * @date 2021-9-23 10:59:37
 */
@Slf4j
@Component
public class InjectBeanSelfProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        if (bean instanceof BeanSelfAware) {
            if (log.isDebugEnabled()) {
                log.debug(" Bean {}  Inject {} himself ");
            }
            BeanSelfAware myBean = (BeanSelfAware) bean;
            myBean.setSelf(bean);
            return myBean;
        }
        return bean;
    }
}
