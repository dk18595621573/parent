package com.cloud.seata;

import com.cloud.seata.handler.WxWorkFailureHandler;
import io.seata.tm.api.FailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.seata.common.Constants.BEAN_NAME_FAILURE_HANDLER;

/**
 * Seata 自动装配.
 *
 * @author zenghao
 * @date 2021/3/9
 */
@Configuration
public class SeataConfiguration {

    @Bean(BEAN_NAME_FAILURE_HANDLER)
    public FailureHandler failureHandler() {
        return new WxWorkFailureHandler();
    }

}
