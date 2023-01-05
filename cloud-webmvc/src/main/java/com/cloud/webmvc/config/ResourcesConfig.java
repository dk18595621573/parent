package com.cloud.webmvc.config;

import com.cloud.webmvc.interceptor.AuthorizeInterceptor;
import com.cloud.webmvc.interceptor.RepeatSubmitInterceptor;
import com.cloud.webmvc.properties.SystemProperties;
import org.apache.commons.lang3.ArrayUtils;
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

    private static final String[] ALLOW_VISIT_PATHS = {"/doc.html", "/swagger-resources", "/webjars/**", "/*/api-docs", "/login"};
    @Autowired(required = false)
    private RepeatSubmitInterceptor repeatSubmitInterceptor;
    @Autowired
    private AuthorizeInterceptor authorizeInterceptor;
    @Autowired
    private SystemProperties systemProperties;

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
    public void addInterceptors(final InterceptorRegistry registry) {
        String[] allowPathArr = ALLOW_VISIT_PATHS;
        if (ArrayUtils.isNotEmpty(systemProperties.getSecurity().getAllows())) {
            String[] arr = systemProperties.getSecurity().getAllows();
            allowPathArr = new String[arr.length + ALLOW_VISIT_PATHS.length];

            System.arraycopy(ALLOW_VISIT_PATHS, 0, allowPathArr, 0, ALLOW_VISIT_PATHS.length);
            System.arraycopy(arr, 0, allowPathArr, ALLOW_VISIT_PATHS.length, arr.length);
        }

        registry.addInterceptor(authorizeInterceptor).excludePathPatterns(allowPathArr).addPathPatterns("/**");
        if (Objects.nonNull(repeatSubmitInterceptor)) {
            registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
        }
    }


}