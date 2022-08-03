package com.cloud.webmvc.config;

import com.cloud.webmvc.interceptor.RepeatSubmitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Objects;

/**
 * 通用配置
 *
 * @author author
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
    @Autowired(required = false)
    private RepeatSubmitInterceptor repeatSubmitInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** swagger配置 */
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 自定义拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (Objects.nonNull(repeatSubmitInterceptor)) {
            registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
        }
    }


}