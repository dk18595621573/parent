package com.cloud.generator;

import com.cloud.generator.service.GenTableServiceImpl;
import com.cloud.generator.service.IGenTableService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置.
 *
 * @author zenghao
 * @date 2022/7/20
 */
@Configuration
public class GenAutoConfiguration {

    @Bean
    public IGenTableService genTableService() {
        return new GenTableServiceImpl();
    }
}
